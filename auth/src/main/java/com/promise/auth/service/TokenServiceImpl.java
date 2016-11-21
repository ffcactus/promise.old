package com.promise.auth.service;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.UUID;

import com.promise.common.PromiseToken;
import com.promise.common.PromiseUser;

public class TokenServiceImpl implements TokenServiceInterface
{

    private static List<SimpleEntry<PromiseToken, PromiseUser>> db;
    
    @Override
    public PromiseToken getToken(PromiseUser user)
    {
        PromiseToken token = new PromiseToken(UUID.randomUUID().toString());
        db.add(new SimpleEntry<PromiseToken, PromiseUser>(token, user));
        return token;
    }

    @Override
    public PromiseUser getUser(PromiseToken token)
    {
        PromiseUser user = null;
        for(SimpleEntry<PromiseToken, PromiseUser> each : db)
        {
            if (each.getKey().equals(token))
            {
                user = each.getValue();
            }
        }
        return user;
    }

}
