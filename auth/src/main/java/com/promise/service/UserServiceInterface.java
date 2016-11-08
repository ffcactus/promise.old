package com.promise.service;

import com.promise.auth.Token;
import com.promise.auth.dto.UserDto;

public interface UserInterface
{
    public void createUser(UserDto user);

    public UserDto getUser(Token token);

    public UserDto getUser(String username, char[] password);
}
