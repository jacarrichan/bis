package com.avicit.bis.system.resource.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;


import com.avicit.bis.system.resource.dao.ResourceDAO;
import com.avicit.bis.system.resource.entity.Resource;
import com.avicit.framework.support.dao.hibernate.impl.HibernateDaoSupport;

@Repository("resourceDAO")
public class ResourceDAOImpl extends HibernateDaoSupport<Resource, Integer> implements ResourceDAO<Resource, Integer> {

	@Override
	public List<Resource> getRootResource() {
		return this.findWithWhere(" t.parent is null");
	}

	@Override
	public List<Resource> getChildrenByParent(Integer id) {
		return this.findWithWhere(" t.parent =" + id);
	}

}
