package com.promise.auth;

public class Token
{
    private final String id;

    public Token(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }

    public static Token generateToken(LoginDto loginDto)
    {
        return new Token(loginDto.getUserName() + '@' + loginDto.getPassword().toString() + "@" + loginDto.getDomain());
    }
}
