package com.yc.trip.api.business.dao.role;

import java.util.List;

import com.yc.trip.api.business.dto.role.RoleElement;
import com.yc.trip.api.business.query.role.RoleElementQuery;

/**
 * 角色权限信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:41
 *
 */
public interface RoleElementDao {

    /**
     * 角色权限信息新增
     */
    void add(RoleElement roleElement);

    /**
     * 角色权限信息修改
     */
    void update(RoleElement roleElement);
    
    /**
     * 角色权限信息查询
     */
    RoleElement get(RoleElementQuery roleElementQuery);

    /**
     * 角色权限信息列表查询
     */
    List<RoleElement> queryList(RoleElementQuery roleElementQuery);

}