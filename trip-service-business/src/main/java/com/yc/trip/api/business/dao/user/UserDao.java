package com.yc.trip.api.business.dao.user;

import java.util.List;

import com.yc.trip.api.business.dto.user.User;
import com.yc.trip.api.business.query.user.UserQuery;

/**
 * 用户信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-07 20:48
 *
 */
public interface UserDao {

    /**
     * 用户信息新增
     */
    void add(User user);

    /**
     * 用户信息修改
     */
    void update(User user);
    
    /**
     * 用户信息查询
     */
    User get(UserQuery userQuery);

    /**
     * 用户信息列表查询
     */
    List<User> queryList(UserQuery userQuery);

}