package com.promise.employee.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "company")
@Table(name = "company")
public class CompanyEntity
{
    @javax.persistence.Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @org.hibernate.annotations.Type(type = "pg-uuid")
    private UUID id;

    @Column(name = "\"name\"")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeEntity> employeeList;

    public CompanyEntity()
    {

    }

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
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

    public List<EmployeeEntity> getEmployeeList()
    {
        return employeeList;
    }

    public void setEmployeeList(List<EmployeeEntity> employeeList)
    {
        this.employeeList = employeeList;
    }
}
