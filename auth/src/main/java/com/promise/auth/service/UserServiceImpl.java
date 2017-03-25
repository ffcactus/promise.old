package com.promise.auth.service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.promise.auth.dao.UserDaoInterface;
import com.promise.auth.sdk.dto.CreateUserRequest;
import com.promise.auth.sdk.dto.GetUserListResponse;
import com.promise.auth.sdk.dto.GetUserResponse;
import com.promise.auth.util.PasswordUtil;
import com.promise.auth.util.PasswordUtil.HashResult;
import com.promise.common.PromiseUser;
import com.promise.common.constant.PromiseCategory;
import com.promise.common.dto.PromiseHttpOperationResponse;
import com.promise.common.exception.DbOperationException;
import com.promise.common.exception.InvalidRequestBodyException;
import com.promise.common.exception.NoDbInstanceException;

@Service("userService")
@Transactional
@DependsOn("authServiceImpl")
public class UserServiceImpl implements UserServiceInterface
{
    @Autowired
    private UserDaoInterface userDao;

    @Autowired
    private PlatformTransactionManager transactionManager;

    private static Logger log = Logger.getLogger(UserServiceImpl.class);

    @PostConstruct
    private void postConstruct()
    {
        new TransactionTemplate(transactionManager).execute(new TransactionCallback<Object>()
        {

            @Override
            public Object doInTransaction(TransactionStatus transactionStatus)
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
                    catch (final InvalidRequestBodyException e)
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
                return null;
            }
        });
    }

    @Override
    public PromiseHttpOperationResponse createUser(CreateUserRequest createUserRequest)
            throws InvalidRequestBodyException
    {
        try
        {
            return userDao.create(createUserRequest);
        }
        catch (final DbOperationException e)
        {
            throw new InvalidRequestBodyException(e, PromiseCategory.AA);
        }
    }

    @Override
    public GetUserResponse getUser(String id)
            throws NoDbInstanceException
    {
        return userDao.get(id);
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
    public PromiseHttpOperationResponse deleteUser(String id)
    {
        return userDao.delete(id);
    }

}
