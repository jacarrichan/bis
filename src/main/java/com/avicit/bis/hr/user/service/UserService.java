package com.avicit.bis.hr.user.service;


import com.avicit.bis.hr.user.model.UserModel;
import com.avicit.bis.hr.user.model.UserQueryModel;
import com.avicit.framework.support.service.BaseService;
import com.avicit.framework.web.support.pagination.Page;


/**
 * User: Zhang Kaitao
 * Date: 12-1-4 上午10:13
 * Version: 1.0
 */
public interface UserService extends BaseService<UserModel, Integer> {

    Page<UserModel> query(int pn, int pageSize, UserQueryModel command);
}
