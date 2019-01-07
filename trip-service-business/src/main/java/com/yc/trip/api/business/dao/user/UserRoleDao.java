package com.yc.trip.api.business.dao.user;

import java.util.List;

import com.yc.trip.api.business.dto.user.UserRole;
import com.yc.trip.api.business.query.user.UserRoleQuery;

/**
 * 用户角色信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 18:01
 *
 */
public interface UserRoleDao {

    /**
     * 用户角色信息新增
     */
    void add(UserRole userRole);

    /**
     * 用户角色信息修改
     */
    void update(UserRole userRole);
    
    /**
     * 用户角色信息查询
     */
    UserRole get(UserRoleQuery userRoleQuery);

    /**
     * 用户角色信息列表查询
     */
    List<UserRole> queryList(UserRoleQuery userRoleQuery);

}