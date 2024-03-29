package com.promise.employee.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Name
{
    private String firstName;
    private String lastName;

    public Name()
    {

    }

    public Name(String name)
    {
        firstName = name;
        lastName = "";
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    @Override
    public String toString()
    {
        return firstName + " " + lastName;
    }
}
