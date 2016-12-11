package com.promise.util;

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
}
