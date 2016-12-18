package com.promise.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.promise.common.exception.NoDbInstanceException;
import com.promise.employee.dao.EmployeeDaoInterface;
import com.promise.employee.dto.GetEmployeeResponse;
import com.promise.employee.dto.PostEmployeeRequest;
import com.promise.employee.dto.PostEmployeeResponse;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeServiceInterface
{

    @Autowired
    private EmployeeDaoInterface dao;

    @Override
    public PostEmployeeResponse postEmployee(PostEmployeeRequest request)
    {
        return dao.postEmployee(request);
    }

    @Override
    public GetEmployeeResponse getEmployee(String id)
            throws NoDbInstanceException
    {
        return dao.getEmployee(id);
    }

}
