package com.avicit.bis.hr.user.dao.hibernate4;

import java.util.List;

import org.springframework.stereotype.Repository;


import com.avicit.bis.hr.user.dao.UserDao;
import com.avicit.bis.hr.user.model.UserModel;
import com.avicit.bis.hr.user.model.UserQueryModel;
import com.avicit.framework.support.dao.hibernate.BaseHibernateDao;


/**
 * User: Zhang Kaitao
 * Date: 11-12-26 下午4:19
 * Version: 1.0
 */
@Repository("UserDao")
public class UserHibernate4DaoImpl extends BaseHibernateDao<UserModel, Integer> implements UserDao {

    private static final String HQL_LIST = "from UserModel ";
    private static final String HQL_COUNT = "select count(*) from UserModel ";

    private static final String HQL_LIST_QUERY_CONDITION = " where username like ?";
    private static final String HQL_LIST_QUERY_ALL = HQL_LIST + HQL_LIST_QUERY_CONDITION + "order by id desc";
    private static final String HQL_COUNT_QUERY_ALL = HQL_COUNT + HQL_LIST_QUERY_CONDITION;

    @Override
    public List<UserModel> query(int pn, int pageSize, UserQueryModel command) {
        return list(HQL_LIST_QUERY_ALL, pn, pageSize, getQueryParam(command));
    }

    @Override
    public int countQuery(UserQueryModel command) {
        return this.<Number>aggregate(HQL_COUNT_QUERY_ALL, getQueryParam(command)).intValue();
    }
    

    private Object[] getQueryParam(UserQueryModel command) {
        //TODO 改成全文索引
        String usernameLikeStr = "%" + command.getUsername() + "%";
        return new Object[]{
            usernameLikeStr
        };
    }

}
