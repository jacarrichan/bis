package com.avicit.bis.system.resource.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.avicit.bis.system.resource.dao.ResourceDAO;
import com.avicit.bis.system.resource.entity.Resource;
import com.avicit.bis.system.resource.service.ResourceService;
import com.avicit.bis.system.resource.vo.ResourceNode;
import com.avicit.framework.util.ListUtils;

@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {

	protected Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	protected ResourceDAO<Resource, Integer> resourceDAO;

	@Override
	public List<ResourceNode> getRoot() throws Exception {
		return ListUtils.transform(this.resourceDAO.getRootResource(),ResourceNode.class);
	}
	
	public List<ResourceNode> getChildren(Integer id) throws Exception{
		return ListUtils.transform(this.resourceDAO.getChildrenByParent(id),ResourceNode.class);
	}

}
