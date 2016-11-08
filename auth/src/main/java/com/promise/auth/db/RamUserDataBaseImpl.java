package com.promise.auth.db;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.promise.auth.util.PasswordUtil.HashResult;

@Component
@Scope("singleton")
public class RamUserDataBaseImpl implements UserDatabaseInterface
{

    Map<String, UserDao> ramDB = new HashMap<String, UserDao>();

    @Override
    public boolean isUsernameExist(String username)
    {
        for (UserDao user : ramDB.values())
        {
            if (user.getUsername() == username)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public void createUser(UserDao user)
    {
        ramDB.put(UUID.randomUUID().toString(), user);
    }

    @Override
    public UserDao getUser(String username, HashResult hashResult)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
