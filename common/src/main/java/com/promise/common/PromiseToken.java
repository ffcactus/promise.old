package com.promise.common;

/**
 * Tokens can combined together to represents a a combined-token.
 *
 */
public class PromiseToken
{
    private String value;

    public PromiseToken(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }

    public PromiseToken getLocalToken()
    {
        final String[] parts = value.split(":");
        if (parts.length == 1)
        {
            return null;
        }
        else
        {
            return new PromiseToken(parts[0]);
        }
    }

    public void combineLocalToken(PromiseToken localToken)
    {
        value = localToken.getValue() + ":" + value;
    }

    @Override
    public boolean equals(Object other)
    {
        return (other != null && other instanceof PromiseToken && ((PromiseToken) other).getValue().equals(value));
    }
}
