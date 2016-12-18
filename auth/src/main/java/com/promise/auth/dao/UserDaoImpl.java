package com.promise.auth.dao;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.promise.auth.entity.UserEntity;
import com.promise.auth.sdk.dto.CreateUserRequest;
import com.promise.auth.sdk.dto.CreateUserResponse;
import com.promise.auth.sdk.dto.GetUserListResponse;
import com.promise.auth.sdk.dto.GetUserResponse;
import com.promise.auth.util.PasswordUtil;
import com.promise.auth.util.PasswordUtil.HashResult;
import com.promise.common.PromiseEntity;
import com.promise.common.PromiseUser;
import com.promise.common.exception.NoDbInstanceException;

@Component
@Scope("singleton")
public class UserDaoImpl implements UserDaoInterface
{
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public boolean isUsernameExist(String username)
    {
        return !getSession().createQuery("from promise_user", UserEntity.class).getResultList().isEmpty();
    }

    @Override
    public CreateUserResponse createUser(CreateUserRequest createUserRequest)
            throws NoSuchAlgorithmException
    {
        final UserEntity entity = new UserEntity();
        entity.setUsername(createUserRequest.getUsername());
        entity.setEmail(createUserRequest.getEmail());
        final HashResult hashResult = PasswordUtil.hashPassword(createUserRequest.getPassword());
        entity.setHash(hashResult.getHash());
        entity.setSalt(hashResult.getSalt());
        entity.setScopeUri(createUserRequest.getScopeUri());
        getSession().persist(entity);
        return null;
    }

    @Override
    public GetUserResponse getUser(String id)
            throws NoDbInstanceException
    {
        final UserEntity entity = getSession().get(UserEntity.class, UUID.fromString(id));
        final GetUserResponse response = new GetUserResponse();
        PromiseEntity.copyAttribute(response, entity);
        response.setUsername(entity.getUsername());
        response.setEmail(entity.getEmail());
        response.setScopeUri(entity.getScopeUri());
        return response;
    }

    @Override
    public PromiseUser getUser(String username, HashResult hashResult)
            throws NoDbInstanceException
    {
        final UserEntity exampleEntity = new UserEntity();
        exampleEntity.setHash(hashResult.getHash());
        exampleEntity.setSalt(hashResult.getSalt());
        getSession().createCriteria(UserEntity.class).add(Example.create(exampleEntity)).list();
        getSession().getCriteriaBuilder().construct(UserEntity.class);
        return null;
    }

    @Override
    public GetUserListResponse getUserList(int start, int count)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteUser(String id)
    {
        // TODO Auto-generated method stub

    }

}
