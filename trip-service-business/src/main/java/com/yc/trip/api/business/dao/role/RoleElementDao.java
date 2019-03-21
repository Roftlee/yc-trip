package com.yc.trip.api.business.dao.role;

import java.util.List;

import com.yc.trip.api.business.bo.role.RoleElementDomain;

/**
 * 角色权限信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:16
 *
 */
public interface RoleElementDao {

    /**
     * 新增角色权限信息
     */
    void addRoleElement(RoleElementDomain roleElementDomain);

    /**
     * 修改角色权限信息
     */
    void updateRoleElement(RoleElementDomain roleElementDomain);
    
    /**
     * 查询角色权限信息
     */
    RoleElementDomain getRoleElement(RoleElementDomain roleElementDomain);

    /**
     * 查询角色权限信息列表
     */
    List<RoleElementDomain> queryRoleElementList(RoleElementDomain roleElementDomain);

}