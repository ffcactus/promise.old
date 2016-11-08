package com.promise.common.constant;

public class PromiseConstant
{

    public static final String URI_HEAD = "/rest/";

    public static String makeUri(PromiseCategory category, String id)
    {
        return URI_HEAD + category + "/" + id;
    }
}
