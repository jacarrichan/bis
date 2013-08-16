package com.avicit.bis.system.simple.dao.impl;

import org.springframework.stereotype.Repository;


import com.avicit.bis.system.simple.dao.SimpleDao;
import com.avicit.bis.system.simple.domain.Simple;
import com.avicit.framework.support.dao.hibernate.impl.HibernateDaoSupport;

@Repository("simpleDaoImpl")
public class SimpleDaoImpl extends HibernateDaoSupport<Simple,Integer> implements SimpleDao<Simple,Integer> {


}
