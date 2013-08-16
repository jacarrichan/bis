package com.avicit.bis.system.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avicit.bis.system.data.dao.DataDao;
import com.avicit.bis.system.data.entity.Data;
import com.avicit.bis.system.data.service.DataService;


@Service("dataService")
public class DataServiceImpl implements DataService{
	
	@Autowired
	private DataDao<Data, Integer> dataDao;

	@Override
	public List<Data> list() {
		return this.dataDao.find();
	}

}
