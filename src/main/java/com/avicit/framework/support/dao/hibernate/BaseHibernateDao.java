package com.avicit.framework.support.dao.hibernate;

import org.hibernate.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.avicit.framework.support.dao.util.ConditionQuery;
import com.avicit.framework.support.dao.util.OrderBy;
import com.avicit.framework.util.Assert;
import com.avicit.framework.web.support.pagination.PageUtil;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.Map.Entry;

/**
 * 
 * @author Zhang Kaitao
 *
 * @version 1.0, 2010-8-12
 */
public abstract class BaseHibernateDao<M extends java.io.Serializable, PK extends java.io.Serializable> implements IBaseDao<M, PK> {

    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseHibernateDao.class);

    private final Class<M> entityClass;
    private final String HQL_LIST_ALL;
    private final String HQL_COUNT_ALL;
    private final String HQL_OPTIMIZE_PRE_LIST_ALL;
    private final String HQL_OPTIMIZE_NEXT_LIST_ALL;
    private String pkName = null;

    @SuppressWarnings("unchecked")
    public BaseHibernateDao() {
        this.entityClass = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Field[] fields = this.entityClass.getDeclaredFields();
        for(Field f : fields) {
            if(f.isAnnotationPresent(Id.class)) {
                this.pkName = f.getName();
            }
        }
        
        Assert.notNull(pkName);
        //TODO @Entity name not null
        HQL_LIST_ALL = "from " + this.entityClass.getSimpleName() + " order by " + pkName + " desc";
        HQL_OPTIMIZE_PRE_LIST_ALL = "from " + this.entityClass.getSimpleName() + " where " + pkName + " > ? order by " + pkName + " asc";
        HQL_OPTIMIZE_NEXT_LIST_ALL = "from " + this.entityClass.getSimpleName() + " where " + pkName + " < ? order by " + pkName + " desc";
        HQL_COUNT_ALL = " select count(*) from " + this.entityClass.getSimpleName();
    }
        
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    public Session getSession() {
        //事务必须是开启的(Required)，否则获取不到
        return sessionFactory.getCurrentSession();
    }

   
    
    @SuppressWarnings("unchecked")
    @Override
    public PK save(M model) {
        return (PK) getSession().save(model);
    }

    @Override
    public void saveOrUpdate(M model) {
        getSession().saveOrUpdate(model);
    }

    @Override
    public void update(M model) {
        getSession().update(model);

    }

    @Override
    public void merge(M model) {
        getSession().merge(model);
    }

    @Override
    public void delete(PK id) {
        getSession().delete(this.get(id));

    }

    @Override
    public void deleteObject(M model) {
        getSession().delete(model);

    }

    @Override
    public boolean exists(PK id) {
        return get(id) != null;
    }

    @SuppressWarnings("unchecked")
    public M get(PK id) {
        return (M) getSession().get(this.entityClass, id);
    }

    @Override
    public int countAll() {
        Long total = aggregate(HQL_COUNT_ALL);
        return total.intValue();
    }


    @Override
    public List<M> listAll() {
        return list(HQL_LIST_ALL);
    }

    @Override
    public List<M> listAll(int pn, int pageSize) {
        return list(HQL_LIST_ALL, pn, pageSize);
    }
    
    @Override
    public List<M> pre(PK pk, int pn, int pageSize) {
        if(pk == null) {
            return list(HQL_LIST_ALL, pn, pageSize);
        }
        //倒序，重排
        List<M> result = list(HQL_OPTIMIZE_PRE_LIST_ALL, 1, pageSize, pk);
        Collections.reverse(result);
        return result;
    }
    @Override
    public List<M> next(PK pk, int pn, int pageSize) {
        if(pk == null) {
            return list(HQL_LIST_ALL, pn, pageSize);
        }
        return list(HQL_OPTIMIZE_NEXT_LIST_ALL, 1, pageSize, pk);
    }

    @Override
    public void flush() {
        getSession().flush();
    }

    @Override
    public void clear() {
        getSession().clear();
    }

    protected long getIdResult(String hql, Object... paramlist) {
        long result = -1;
        List<?> list = list(hql, paramlist);
        if (list != null && list.size() > 0) {
            return ((Number) list.get(0)).longValue();
        }
        return result;
    }

    protected List<M> listSelf(final String hql, final int pn, final int pageSize, final Object... paramlist) {
        return this.<M> list(hql, pn, pageSize, paramlist);
    }


    /**
     * for in
     */
    @SuppressWarnings("unchecked")
    protected <T> List<T> listWithIn(final String hql,final int start, final int length, final Map<String, Collection<?>> map, final Object... paramlist) {
        Query query = getSession().createQuery(hql);
        setParameters(query, paramlist);
        for (Entry<String, Collection<?>> e : map.entrySet()) {
            query.setParameterList(e.getKey(), e.getValue());
        }
        if (start > -1 && length > -1) {
            query.setMaxResults(length);
            if (start != 0) {
                query.setFirstResult(start);
            }
        }
        List<T> results = query.list();
        return results;
    }

    @SuppressWarnings("unchecked")
    protected <T> List<T> list(final String hql, final int pn, final int pageSize, final Object... paramlist) {
        Query query = getSession().createQuery(hql);
        setParameters(query, paramlist);
        if (pn > -1 && pageSize > -1) {
            query.setMaxResults(pageSize);
            int start = PageUtil.getPageStart(pn, pageSize);
            if (start != 0) {
                query.setFirstResult(start);
            }
        }
        if (pn < 0) {
            query.setFirstResult(0);
        }
        List<T> results = query.list();
        return results;
    }

    /**
     * 根据查询条件返回唯一一条记录
     */
    @SuppressWarnings("unchecked")
    protected <T> T unique(final String hql, final Object... paramlist) {
        Query query = getSession().createQuery(hql);
        setParameters(query, paramlist);
        return (T) query.setMaxResults(1).uniqueResult();
    }

       /**
        * 
        * for in
        */
    @SuppressWarnings("unchecked")
    protected <T> T aggregate(final String hql, final Map<String, Collection<?>> map, final Object... paramlist) {
        Query query = getSession().createQuery(hql);
        if (paramlist != null) {
            setParameters(query, paramlist);
            for (Entry<String, Collection<?>> e : map.entrySet()) {
                query.setParameterList(e.getKey(), e.getValue());
            }
        }

        return (T) query.uniqueResult();
    }
        
    @SuppressWarnings("unchecked")
    protected <T> T aggregate(final String hql, final Object... paramlist) {
        Query query = getSession().createQuery(hql);
        setParameters(query, paramlist);

        return (T) query.uniqueResult();

    }


    /**
     * 执行批处理语句.如 之间insert, update, delete 等.
     */
    protected int execteBulk(final String hql, final Object... paramlist) {
        Query query = getSession().createQuery(hql);
        setParameters(query, paramlist);
        Object result = query.executeUpdate();
        return result == null ? 0 : ((Integer) result).intValue();
    }
    
    protected int execteNativeBulk(final String natvieSQL, final Object... paramlist) {
        Query query = getSession().createSQLQuery(natvieSQL);
        setParameters(query, paramlist);
        Object result = query.executeUpdate();
        return result == null ? 0 : ((Integer) result).intValue();
    }

    protected <T> List<T> list(final String sql, final Object... paramlist) {
        return list(sql, -1, -1, paramlist);
    }
        
    @SuppressWarnings("unchecked")
    public <T> List<T> listByNative(final String nativeSQL, final int pn, final int pageSize,
            final List<Entry<String, Class<?>>> entityList, 
            final List<Entry<String, Type>> scalarList, final Object... paramlist) {

        SQLQuery query = getSession().createSQLQuery(nativeSQL);
        if (entityList != null) {
            for (Entry<String, Class<?>> entity : entityList) {
                query.addEntity(entity.getKey(), entity.getValue());
            }
        }
        if (scalarList != null) {
            for (Entry<String, Type> entity : scalarList) {
                query.addScalar(entity.getKey(), entity.getValue());
            }
        }

        setParameters(query, paramlist);

        if (pn > -1 && pageSize > -1) {
            query.setMaxResults(pageSize);
            int start = PageUtil.getPageStart(pn, pageSize);
            if (start != 0) {
                query.setFirstResult(start);
            }
        }
        List<T> result = query.list();
        return result;
    }
        
    @SuppressWarnings("unchecked")
    protected <T> T aggregateByNative(final String natvieSQL, final List<Entry<String, Type>> scalarList, final Object... paramlist) {
        SQLQuery query = getSession().createSQLQuery(natvieSQL);
        if (scalarList != null) {
            for (Entry<String, Type> entity : scalarList) {
                query.addScalar(entity.getKey(), entity.getValue());
            }
        }

        setParameters(query, paramlist);

        Object result = query.uniqueResult();
        return (T) result;
    }
        
    @SuppressWarnings("unchecked")
    public <T> List<T> list(ConditionQuery query, OrderBy orderBy, final int pn, final int pageSize) {
        Criteria criteria = getSession().createCriteria(this.entityClass);
        query.build(criteria);
        orderBy.build(criteria);
        int start = PageUtil.getPageStart(pn, pageSize);
        if(start != 0) {
            criteria.setFirstResult(start);
        }
        criteria.setMaxResults(pageSize);
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> list(Criteria criteria) {
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    public <T> T unique(Criteria criteria) {
        return (T) criteria.uniqueResult();
    }

    public <T> List<T> list(DetachedCriteria criteria) {
        return list(criteria.getExecutableCriteria(getSession()));
    }

    @SuppressWarnings("unchecked")
    public <T> T unique(DetachedCriteria criteria) {
        return (T) unique(criteria.getExecutableCriteria(getSession()));
    }

    protected void setParameters(Query query, Object[] paramlist) {
        if (paramlist != null) {
            for (int i = 0; i < paramlist.length; i++) {
                if(paramlist[i] instanceof Date) {
                    //TODO 难道这是bug 使用setParameter不行？？
                    query.setTimestamp(i, (Date)paramlist[i]);
                } else {
                    query.setParameter(i, paramlist[i]);
                }
            }
        }
    }

        
}
