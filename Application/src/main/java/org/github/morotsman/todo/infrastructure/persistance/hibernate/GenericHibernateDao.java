package org.github.morotsman.todo.infrastructure.persistance.hibernate;


import org.github.morotsman.todo.model.repository.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;


//Generic repository as described in the book Java persistence with Hibernate page 709-714
public abstract class GenericHibernateDao<T,ID extends Serializable> implements GenericDao<T,ID> {

    private Class<T> persistentClass;
    private SessionFactory sessionFactory;

    public GenericHibernateDao(){
        this.persistentClass =
                (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Class<T> getPersistentClass(){
        return persistentClass;
    }

    @SuppressWarnings("unchecked")
    public T findById(ID id,boolean lock){
        if(lock){
            //TODO replace LockMode.UPGRADE with non deprecated
            return (T)getSession().load(getPersistentClass(), id, LockMode.UPGRADE);
        } else{
            return (T)getSession().load(getPersistentClass(), id);
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll(){
        return findByCriteria();
    }

    @SuppressWarnings("unchecked")
    public List<T> findByExample(T exampleInstance, String... excludeProperties){
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        Example example = Example.create(exampleInstance);
        for(String exclude: excludeProperties){
            example.excludeProperty(exclude);
        }
        criteria.add(example);
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    public T makePersistent(T entity){
        getSession().saveOrUpdate(entity);
        return entity;
    }

    public void makeTransient(T entity){
        getSession().delete(entity);
    }

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    protected List<T> findByCriteria(Criterion... criterions){
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        for(Criterion criterion:criterions){
            criteria.add(criterion);
        }
        return criteria.list();
    }

    @Resource
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


}
