package com.yc.trip.api.business.dao.user;

import java.util.List;

import com.yc.trip.api.business.bo.user.UserPasswordDomain;

/**
 * 用户密码Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:33
 *
 */
public interface UserPasswordDao {

    /**
     * 新增用户密码
     */
    void addUserPassword(UserPasswordDomain userPasswordDomain);

    /**
     * 修改用户密码
     */
    void updateUserPassword(UserPasswordDomain userPasswordDomain);
    
    /**
     * 查询用户密码
     */
    UserPasswordDomain getUserPassword(UserPasswordDomain userPasswordDomain);

    /**
     * 查询用户密码列表
     */
    List<UserPasswordDomain> queryUserPasswordList(UserPasswordDomain userPasswordDomain);

}