package com.yc.trip.api.business.dao.user;

import java.util.List;

import com.yc.trip.api.business.bo.user.UserDomain;

/**
 * 用户信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:31
 *
 */
public interface UserDao {

    /**
     * 新增用户信息
     */
    void addUser(UserDomain userDomain);

    /**
     * 修改用户信息
     */
    void updateUser(UserDomain userDomain);
    
    /**
     * 查询用户信息
     */
    UserDomain getUser(UserDomain userDomain);

    /**
     * 查询用户信息列表
     */
    List<UserDomain> queryUserList(UserDomain userDomain);

}