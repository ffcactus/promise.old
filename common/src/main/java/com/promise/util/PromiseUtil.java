package com.promise.util;

import java.util.UUID;

public class PromiseUtil
{
    public static String avoidNull(String input)
    {
        if (input == null)
        {
            return "";
        }
        else
        {
            return input;
        }
    }

    public static UUID getIdFromUri(String uri)
    {
        final String[] parts = uri.split("/");
        if (parts.length == 3)
        {
            return UUID.fromString(parts[2]);
        }
        else
        {
            return null;
        }
    }
}
