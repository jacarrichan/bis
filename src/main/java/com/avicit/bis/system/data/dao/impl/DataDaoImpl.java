package com.avicit.bis.system.data.dao.impl;

import org.springframework.stereotype.Repository;


import com.avicit.bis.system.data.dao.DataDao;
import com.avicit.bis.system.data.entity.Data;
import com.avicit.framework.support.dao.hibernate.impl.HibernateDaoSupport;

@Repository("dataDao")
public class DataDaoImpl extends HibernateDaoSupport<Data, Integer> implements DataDao<Data, Integer>{

}
