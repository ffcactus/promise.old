package com.promise.employee.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "employee")
@Table(name = "employee")
public class EmployeeEntity
{
    @javax.persistence.Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @org.hibernate.annotations.Type(type = "pg-uuid")
    private UUID id;

    @Embedded
    private Name name;

    @ElementCollection
    private List<Telephone> telephoneList;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeEntity> others;

    public EmployeeEntity()
    {

    }

    public UUID getId()
    {
        return id;
    }

    public Name getName()
    {
        return name;
    }

    public void setName(Name name)
    {
        this.name = name;
    }

    public List<EmployeeEntity> getOthers()
    {
        return others;
    }

    public void setOthers(List<EmployeeEntity> others)
    {
        this.others = others;
    }
}
