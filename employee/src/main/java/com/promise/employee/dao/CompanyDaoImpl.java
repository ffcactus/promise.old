package com.promise.employee.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.promise.common.constant.PromiseCategory;
import com.promise.common.exception.NoDbInstanceException;
import com.promise.employee.dto.GetCompanyResponse;
import com.promise.employee.dto.PostCompanyEmployeeRequest;
import com.promise.employee.dto.PostCompanyRequest;
import com.promise.employee.dto.PostCompanyResponse;
import com.promise.employee.entity.CompanyEntity;
import com.promise.employee.entity.EmployeeEntity;

@Repository("companyDao")
public class CompanyDaoImpl implements CompanyDaoInterface
{

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public PostCompanyResponse postCompany(PostCompanyRequest request)
    {
        final CompanyEntity entity = new CompanyEntity();
        entity.setName(request.getName());
        getSession().persist(entity);
        final PostCompanyResponse response = new PostCompanyResponse();
        response.setId(entity.getId().toString());
        response.setName(entity.getName());
        return response;
    }

    @Override
    public GetCompanyResponse getCompany(String id)
            throws NoDbInstanceException
    {
        final CompanyEntity entity = getSession().get(CompanyEntity.class, UUID.fromString(id));
        if (entity == null)
        {
            throw new NoDbInstanceException(PromiseCategory.USER);
        }
        else
        {
            final GetCompanyResponse response = new GetCompanyResponse();
            response.setId(entity.getId().toString());
            response.setName(entity.getName());
            final List<String> employeeIdList = new ArrayList<>();
            for (final EmployeeEntity employee : entity.getEmployeeList())
            {
                employeeIdList.add(employee.getId().toString());
            }
            response.setEmployeeIdList(employeeIdList);
            return response;
        }
    }

    @Override
    public void postComanyEmployee(String companyId, PostCompanyEmployeeRequest request)
    {
        final CompanyEntity companyEntity = getSession().get(CompanyEntity.class, UUID.fromString(companyId));
        final EmployeeEntity employEntity = getSession().get(EmployeeEntity.class, UUID.fromString(request.getEmployeeId()));
        companyEntity.getEmployeeList().add(employEntity);
        getSession().persist(companyEntity);
    }

}
