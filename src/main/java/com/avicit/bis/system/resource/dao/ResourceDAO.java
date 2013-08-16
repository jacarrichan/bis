package com.avicit.bis.system.resource.dao;

import java.util.List;


import com.avicit.bis.system.resource.entity.Resource;
import com.avicit.framework.support.dao.hibernate.HibernateDao;

public interface ResourceDAO<T,ID> extends HibernateDao<T,ID> {

	public List<Resource> getRootResource();
	
	public List<Resource> getChildrenByParent(Integer id);
}
