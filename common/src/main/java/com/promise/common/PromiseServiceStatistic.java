package com.promise.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PromiseServiceStatistic
{
    private String serviceName;
    private String startTime;

    private Map<String, Long> uriCallCount;

    public PromiseServiceStatistic()
    {
        uriCallCount = new HashMap<String, Long>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss z");
        startTime = sdf.format(new Date());
    }

    public String getServiceName()
    {
        return serviceName;
    }

    public void setServiceName(String serviceName)
    {
        this.serviceName = serviceName;
    }

    public String getStartTime()
    {
        return startTime;
    }

    public Map<String, Long> getUriCallCount()
    {
        return uriCallCount;
    }

    public void setUriCallCount(Map<String, Long> uriCallCount)
    {
        this.uriCallCount = uriCallCount;
    }

    @JsonIgnore
    public void recodeUri(String uri)
    {
        Long count = uriCallCount.get(uri);
        if (count == null)
        {
            uriCallCount.put(uri, 1L);
        }
        else
        {
            uriCallCount.put(uri, count + 1);
        }
    }

}
