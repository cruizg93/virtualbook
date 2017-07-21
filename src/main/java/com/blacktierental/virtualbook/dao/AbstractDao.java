package com.blacktierental.virtualbook.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<PK extends Serializable, T>{
	private final Class<T> persistentClass;
	
	@SuppressWarnings("unchecked")
	public AbstractDao(){
		this.persistentClass= (Class<T>)((ParameterizedType)this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[1];
	}
	
	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	public T getByKey(PK key){
		return (T) getSession().get(persistentClass, key);
	}
	
	public void persist(T entity){
		try {
			getSession().persist(entity);
		} catch (Exception e) {
			getSession().merge(entity);
		}
	}
	
	public void delete(T entity){
		getSession().delete(entity);
	}
	
	public void update(T entity) {
        getSession().update(entity);
    }
	
	protected Criteria createEntityCriteria(){
		return getSession().createCriteria(persistentClass);
	}
}
