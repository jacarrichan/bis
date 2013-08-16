package com.avicit.bis.hr.user.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import com.avicit.bis.hr.user.dao.UserDao;
import com.avicit.bis.hr.user.model.UserModel;
import com.avicit.bis.hr.user.model.UserQueryModel;
import com.avicit.bis.hr.user.service.UserService;
import com.avicit.framework.support.dao.hibernate.IBaseDao;
import com.avicit.framework.support.service.impl.BaseServiceImpl;
import com.avicit.framework.web.support.pagination.Page;
import com.avicit.framework.web.support.pagination.PageUtil;


/**
 * User: Zhang Kaitao
 * Date: 12-1-4 上午11:06
 * Version: 1.0
 */

@Service("UserService")
public class UserServiceImpl extends BaseServiceImpl<UserModel, Integer> implements UserService {

    protected static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserDao userDao;

    @Autowired
    @Qualifier("UserDao")
    @Override
    public void setBaseDao(IBaseDao<UserModel, Integer> userDao) {
        this.baseDao = userDao;
        this.userDao = (UserDao) userDao;
    }
    


    @Override
    public Page<UserModel> query(int pn, int pageSize, UserQueryModel command) {
        return PageUtil.getPage(userDao.countQuery(command) ,pn, userDao.query(pn, pageSize, command), pageSize);
    }

   
}