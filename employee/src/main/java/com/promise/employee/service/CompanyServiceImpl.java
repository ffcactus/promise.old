package com.promise.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.promise.common.exception.NoDbInstanceException;
import com.promise.employee.dao.CompanyDaoInterface;
import com.promise.employee.dto.GetCompanyResponse;
import com.promise.employee.dto.PostCompanyEmployeeRequest;
import com.promise.employee.dto.PostCompanyRequest;
import com.promise.employee.dto.PostCompanyResponse;

@Service("companyService")
@Transactional
public class CompanyServiceImpl implements CompanyServiceInterface
{

    @Autowired
    private CompanyDaoInterface dao;

    @Override
    public PostCompanyResponse postCompany(PostCompanyRequest request)
    {
        return dao.postCompany(request);
    }

    @Override
    public GetCompanyResponse getCompany(String id)
            throws NoDbInstanceException
    {
        return dao.getCompany(id);
    }

    @Override
    public void postCompanyEmployee(String companyId, PostCompanyEmployeeRequest request)
            throws NoDbInstanceException
    {
        dao.postComanyEmployee(companyId, request);
    }

}
