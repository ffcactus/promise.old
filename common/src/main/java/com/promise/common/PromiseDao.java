package com.promise.common;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.promise.common.constant.PromiseCategory;
import com.promise.common.exception.DbOperationException;
import com.promise.common.exception.NoDbInstanceException;
import com.promise.util.PromiseUtil;

public abstract class PromiseDao<E, C, G> implements PromiseDaoInterface<C, G>
{
    private final Class<E> entityType;
    private final PromiseCategory category;

    @Autowired
    protected SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public PromiseDao(PromiseCategory category)
    {
        this.entityType = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.category = category;
    }

    protected Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

    public abstract E fromCreateRequestToEntity(C request)
            throws DbOperationException;

    public abstract G fromEntityToGetResponse(E entity);

    @Override
    public G create(C request)
            throws DbOperationException
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

    @Override
    public List<G> list(int start, int count)
    {
        final CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        final CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entityType);
        final Root<E> from = criteriaQuery.from(entityType);
        final CriteriaQuery<E> select = criteriaQuery.select(from);
        final TypedQuery<E> typedQuery = getSession().createQuery(select);
        typedQuery.setFirstResult(start);
        typedQuery.setMaxResults(count);
        final List<E> entityList = typedQuery.getResultList();

        final List<G> ret = new ArrayList<>();
        for (final E each : PromiseUtil.emptyIfNull(entityList))
        {
            ret.add(fromEntityToGetResponse(each));
        }
        return ret;
    }

}
