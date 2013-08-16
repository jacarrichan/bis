package com.avicit.bis.system.resource.service;

import java.util.List;

import com.avicit.bis.system.resource.vo.ResourceNode;


public interface ResourceService {

	public List<ResourceNode> getRoot() throws Exception;
	
	public List<ResourceNode> getChildren(Integer id) throws Exception;
}
