package com.yc.trip.api.business.dao.role;

import java.util.List;

import com.yc.trip.api.business.dto.role.Role;
import com.yc.trip.api.business.query.role.RoleQuery;

/**
 * 角色信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:31
 *
 */
public interface RoleDao {

    /**
     * 角色信息新增
     */
    void add(Role role);

    /**
     * 角色信息修改
     */
    void update(Role role);
    
    /**
     * 角色信息查询
     */
    Role get(RoleQuery roleQuery);

    /**
     * 角色信息列表查询
     */
    List<Role> queryList(RoleQuery roleQuery);

}