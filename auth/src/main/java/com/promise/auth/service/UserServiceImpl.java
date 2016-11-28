package com.promise.auth.service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.promise.auth.db.UserDao;
import com.promise.auth.db.UserDatabaseInterface;
import com.promise.auth.sdk.dto.CreateUserRequest;
import com.promise.auth.sdk.dto.CreateUserResponse;
import com.promise.auth.sdk.dto.GetUserListResponse;
import com.promise.auth.sdk.dto.GetUserResponse;
import com.promise.auth.util.PasswordUtil;
import com.promise.auth.util.PasswordUtil.HashResult;
import com.promise.common.PromiseToken;
import com.promise.common.PromiseUser;
import com.promise.common.exception.NoDBInstanceException;

@Component
@Scope("singleton")
public class UserServiceImpl implements UserServiceInterface, InitializingBean
{
    @Autowired
    private UserDatabaseInterface userDatabase;

    private static Logger log = Logger.getLogger(UserServiceImpl.class);

    @Override
    public CreateUserResponse createUser(CreateUserRequest userDto)
    {
        try
        {
            final UserDao userDao = userDatabase.createUser(UserDao.makeInstance(userDto));
            return UserDao.toDto(userDao);
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
    {
        return null;
    }

    @Override
    public GetUserResponse getUser(PromiseToken token)
    {
        return null;
    }

    @Override
    public PromiseUser getUser(String username, char[] password)
            throws NoSuchAlgorithmException, NoDBInstanceException
    {
        final HashResult hashResult = PasswordUtil.hashPassword(password);
        final UserDao userDao = userDatabase.getUser(username, hashResult);
        return dao2GetUserResponseDto(userDao);
    }

    @Override
    public GetUserListResponse getUserList(int start, int count)
    {
        final List<UserDao> userDaoList = userDatabase.getUser(start, count);
        final GetUserListResponse ret = new GetUserListResponse();

        // The start point should follow the start point in the request?
        ret.setStart(start);
        ret.setCount(userDaoList.size());
        final List<GetUserResponse> memberList = new ArrayList<>();
        for (final UserDao each : userDaoList)
        {
            final GetUserResponse t = getUser(each.getId());
            if (t != null)
            {
                memberList.add(t);
            }
        }
        ret.setMemberList(memberList);
        return ret;
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

    @Override
    public void afterPropertiesSet()
            throws Exception
    {
        if (userDatabase.isUsernameExist("Administrator"))
        {
            final CreateUserRequest userDto = new CreateUserRequest();
            userDto.setUsername("Administrator");
            userDto.setPassword("admin".toCharArray());
            createUser(userDto);
            log.warn("Administrator is created.");
        }
        else
        {
            log.info("Administrator already exist.");
        }
    }

}
