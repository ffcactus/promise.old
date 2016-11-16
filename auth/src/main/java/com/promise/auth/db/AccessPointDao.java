package com.promise.auth.db;

import java.util.UUID;

public class AccessPointDao
{

    private String id;
    private String type;
    private String value;

    public AccessPointDao()
    {

    }

    /**
     * Make an instance that the ID is set.
     * 
     * @return
     */
    public AccessPointDao makeInstance()
    {
        final AccessPointDao ret = new AccessPointDao();
        ret.setId(UUID.randomUUID().toString());
        return ret;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

}
