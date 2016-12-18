package com.promise.employee.dao;

import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.promise.common.constant.PromiseCategory;
import com.promise.common.exception.NoDbInstanceException;
import com.promise.employee.dto.GetEmployeeResponse;
import com.promise.employee.dto.PostEmployeeRequest;
import com.promise.employee.dto.PostEmployeeResponse;
import com.promise.employee.entity.EmployeeEntity;

@Repository("employeeDao")
public class EmployeeDaoImpl implements EmployeeDaoInterface
{

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public PostEmployeeResponse postEmployee(PostEmployeeRequest request)
    {
        final EmployeeEntity entity = new EmployeeEntity();
        entity.setName(request.getName());
        getSession().persist(entity);
        final PostEmployeeResponse response = new PostEmployeeResponse();
        response.setId(entity.getId().toString());
        response.setName(entity.getName());
        return response;
    }

    @Override
    public GetEmployeeResponse getEmployee(String id)
            throws NoDbInstanceException
    {
        final EmployeeEntity entity = getSession().get(EmployeeEntity.class, UUID.fromString(id));
        if (entity == null)
        {
            throw new NoDbInstanceException(PromiseCategory.USER);
        }
        else
        {
            final GetEmployeeResponse response = new GetEmployeeResponse();
            response.setId(entity.getId().toString());
            response.setName(entity.getName());
            return response;
        }
    }
}
