package com.avicit.bis.system.role.dao.impl;

import org.springframework.stereotype.Repository;


import com.avicit.bis.system.role.dao.RoleDao;
import com.avicit.bis.system.role.entity.Role;
import com.avicit.framework.support.dao.hibernate.impl.HibernateDaoSupport;

@Repository("roleDao")
public class RoleDaoImpl extends HibernateDaoSupport<Role, Integer> implements RoleDao<Role, Integer> {

	public RoleDaoImpl(){
		this.getEntityClass();
	}
}
