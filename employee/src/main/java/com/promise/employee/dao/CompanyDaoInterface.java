package com.promise.employee.dao;

import com.promise.common.exception.NoDbInstanceException;
import com.promise.employee.dto.GetCompanyResponse;
import com.promise.employee.dto.PostCompanyEmployeeRequest;
import com.promise.employee.dto.PostCompanyRequest;
import com.promise.employee.dto.PostCompanyResponse;

public interface CompanyDaoInterface
{
    /**
     * Create a Company.
     *
     * @param request The DTO that represents the Company to create.
     * @return The DTO that represents the Company created.
     */
    public PostCompanyResponse postCompany(PostCompanyRequest request);

    /**
     * Get a Company.
     *
     * @param id The ID of the Company.
     * @return The DTO that represents the Company get.
     * @throws NoDbInstanceException If the task can't be found by the ID.
     */
    public GetCompanyResponse getCompany(String id)
            throws NoDbInstanceException;

    public void postComanyEmployee(String companyId, PostCompanyEmployeeRequest request);
}
