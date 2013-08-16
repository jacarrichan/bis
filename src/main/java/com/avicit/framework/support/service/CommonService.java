package com.avicit.framework.support.service;


import java.io.Serializable;
import java.util.List;

import com.avicit.framework.support.entity.BaseEntity;
import com.avicit.framework.web.support.pagination.Page;

public interface CommonService {
    
    public <T extends BaseEntity> T save(T model);

    public <T extends BaseEntity> void saveOrUpdate(T model);
    
    public <T extends BaseEntity> void update(T model);
    
    public <T extends BaseEntity> void merge(T model);

    public <T extends BaseEntity, PK extends Serializable> void delete(Class<T> entityClass, PK id);

    public <T extends BaseEntity> void deleteObject(T model);

    public <T extends BaseEntity, PK extends Serializable> T get(Class<T> entityClass, PK id);
    
    public <T extends BaseEntity> int countAll(Class<T> entityClass);
    
    public <T extends BaseEntity> List<T> listAll(Class<T> entityClass);
    
    public <T extends BaseEntity> Page<T> listAll(Class<T> entityClass, int pn);
    
    public <T extends BaseEntity> Page<T> listAll(Class<T> entityClass, int pn, int pageSize);
    
}
