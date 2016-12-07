package com.promise.auth.db;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.UUID;

import com.promise.common.PromiseToken;
import com.promise.common.PromiseUser;

public class TokenDatabaseImpl implements TokenDatabaseInterface
{
    
    private static List<SimpleEntry<PromiseToken, PromiseUser>> db;

    @Override
    public PromiseToken makeAndCacheToken(PromiseUser user)
    {
        PromiseToken token = new PromiseToken(UUID.randomUUID().toString());
        db.add(new SimpleEntry<PromiseToken, PromiseUser>(token, user));
        return token;        
    }

}