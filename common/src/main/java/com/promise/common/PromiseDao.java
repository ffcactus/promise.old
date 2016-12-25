package com.promise.common;

import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.promise.common.constant.PromiseCategory;
import com.promise.common.exception.NoDbInstanceException;

public abstract class PromiseDao<E, C, G> implements PromiseDaoInterface<C, G>
{
    private final Class<E> entityType;
    private final PromiseCategory category;

    @Autowired
    protected SessionFactory sessionFactory;

    public PromiseDao(Class<E> entityType, PromiseCategory category)
    {
        this.entityType = entityType;
        this.category = category;
    }

    protected Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

    public abstract E fromCreateRequestToEntity(C request);

    public abstract G fromEntityToGetResponse(E entity);

    @Override
    public G create(C request)
    {
        final E entity = fromCreateRequestToEntity(request);
        getSession().save(entity);
        getSession().flush();
        return fromEntityToGetResponse(entity);
    }

    @Override
    public G get(String id)
            throws NoDbInstanceException
    {
        E entity;
        try
        {
            entity = getSession().get(entityType, UUID.fromString(id));
        }
        catch (final IllegalArgumentException e)
        {
            throw new NoDbInstanceException(category);
        }

        if (entity == null)
        {
            throw new NoDbInstanceException(category);
        }

        return fromEntityToGetResponse(entity);
    }

    @Override
    public void delete(String id)
            throws NoDbInstanceException
    {
        E entity;
        try
        {
            entity = getSession().get(entityType, UUID.fromString(id));
        }
        catch (final IllegalArgumentException e)
        {
            throw new NoDbInstanceException(category);
        }

        if (entity == null)
        {
            throw new NoDbInstanceException(category);
        }
        getSession().delete(entity);

    }

}
