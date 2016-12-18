package com.promise.employee.service;

import com.promise.common.exception.NoDbInstanceException;
import com.promise.employee.dto.GetEmployeeResponse;
import com.promise.employee.dto.PostEmployeeRequest;
import com.promise.employee.dto.PostEmployeeResponse;

public interface EmployeeServiceInterface
{

    /**
     * Create a employee.
     *
     * @param request The DTO that represents the employee to create.
     * @return The DTO that represents the employee created.
     */
    public PostEmployeeResponse postEmployee(PostEmployeeRequest request);

    /**
     * Get a employee.
     *
     * @param id The ID of the employee.
     * @return The DTO that represents the employee get.
     * @throws NoDbInstanceException If the employee can't be found by the ID.
     */
    public GetEmployeeResponse getEmployee(String id)
            throws NoDbInstanceException;

}
