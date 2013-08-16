package com.avicit.framework.support.dao.hibernate;

import java.util.List;

public interface IBaseDao<M extends java.io.Serializable, PK extends java.io.Serializable> {
    
    public PK save(M model);

    public void saveOrUpdate(M model);
    
    public void update(M model);
    
    public void merge(M model);

    public void delete(PK id);

    public void deleteObject(M model);

    public M get(PK id);
    
    public int countAll();

    public List<M> listAll();

    public List<M> listAll(int pn, int pageSize);
    
    public List<M> pre(PK pk, int pn, int pageSize);
    public List<M> next(PK pk, int pn, int pageSize);
    
    boolean exists(PK id);
    
    public void flush();
    
    public void clear();



}
