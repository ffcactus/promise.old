package com.promise.integrationtest.util;

import java.util.Collection;

public class CommonTestUtil
{

    public static <T> boolean collectionEquals(Collection<T> first, Collection<T> second)
    {
        if (first.size() != second.size())
        {
            return false;
        }
        return first.containsAll(second);
    }
}
