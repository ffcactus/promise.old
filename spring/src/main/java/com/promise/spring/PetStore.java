package com.promise.spring;

public class PetStore
{
    private String name;

    public PetStore()
    {

    }

    public static PetStore createInstance()
    {
        final PetStore ret = new PetStore();
        ret.setName("Store created by static method.");
        return ret;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
