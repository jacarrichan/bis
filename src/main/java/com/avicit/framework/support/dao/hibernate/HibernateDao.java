package com.avicit.framework.support.dao.hibernate;

import java.util.List;


public interface HibernateDao<T,ID> {
	
	public List<T> find();

	/**
	 * 保存实体类，返回主键ID
	 * @param <ID>
	 * @param <T>
	 * @param entity 实体类
	 * @return
	 */
	public ID save(T entity);
	
	/**
	 * 删除集合
	 * @param <T>
	 * @param entityList
	 */
	public  void delete(List<T> entityList);
	
	/**
	 * 删除实体类
	 * @param <T>
	 * @param entity
	 */
	public void delete(T entity);
	
	/**
	 * 更新实体类
	 * @param <T>
	 * @param entity
	 */
	public void update(T entity);
	
	public T get(ID id);
	
	/**
	 * @param content
	 */
	
	public Object findUniqueBy(Class<?> entityClass, String propertyName,
			Object value);
	
	public List<T> findByHQL(String hql);
	
	public List<T> findWithWhere(String where);
	
	public List<T> findPageByHql(String hql, Object... params);
}
