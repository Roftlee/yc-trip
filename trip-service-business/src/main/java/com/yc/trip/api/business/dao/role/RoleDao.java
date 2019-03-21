package com.yc.trip.api.business.dao.role;

import java.util.List;

import com.yc.trip.api.business.bo.role.RoleDomain;

/**
 * 角色信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:15
 *
 */
public interface RoleDao {

    /**
     * 新增角色信息
     */
    void addRole(RoleDomain roleDomain);

    /**
     * 修改角色信息
     */
    void updateRole(RoleDomain roleDomain);
    
    /**
     * 查询角色信息
     */
    RoleDomain getRole(RoleDomain roleDomain);

    /**
     * 查询角色信息列表
     */
    List<RoleDomain> queryRoleList(RoleDomain roleDomain);

}