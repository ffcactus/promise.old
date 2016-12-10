package com.promise.auth.service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.promise.auth.db.UserDao;
import com.promise.auth.db.UserDbInterface;
import com.promise.auth.sdk.dto.CreateUserRequest;
import com.promise.auth.sdk.dto.CreateUserResponse;
import com.promise.auth.sdk.dto.GetUserListResponse;
import com.promise.auth.sdk.dto.GetUserResponse;
import com.promise.auth.util.PasswordUtil;
import com.promise.auth.util.PasswordUtil.HashResult;
import com.promise.common.PromiseToken;
import com.promise.common.PromiseUser;
import com.promise.common.exception.NoDbInstanceException;

@Component
@Scope("singleton")
@DependsOn("authServiceImpl")
public class UserServiceImpl implements UserServiceInterface//, InitializingBean
{
    @Autowired
    private UserDbInterface userDatabase;

    private static Logger log = Logger.getLogger(UserServiceImpl.class);

    @PostConstruct
    private void postConstruct()
    {
        if (!userDatabase.isUsernameExist("Administrator"))
        {
            final CreateUserRequest userDto = new CreateUserRequest();
            userDto.setUsername("Administrator");
            userDto.setPassword("admin".toCharArray());
            createUser(userDto);
            log.warn("User Administrator is created.");
        }
        else
        {
            log.info("User Administrator already exist.");
        }
    }

    @Override
    public CreateUserResponse createUser(CreateUserRequest userDto)
    {
        try
        {
            final UserDao userDao = userDatabase.createUser(UserDao.makeInstance(userDto));
            return CreateUserResponse.makeInstance(UserDao.toPromiseUser(userDao));
        }
        catch (final NoSuchAlgorithmException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public GetUserResponse getUser(String id)
            throws NoDbInstanceException
    {
        final UserDao userDao = userDatabase.getUser(id);
        return GetUserResponse.makeInstance(UserDao.toPromiseUser(userDao));
    }

    @Override
    public GetUserResponse getUser(PromiseToken token)
    {
        return null;
    }

    @Override
    public PromiseUser getUser(String username, char[] password)
            throws NoSuchAlgorithmException, NoDbInstanceException
    {
        final HashResult hashResult = PasswordUtil.hashPassword(password);
        final UserDao userDao = userDatabase.getUser(username, hashResult);
        return dao2GetUserResponseDto(userDao);
    }

    @Override
    public GetUserListResponse getUserList(Optional<Integer> start, Optional<Integer> count)
    {
        final List<UserDao> userDaoList = userDatabase.getUser(
                start.isPresent() ? start.get() : 0,
                count.isPresent() ? count.get() : 0);
        final GetUserListResponse ret = new GetUserListResponse();

        // The start point should follow the start point in the request?
        ret.setStart(start.isPresent() ? start.get() : 0);
        ret.setCount(userDaoList.size());
        final List<GetUserResponse> memberList = new ArrayList<>();
        for (final UserDao each : userDaoList)
        {
            GetUserResponse t;
            try
            {
                t = getUser(each.getId());
                memberList.add(t);
            }
            catch (final NoDbInstanceException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        ret.setMemberList(memberList);
        return ret;
    }

    @Override
    public void deleteUser(String id)
            throws NoDbInstanceException
    {
        userDatabase.deleteUser(id);
    }

    /**
     * Convert DAO to GetUserResponseDto.
     *
     * @param input
     *        UserDao
     * @return GetUserResponseDto
     */
    private PromiseUser dao2GetUserResponseDto(UserDao input)
    {
        final PromiseUser ret = new PromiseUser();
        ret.setUsername(input.getUsername());
        ret.setEmail(input.getEmail());
        ret.setScopeUri(input.getScopeUri());
        ret.setId(input.getId());
        return ret;
    }

}
