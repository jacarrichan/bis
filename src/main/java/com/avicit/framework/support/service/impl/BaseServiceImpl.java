package com.avicit.framework.support.service.impl;

import com.avicit.framework.constants.Constants;
import com.avicit.framework.support.dao.hibernate.IBaseDao;
import com.avicit.framework.support.service.BaseService;
import com.avicit.framework.web.support.pagination.Page;
import com.avicit.framework.web.support.pagination.PageUtil;

import java.util.List;

public abstract class BaseServiceImpl<M extends java.io.Serializable, PK extends java.io.Serializable>
		implements BaseService<M, PK> {

	protected IBaseDao<M, PK> baseDao;

	public abstract void setBaseDao(IBaseDao<M, PK> baseDao);

	@Override
	public M save(M model) {
		baseDao.save(model);
		return model;
	}

	@Override
	public void merge(M model) {
		baseDao.merge(model);
	}

	@Override
	public void saveOrUpdate(M model) {
		baseDao.saveOrUpdate(model);
	}

	@Override
	public void update(M model) {
		baseDao.update(model);
	}

	@Override
	public void delete(PK id) {
		baseDao.delete(id);
	}

	@Override
	public void deleteObject(M model) {
		baseDao.deleteObject(model);
	}

	@Override
	public M get(PK id) {
		return baseDao.get(id);
	}

	@Override
	public int countAll() {
		return baseDao.countAll();
	}

	@Override
	public List<M> listAll() {
		return baseDao.listAll();
	}

	@Override
	public Page<M> listAll(int pn) {

		return this.listAll(pn, Constants.DEFAULT_PAGE_SIZE);
	}

	public Page<M> listAllWithOptimize(int pn) {
		return this.listAllWithOptimize(pn, Constants.DEFAULT_PAGE_SIZE);
	}

	@Override
	public Page<M> listAll(int pn, int pageSize) {
		Integer count = countAll();
		List<M> items = baseDao.listAll(pn, pageSize);
		return PageUtil.getPage(count, pn, items, pageSize);
	}

	public Page<M> listAllWithOptimize(int pn, int pageSize) {
		Integer count = countAll();
		List<M> items = baseDao.listAll(pn, pageSize);
		return PageUtil.getPage(count, pn, items, pageSize);
	}

	@Override
	public Page<M> pre(PK pk, int pn, int pageSize) {
		Integer count = countAll();
		List<M> items = baseDao.pre(pk, pn, pageSize);
		return PageUtil.getPage(count, pn, items, pageSize);
	}

	@Override
	public Page<M> next(PK pk, int pn, int pageSize) {
		Integer count = countAll();
		List<M> items = baseDao.next(pk, pn, pageSize);
		return PageUtil.getPage(count, pn, items, pageSize);
	}

	@Override
	public Page<M> pre(PK pk, int pn) {
		return pre(pk, pn, Constants.DEFAULT_PAGE_SIZE);
	}

	@Override
	public Page<M> next(PK pk, int pn) {
		return next(pk, pn, Constants.DEFAULT_PAGE_SIZE);
	}

}
