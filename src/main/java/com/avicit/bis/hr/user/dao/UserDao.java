package com.avicit.bis.hr.user.dao;

import java.util.List;


import com.avicit.bis.hr.user.model.UserModel;
import com.avicit.bis.hr.user.model.UserQueryModel;
import com.avicit.framework.support.dao.hibernate.IBaseDao;



public interface UserDao extends IBaseDao<UserModel, Integer> {
    
    List<UserModel> query(int pn, int pageSize, UserQueryModel command);

    int countQuery(UserQueryModel command);

}
