package com.yc.trip.api.business.dao.user;

import java.util.List;

import com.yc.trip.api.business.dto.user.UserPassword;
import com.yc.trip.api.business.query.user.UserPasswordQuery;

/**
 * 用户密码信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 18:08
 *
 */
public interface UserPasswordDao {

    /**
     * 用户密码信息新增
     */
    void add(UserPassword userPassword);

    /**
     * 用户密码信息修改
     */
    void update(UserPassword userPassword);
    
    /**
     * 用户密码信息查询
     */
    UserPassword get(UserPasswordQuery userPasswordQuery);

    /**
     * 用户密码信息列表查询
     */
    List<UserPassword> queryList(UserPasswordQuery userPasswordQuery);

}