package com.promise.auth.db;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.promise.auth.util.PasswordUtil.HashResult;
import com.promise.common.constant.PromiseCategory;
import com.promise.common.exception.NoDBInstanceException;

@Component
@Scope("singleton")
public class RamUserDataBaseImpl implements UserDatabaseInterface
{

    Map<String, UserDao> ramDB = new HashMap<>();

    @Override
    public boolean isUsernameExist(String username)
    {
        for (final UserDao user : ramDB.values())
        {
            if (user.getUsername().equals(username))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public UserDao createUser(UserDao user)
    {
        ramDB.put(user.getId(), user);
        return user;
    }

    @Override
    public List<UserDao> getUser(int start, int count)
    {
        final List<UserDao> ret = new ArrayList<>(ramDB.values());
        return ret;
    }

    @Override
    public UserDao getUser(String username, HashResult hashResult)
            throws NoDBInstanceException
    {
        for (final UserDao user : ramDB.values())
        {
            if (user.getUsername().equals(username) && Arrays.equals(user.getHashcode(), hashResult.getHash()))
            {
                return user;
            }
        }
        throw new NoDBInstanceException(PromiseCategory.USER);
    }

    @Override
    public void deleteUser(String id)
            throws NoDBInstanceException
    {
        if (ramDB.containsKey(id))
        {
            ramDB.remove(id);
        }
        else
        {
            throw new NoDBInstanceException(PromiseCategory.USER);
        }

    }

}
