package com.avicit.bis.system.role.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.avicit.bis.system.role.entity.Role;
import com.avicit.bis.system.role.service.RoleService;
import com.avicit.framework.util.ResponseUtils;

@Controller
@RequestMapping(value="roles")
public class RoleController {
	
	@Autowired
	private RoleService roleService;

	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody Object list(){
		return ResponseUtils.sendPagination(roleService.list()) ;
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	public @ResponseBody Object update(@RequestBody Role role) throws Exception{
		this.roleService.update(role);
		return ResponseUtils.sendSuccess("保存成功");
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody Object save(@RequestBody Role role) throws Exception{
		return ResponseUtils.sendSuccess("保存成功",this.roleService.save(role));
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.DELETE)
	public @ResponseBody Object delete(@RequestBody Role role) throws Exception{
		this.roleService.delete(role);
		return ResponseUtils.sendSuccess("删除成功");
	}
	
}
