package com.promise.auth.service;

import java.security.NoSuchAlgorithmException;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.promise.auth.db.UserDao;
import com.promise.auth.db.UserDatabaseInterface;
import com.promise.auth.sdk.dto.CreateUserRequest;
import com.promise.auth.sdk.dto.CreateUserResponse;
import com.promise.auth.sdk.dto.GetUserResponse;
import com.promise.auth.util.PasswordUtil;
import com.promise.auth.util.PasswordUtil.HashResult;
import com.promise.common.PromiseToken;
import com.promise.common.PromiseUser;
import com.promise.common.exception.NoDBInstanceException;

@Component(value = "userServiceImpl")
@Scope("singleton")
public class UserServiceImpl implements UserServiceInterface
{

    private static Logger log = Logger.getLogger(UserServiceImpl.class);

    @PostConstruct
    private void postConstruct()
    {
        if (userDatabase.isUsernameExist("Administrator"))
        {
            final CreateUserRequest userDto = new CreateUserRequest();
            userDto.setUsername("Administrator");
            userDto.setPassword("admin".toCharArray());
            this.createUser(userDto);
            log.warn("Administrator is created.");
        }
    }

    @Autowired
    private UserDatabaseInterface userDatabase;

    @Override
    public CreateUserResponse createUser(CreateUserRequest userDto)
    {
        try
        {
            userDatabase.createUser(createUserRequestDto2Dao(userDto));
        }
        catch (final NoSuchAlgorithmException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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

    /**
     * Convert CreateUserRequestDto to DAO
     *
     * @param input
     *        CreateUserRequestDto
     * @return UserDao
     * @throws NoSuchAlgorithmException
     */
    private UserDao createUserRequestDto2Dao(CreateUserRequest input)
            throws NoSuchAlgorithmException
    {
        final UserDao ret = new UserDao();
        ret.setUsername(input.getUsername());
        ret.setEmail(input.getEmail());
        final HashResult hashResult = PasswordUtil.hashPassword(input.getPassword());
        ret.setSalt(hashResult.getSalt());
        ret.setHash(hashResult.getHash());
        ret.setScopeUri(input.getScopeUri());
        ret.setId(null);
        return ret;
    }

}
