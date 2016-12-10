package com.promise.auth.db;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

import com.promise.auth.sdk.dto.CreateUserRequest;
import com.promise.auth.util.PasswordUtil;
import com.promise.auth.util.PasswordUtil.HashResult;
import com.promise.common.PromiseResource;
import com.promise.common.PromiseUser;
import com.promise.common.constant.PromiseCategory;

/**
 * Represent the User information in database.
 *
 */
public class UserDao extends PromiseResource
{
    private String username;
    private String email;
    private byte[] hashcode;
    private byte[] salt;
    private List<String> scopeUri;

    public UserDao()
    {

    }

    /**
     * Make an instance of UserDao in which the ID, category and URI already
     * set.
     *
     * @return
     */
    public static UserDao makeInstance()
    {
        final UserDao ret = new UserDao();
        ret.setId(UUID.randomUUID().toString());
        ret.setCategory(PromiseCategory.USER);
        PromiseResource.makeUri(ret);
        return ret;
    }

    /**
     * Make an instance of ScopeDao from DTO object.
     *
     * @param dto
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static UserDao makeInstance(CreateUserRequest dto)
            throws NoSuchAlgorithmException
    {
        final UserDao ret = makeInstance();
        ret.setUsername(dto.getUsername());
        ret.setEmail(dto.getEmail());
        ret.setScopeUri(dto.getScopeUri());
        final HashResult hash = PasswordUtil.hashPassword(dto.getPassword());
        ret.setHash(hash.getHash());
        ret.setSalt(hash.getSalt());

        return ret;
    }

    /**
     * Convert to PromiseUser.
     *
     * @param dao The DAO object.
     * @return PromiseUser object.
     */
    public static PromiseUser toPromiseUser(UserDao dao)
    {
        final PromiseUser ret = new PromiseUser();

        PromiseResource.attributeCopy(ret, dao);

        ret.setUsername(dao.getUsername());
        ret.setEmail(dao.getEmail());
        ret.setScopeUri(dao.getScopeUri());

        return ret;
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

    public List<String> getScopeUri()
    {
        return scopeUri;
    }

    public void setScopeUri(List<String> scopeUri)
    {
        this.scopeUri = scopeUri;
    }
}
