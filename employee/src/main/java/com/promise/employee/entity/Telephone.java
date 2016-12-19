package com.promise.employee.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Telephone
{
    @Column(name = "\"number\"")
    private String number;

    public Telephone()
    {

    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }
}
