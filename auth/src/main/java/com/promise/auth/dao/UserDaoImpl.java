package com.promise.auth.dao;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.promise.auth.entity.ScopeEntity;
import com.promise.auth.entity.UserEntity;
import com.promise.auth.sdk.dto.CreateUserRequest;
import com.promise.auth.sdk.dto.GetUserListResponse;
import com.promise.auth.sdk.dto.GetUserResponse;
import com.promise.auth.util.PasswordUtil;
import com.promise.auth.util.PasswordUtil.HashResult;
import com.promise.common.PromiseDao;
import com.promise.common.PromiseEntity;
import com.promise.common.PromiseUser;
import com.promise.common.constant.PromiseCategory;
import com.promise.common.exception.DbOperationException;
import com.promise.common.exception.NoDbInstanceException;
import com.promise.util.PromiseUtil;

@Repository("userDao")
public class UserDaoImpl extends PromiseDao<UserEntity, CreateUserRequest, GetUserResponse> implements UserDaoInterface
{

    public UserDaoImpl()
    {
        super(PromiseCategory.USER);
    }

    @Override
    public UserEntity fromCreateRequestToEntity(CreateUserRequest request)
            throws DbOperationException
    {
        final UserEntity entity = new UserEntity();
        entity.setUsername(request.getUsername());
        entity.setEmail(request.getEmail());
        HashResult hashResult;
        try
        {
            hashResult = PasswordUtil.hashPassword(request.getPassword());
        }
        catch (final NoSuchAlgorithmException e)
        {
            throw new DbOperationException();
        }
        entity.setHash(hashResult.getHash());
        entity.setSalt(hashResult.getSalt());
        final List<ScopeEntity> scopeList = new ArrayList<>();
        for (final String uri : PromiseUtil.emptyIfNull(request.getScopeUriList()))
        {
            final ScopeEntity scopeEntity = getSession().get(ScopeEntity.class, PromiseUtil.getIdFromUri(uri));
            if (scopeEntity == null)
            {
                throw new DbOperationException();
            }
            else
            {
                scopeList.add(scopeEntity);
            }
        }
        entity.setScopeList(scopeList);
        return entity;
    }

    @Override
    public GetUserResponse fromEntityToGetResponse(UserEntity entity)
    {
        final GetUserResponse response = new GetUserResponse();
        PromiseEntity.copyAttribute(response, entity);
        response.setUsername(entity.getUsername());
        response.setEmail(entity.getEmail());
        final List<ScopeEntity> scopeEntityList = entity.getScopeList();
        final List<String> scopeUriList = new ArrayList<>();
        for (final ScopeEntity each : PromiseUtil.emptyIfNull(scopeEntityList))
        {
            scopeUriList.add(each.getUri());
        }
        response.setScopeUri(scopeUriList);
        return response;
    }

    @Override
    public boolean isUsernameExist(String username)
    {
        final Query query = getSession().createQuery("from promise_user where username = :username");
        query.setParameter("username", username);
        try
        {
            query.getSingleResult();
            return true;
        }
        catch (final Exception e)
        {
            // TODO
            return false;
        }
    }

    @Override
    public PromiseUser getUser(String username, HashResult hashResult)
            throws NoDbInstanceException
    {
        final Query query = getSession().createQuery("from promise_user where username = :username");
        query.setParameter("username", username);
        try
        {
            final UserEntity entity = (UserEntity) query.getSingleResult();
            if (Arrays.equals(entity.getHashcode(), hashResult.getHash()) && Arrays.equals(entity.getSalt(), hashResult.getSalt()))
            {
                final PromiseUser user = new PromiseUser();
                PromiseUtil.copyAttributeFromEntityToResource(user, entity);
                user.setEmail(entity.getEmail());
                user.setUsername(entity.getUsername());
                final List<String> uriList = new ArrayList<>();
                for (final ScopeEntity each : PromiseUtil.emptyIfNull(entity.getScopeList()))
                {
                    uriList.add(each.getUri());
                }
                user.setScopeUri(uriList);
                return user;
            }
            else
            {
                throw new NoDbInstanceException(PromiseCategory.AA);
            }
        }
        catch (final Exception e)
        {
            // TODO
            throw new NoDbInstanceException(PromiseCategory.AA);
        }
    }

    @Override
    public GetUserListResponse getUserList(int start, int count)
    {
        final List<GetUserResponse> list = list(start, count);
        final GetUserListResponse ret = new GetUserListResponse();
        ret.setStart(start);
        ret.setCount(list.size());
        ret.setMember(list);
        return ret;
    }

}
