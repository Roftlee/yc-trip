package com.yc.trip.api.business.dao.user;

import java.util.List;

import com.yc.trip.api.business.bo.user.UserRoleDomain;

/**
 * 用户角色信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:34
 *
 */
public interface UserRoleDao {

    /**
     * 新增用户角色信息
     */
    void addUserRole(UserRoleDomain userRoleDomain);

    /**
     * 修改用户角色信息
     */
    void updateUserRole(UserRoleDomain userRoleDomain);
    
    /**
     * 查询用户角色信息
     */
    UserRoleDomain getUserRole(UserRoleDomain userRoleDomain);

    /**
     * 查询用户角色信息列表
     */
    List<UserRoleDomain> queryUserRoleList(UserRoleDomain userRoleDomain);

}