package com.yc.trip.api.business.facade.role;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.role.Role;
import com.yc.trip.api.business.query.role.RoleQuery;

/**
 * 角色信息相关接口
 * @author My-Toolkits
 * @since 2019-01-06 17:31
 */
public interface RoleFacade {

    /**
     * 新增角色信息
     * @throws PendingException 
     */
    Role add(Role role) throws PendingException;

    /**
     * 修改角色信息
     * @throws PendingException 
     */
    void update(Role role) throws PendingException;
    
    /**
     * 查询角色信息
     * @throws PendingException 
     */
    Role get(RoleQuery roleQuery) throws PendingException;
    
    /**
     * 查询角色信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    Role mustGet(RoleQuery roleQuery) throws PendingException;

    /**
     * 查询角色信息列表
     * @throws PendingException 
     */
    List<Role> queryList(RoleQuery roleQuery) throws PendingException;

    /**
     * 查询角色信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<Role> queryPage(RoleQuery roleQuery) throws PendingException;

}