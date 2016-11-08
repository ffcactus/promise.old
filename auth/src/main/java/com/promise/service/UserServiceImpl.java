package com.promise.service;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.promise.auth.Token;
import com.promise.auth.db.ScopeDao;
import com.promise.auth.db.UserDao;
import com.promise.auth.db.UserDateBaseInterface;
import com.promise.auth.dto.ScopeDto;
import com.promise.auth.dto.UserDto;

public class UserService implements UserInterface
{
    @Autowired
    private UserDateBaseInterface userDatabaseInterface;

    @Override
    public void createUser(UserDto userDto)
    {
        try
        {
            final MessageDigest md = MessageDigest.getInstance("SHA-256");
            // should avoid the creation of String object, because it's immutable.
            md.update(toBytes(userDto.getPassword()));
            userDatabaseInterface.createUser(userDto2Dao(userDto, md.digest(), null));
        }
        catch (final NoSuchAlgorithmException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public UserDto getUser(Token token)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserDto getUser(String username, char[] password)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Convert the char[] to byte[]
     *
     * @param chars
     * @return
     */
    private byte[] toBytes(char[] chars)
    {
        final CharBuffer charBuffer = CharBuffer.wrap(chars);
        final ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(charBuffer);
        final byte[] bytes = Arrays.copyOfRange(
                byteBuffer.array(),
                byteBuffer.position(),
                byteBuffer.limit());
        Arrays.fill(charBuffer.array(), '\u0000'); // clear sensitive data
        Arrays.fill(byteBuffer.array(), (byte) 0); // clear sensitive data
        return bytes;
    }

    /**
     * Convert the user DTO to user DAO
     *
     * @param userDto
     * @param hash
     * @param salt
     * @return
     */
    private UserDao userDto2Dao(UserDto userDto, byte[] hash, byte[] salt)
    {
        final UserDao userDao = new UserDao();
        userDao.setUsername(userDto.getUsername());
        userDao.setEmail(userDto.getEmail());
        userDao.setSalt(salt);
        userDao.setHash(hash);
        final List<ScopeDao> scopeDaoList = new ArrayList<>();
        for (final ScopeDto each : userDto.getScopeList())
        {
            scopeDaoList.add(new ScopeDao(each.getType(), each.getValue()));
        }
        userDao.setScopeList(scopeDaoList);
        userDao.setId(null);
        return userDao;
    }

}
