package com.promise.auth.service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.promise.auth.dao.UserDaoInterface;
import com.promise.auth.sdk.dto.CreateUserRequest;
import com.promise.auth.sdk.dto.CreateUserResponse;
import com.promise.auth.sdk.dto.GetUserListResponse;
import com.promise.auth.sdk.dto.GetUserResponse;
import com.promise.auth.util.PasswordUtil;
import com.promise.auth.util.PasswordUtil.HashResult;
import com.promise.common.PromiseUser;
import com.promise.common.exception.NoDbInstanceException;

@Component
@Scope("singleton")
@Transactional
@DependsOn("authServiceImpl")
public class UserServiceImpl implements UserServiceInterface//, InitializingBean
{
    @Autowired
    private UserDaoInterface userDao;

    private static Logger log = Logger.getLogger(UserServiceImpl.class);

    @PostConstruct
    private void postConstruct()
    {
        if (!userDao.isUsernameExist("Administrator"))
        {
            final CreateUserRequest userDto = new CreateUserRequest();
            userDto.setUsername("Administrator");
            userDto.setPassword("admin".toCharArray());
            try
            {
                createUser(userDto);
            }
            catch (final NoDbInstanceException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (final NoSuchAlgorithmException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            log.warn("User Administrator is created.");
        }
        else
        {
            log.info("User Administrator already exist.");
        }
    }

    @Override
    public CreateUserResponse createUser(CreateUserRequest createUserRequest)
            throws NoDbInstanceException, NoSuchAlgorithmException
    {
        return userDao.createUser(createUserRequest);

    }

    @Override
    public GetUserResponse getUser(String id)
            throws NoDbInstanceException
    {
        return userDao.getUser(id);
    }

    @Override
    public PromiseUser getUser(String username, char[] password)
            throws NoSuchAlgorithmException, NoDbInstanceException
    {
        final HashResult hashResult = PasswordUtil.hashPassword(password);
        return userDao.getUser(username, hashResult);
    }

    @Override
    public GetUserListResponse getUserList(Optional<Integer> start, Optional<Integer> count)
    {
        return userDao.getUserList(
                start.isPresent() ? start.get() : 0,
                count.isPresent() ? count.get() : 0);
    }

    @Override
    public void deleteUser(String id)
            throws NoDbInstanceException
    {
        userDao.deleteUser(id);
    }

}
