package com.promise.auth.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.promise.common.PromiseEntity;
import com.promise.common.constant.PromiseCategory;

/**
 * Represent the User information in database.
 *
 */
@Entity(name = "promise_user")
@Table(name = "promise_user")
public class UserEntity extends PromiseEntity
{
    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "hashcode")
    private byte[] hashcode;

    @Column(name = "salt")
    private byte[] salt;

    @OneToMany
    private List<ScopeEntity> scopeList;

    public UserEntity()
    {
        setCategory(PromiseCategory.USER);
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

    public List<ScopeEntity> getScopeList()
    {
        return scopeList;
    }

    public void setScopeList(List<ScopeEntity> scopeList)
    {
        this.scopeList = scopeList;
    }
}
