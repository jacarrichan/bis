package com.avicit.framework.support.dao.hibernate.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

@Repository
public class GenericHibernateDao<T, ID extends Serializable> extends
		HibernateDaoSupport<T, ID> {

}
