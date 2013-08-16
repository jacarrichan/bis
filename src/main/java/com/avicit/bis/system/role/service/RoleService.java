package com.avicit.bis.system.role.service;

import java.util.List;

import com.avicit.bis.system.role.entity.Role;
import com.avicit.bis.system.role.vo.RoleVo;


public interface RoleService {

	public List<RoleVo> list();
	
	public void update(Role role) throws Exception;
	
	public Integer save(Role role) throws Exception;
	
	public void delete(Role role) throws Exception;

}
