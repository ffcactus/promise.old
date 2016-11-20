package com.promise.common;

/**
 * AccessPoint aggregates the scope object. <br>
 * Examples:<br>
 * { type: "uri", value: "/rest/user" }<br>
 * { type: "scope", value: "/rest/scope/xxxx" }
 *
 */
public class PromiseAccessPoint
{
    public static final String URI = "URI";
    public static final String SCOPE = "SCOPE";
    private String type;
    private String value;

    public PromiseAccessPoint()
    {

    }

    public PromiseAccessPoint(String type, String value)
    {
        this.type = type;
        this.value = value;
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

    @Override
    public boolean equals(Object other)
    {
        return other instanceof PromiseAccessPoint
                && type.equals(((PromiseAccessPoint) other).getType())
                && value.equals(((PromiseAccessPoint) other).getValue());
    }

}
