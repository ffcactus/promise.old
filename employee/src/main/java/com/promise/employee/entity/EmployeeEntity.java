package com.promise.employee.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "employee")
@Table(name = "employee")
public class EmployeeEntity
{
    //    @Id
    //    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //    @org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
    //    @Column(name = "id")

    @javax.persistence.Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @org.hibernate.annotations.Type(type = "pg-uuid")
    private UUID id;

    @Column(name = "\"name\"")
    private String name;

    public EmployeeEntity()
    {

    }

    public UUID getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
