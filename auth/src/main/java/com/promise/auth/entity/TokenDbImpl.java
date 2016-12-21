package com.promise.auth.entity;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.promise.common.PromiseToken;
import com.promise.common.PromiseUser;

@Component
public class TokenDbImpl implements TokenDbInterface
{

    private static List<SimpleEntry<PromiseToken, PromiseUser>> db;

    @Override
    public PromiseToken makeAndCacheToken(PromiseUser user)
    {
        final PromiseToken token = new PromiseToken(UUID.randomUUID().toString());
        db.add(new SimpleEntry<>(token, user));
        return token;
    }

}
