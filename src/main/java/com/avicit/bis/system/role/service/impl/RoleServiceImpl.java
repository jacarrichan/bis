package com.avicit.bis.system.role.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avicit.bis.system.role.dao.RoleDao;
import com.avicit.bis.system.role.entity.Role;
import com.avicit.bis.system.role.service.RoleService;
import com.avicit.bis.system.role.vo.RoleVo;
import com.avicit.framework.util.ListUtils;

@Service("roleServiceImpl")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao<Role, Integer> roleDao;

	public List<RoleVo> list() {
//		HQLParameter p = new HQLParameter(Role.class);
//		System.out.println(p);
		return ListUtils.transform(roleDao.findPageByHql(" from Role "),
				RoleVo.class);
	}

	@Override
	public void update(Role role) throws Exception {
		roleDao.update(role);
	}

	@Override
	public Integer save(Role role) throws Exception {
		return roleDao.save(role);
	}

	@Override
	public void delete(Role role) throws Exception {
		roleDao.delete(role);
	}
}
