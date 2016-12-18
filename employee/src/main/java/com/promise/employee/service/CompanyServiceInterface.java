package com.promise.employee.service;

import com.promise.common.exception.NoDbInstanceException;
import com.promise.employee.dto.GetCompanyResponse;
import com.promise.employee.dto.PostCompanyEmployeeRequest;
import com.promise.employee.dto.PostCompanyRequest;
import com.promise.employee.dto.PostCompanyResponse;

public interface CompanyServiceInterface
{

    /**
     * Create a company.
     *
     * @param request The DTO that represents the company to create.
     * @return The DTO that represents the company created.
     */
    public PostCompanyResponse postCompany(PostCompanyRequest request);

    /**
     * Get a company.
     *
     * @param id The ID of the company.
     * @return The DTO that represents the company get.
     * @throws NoDbInstanceException If the company can't be found by the ID.
     */
    public GetCompanyResponse getCompany(String id)
            throws NoDbInstanceException;

    public void postCompanyEmployee(String companyId, PostCompanyEmployeeRequest request)
            throws NoDbInstanceException;

}
