package com.avicit.framework.support.dao.hibernate.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.avicit.framework.support.dao.hibernate.HibernateDao;
import com.avicit.framework.support.entity.BaseEntity;
import com.avicit.framework.web.support.pagination.PaginationUtils;

public class HibernateDaoSupport<T, ID extends Serializable> implements
		HibernateDao<T, ID> {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	protected Class<T> getEntityClass() {
		return (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	protected String getEntityName() {
		return getEntityClass().getSimpleName();
	}

	@SuppressWarnings("unchecked")
	public List<T> find() {
		return getSession().createQuery(buildHQLByClass(this.getEntityClass()))
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<T> findWithWhere(String where) {
		return getSession().createQuery(
				buildHQLByClass(this.getEntityClass()) + " where" + where)
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<T> findByHQL(String hql) {
		return getSession().createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	public T get(ID id) {
		return (T) getSession().get(this.getEntityClass(), id);
	}

	public void delete(ID... ids) {
		for (ID id : ids) {
			this.delete(this.get(id));
		}
	}

	@SuppressWarnings("unchecked")
	public ID save(T entity) {
		if (entity instanceof BaseEntity) {
			((BaseEntity) entity).setCreateDate(new Date());
			((BaseEntity) entity).setVersion(0);
			((BaseEntity) entity).setModifyDate(new Date());
			((BaseEntity) entity).setCreateUserId(-1);
			((BaseEntity) entity).setModifyUserId(-1);
		}
		return (ID) getSession().save(entity);
	}

	@Override
	public void delete(List<T> entityList) {
		for (T t : entityList) {
			delete(t);
		}
	}

	@Override
	public void delete(T entity) {
		getSession().delete(entity);
	}

	@Override
	public void update(T entity) {
		if (entity instanceof BaseEntity) {
			((BaseEntity) entity).setModifyDate(new Date());
			((BaseEntity) entity).setModifyUserId(-1);
		}
		getSession().update(entity);
	}

	@Override
	public Object findUniqueBy(Class<?> entityClass, String propertyName,
			Object value) {
		Criteria criteria = getSession().createCriteria(entityClass);
		criteria.add(Restrictions.eq(propertyName, value));
		return criteria.uniqueResult();
	}

	protected Query setParameters(Query query, Object... params) {
		int i = 0;
		for (Object object : params) {
			query.setParameter(i, object);
		}
		return query;
	}

	@SuppressWarnings("unchecked")
	public List<T> findPageByHql(String hql, Object... params) {
		this.getEntityClass();
		if (PaginationUtils.exist() && PaginationUtils.getSorter() != null) {
			hql += " order by " + PaginationUtils.getSorter() + " "
					+ PaginationUtils.getOrder();
		}
		Query query = createHQLQuery(hql, params);

		if (PaginationUtils.exist()) {
			Query countQuery = createHQLQuery("select count(*)"
					+ removeSelect(hql), params);
			int total = Integer.valueOf(countQuery.uniqueResult().toString());
			PaginationUtils.setTotal(total);

			query.setFirstResult(PaginationUtils.getStart()).setMaxResults(
					PaginationUtils.getLimit());
		}
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<T> findPageBySql(String sql, Object... params) {
		if (PaginationUtils.exist()) {
			sql += " order by " + PaginationUtils.getSorter() + " "
					+ PaginationUtils.getOrder();
		}
		SQLQuery query = createSQLQuery(sql, params);

		if (PaginationUtils.exist()) {

			SQLQuery countQuery = createSQLQuery("select count(1)"
					+ removeSelect(sql), params);
			int total = Integer.valueOf(countQuery.uniqueResult().toString());
			PaginationUtils.setTotal(total);

			query.setFirstResult(PaginationUtils.getStart()).setMaxResults(
					PaginationUtils.getLimit());
		}
		return query.list();
	}

	public SQLQuery createSQLQuery(String sql, Object... params) {
		return (SQLQuery) setParameters(getSession().createSQLQuery(sql), params);
	}

	public Query createHQLQuery(String hql, Object... params) {
		return setParameters(getSession().createQuery(hql),params);
	}

	public static String buildHQLByClass(Class<?> clazz) {
		return "from " + clazz.getSimpleName() + " t";
	}

	public static String removeSelect(String str) {
		int pos = str.toLowerCase().indexOf("from");
		if (pos != -1) {
			str.substring(pos);
		}
		return str;
	}
	
}
