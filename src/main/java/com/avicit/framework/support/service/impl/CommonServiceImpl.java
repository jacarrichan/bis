package com.avicit.framework.support.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.avicit.framework.constants.Constants;
import com.avicit.framework.support.dao.hibernate.ICommonDao;
import com.avicit.framework.support.entity.BaseEntity;
import com.avicit.framework.support.service.CommonService;
import com.avicit.framework.web.support.pagination.Page;
import com.avicit.framework.web.support.pagination.PageUtil;

import java.io.Serializable;
import java.util.List;

@Service("CommonService")
public class CommonServiceImpl implements CommonService {
    
    @Autowired
    @Qualifier("CommonHibernateDao")
    private ICommonDao commonDao;


    public <T extends BaseEntity> T save(T model) {
        return commonDao.save(model);
    }

    public <T extends BaseEntity> void saveOrUpdate(T model) {
        commonDao.saveOrUpdate(model);
        
    }
    
    public <T extends BaseEntity> void update(T model) {
        commonDao.update(model);
    }
    
    public <T extends BaseEntity> void merge(T model) {
        commonDao.merge(model);
    }

    public <T extends BaseEntity, PK extends Serializable> void delete(Class<T> entityClass, PK id) {
        commonDao.delete(entityClass, id);
    }

    public <T extends BaseEntity> void deleteObject(T model) {
        commonDao.deleteObject(model);
    }

    public <T extends BaseEntity, PK extends Serializable> T get(Class<T> entityClass, PK id) {
        return commonDao.get(entityClass, id);
        
    }
    
    public <T extends BaseEntity> int countAll(Class<T> entityClass) {
        return commonDao.countAll(entityClass);
    }
    
    public <T extends BaseEntity> List<T> listAll(Class<T> entityClass) {
        return commonDao.listAll(entityClass);
    }
    
    public <T extends BaseEntity> Page<T> listAll(Class<T> entityClass, int pn) {
        return listAll(entityClass, pn, Constants.DEFAULT_PAGE_SIZE);
    }
    
    public <T extends BaseEntity> Page<T> listAll(Class<T> entityClass, int pn, int pageSize) {
        int total = countAll(entityClass);
        List<T> items = commonDao.listAll(entityClass, pn, pageSize);
        return PageUtil.getPage(total, pn, items, pageSize);
    }

}
