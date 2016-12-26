package com.promise.util;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.promise.common.PromiseEntity;
import com.promise.common.PromiseResource;

public class PromiseUtil
{
    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss z");

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
        if (parts.length == 4)
        {
            return UUID.fromString(parts[3]);
        }
        else
        {
            return null;
        }
    }

    public static <T> List<T> emptyIfNull(List<T> other)
    {
        return other == null ? Collections.emptyList() : other;
    }

    public static String dateToString(Date date)
    {
        if (date == null)
        {
            return null;
        }
        else
        {
            return sdf.format(date);
        }
    }

    public static void copyAttributeFromEntityToResource(PromiseResource to, PromiseEntity from)
    {
        to.setId(from.getId().toString());
        to.setCategory(from.getCategory());
        to.setUri(from.getUri());
    }

}
