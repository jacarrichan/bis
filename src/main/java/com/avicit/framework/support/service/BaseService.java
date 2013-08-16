package com.avicit.framework.support.service;


import java.util.List;

import com.avicit.framework.web.support.pagination.Page;

public interface BaseService<M extends java.io.Serializable, PK extends java.io.Serializable> {
    
    public M save(M model);

    public void saveOrUpdate(M model);
    
    public void update(M model);
    
    public void merge(M model);

    public void delete(PK id);

    public void deleteObject(M model);

    public M get(PK id);
    
    public int countAll();
    
    public List<M> listAll();
    
    public Page<M> listAll(int pn);
    
    public Page<M> listAll(int pn, int pageSize);
    

    public Page<M> pre(PK pk, int pn, int pageSize);
    
    public Page<M> next(PK pk, int pn, int pageSize);
    
    public Page<M> pre(PK pk, int pn);
    
    public Page<M> next(PK pk, int pn);
}
