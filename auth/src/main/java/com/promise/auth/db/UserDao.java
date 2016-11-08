package com.promise.auth.db;

/**
 * Represent the User information in database.
 *
 */
public class UserDao
{

    private String id;
    private String username;
    private String email;
    private byte[] hashcode;
    private byte[] salt;
    private String scopeUri;

    public UserDao()
    {

    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public byte[] getHashcode()
    {
        return hashcode;
    }

    public void setHash(byte[] hashcode)
    {
        this.hashcode = hashcode;
    }

    public byte[] getSalt()
    {
        return salt;
    }

    public void setSalt(byte[] salt)
    {
        this.salt = salt;
    }

    public String getScopeUri()
    {
        return scopeUri;
    }

    public void setScopeUri(String scopeUri)
    {
        this.scopeUri = scopeUri;
    }
}
