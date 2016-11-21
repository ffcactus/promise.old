package com.promise.auth.service;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.promise.common.PromiseToken;
import com.promise.common.PromiseUser;

@Component
@Scope("singleton")
public class TokenServiceImpl implements TokenServiceInterface
{

    private static List<SimpleEntry<PromiseToken, PromiseUser>> db;

    @Override
    public PromiseToken getToken(PromiseUser user)
    {
        final PromiseToken token = new PromiseToken(UUID.randomUUID().toString());
        db.add(new SimpleEntry<>(token, user));
        return token;
    }

    @Override
    public PromiseUser getUser(PromiseToken token)
    {
        PromiseUser user = null;
        for (final SimpleEntry<PromiseToken, PromiseUser> each : db)
        {
            if (each.getKey().equals(token))
            {
                user = each.getValue();
            }
        }
        return user;
    }

}
