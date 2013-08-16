package com.avicit.bis.system.resource.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.avicit.bis.system.resource.service.ResourceService;
import com.avicit.bis.system.resource.vo.ResourceNode;


@Controller
@RequestMapping("/resource")
public class ResourceController {

	@Autowired
	private ResourceService resourceService;
	
	@RequestMapping(value="root",method=RequestMethod.GET)
	public @ResponseBody List<ResourceNode> root() throws Exception{
		return this.resourceService.getRoot();
	}
	
	@RequestMapping(value="child",method=RequestMethod.GET)
	public @ResponseBody List<ResourceNode> child(@RequestParam("id") Integer id) throws Exception{
		return this.resourceService.getChildren(id);
	}
}
