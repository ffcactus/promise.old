package com.promise.employee.dto;

import java.util.List;

public class GetCompanyResponse
{
    private String id;
    private String name;
    private List<String> employeeIdList;

    public GetCompanyResponse()
    {

    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<String> getEmployeeIdList()
    {
        return employeeIdList;
    }

    public void setEmployeeIdList(List<String> employeeIdList)
    {
        this.employeeIdList = employeeIdList;
    }
}
