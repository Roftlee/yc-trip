package com.yc.trip.api.business.facade.role;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.role.Role;

/**
 * 角色信息相关接口
 * @author My-Toolkits
 * @since 2019-03-21 22:15
 */
public interface RoleFacade {

    /**
     * 新增角色信息
     * @throws PendingException 
     */
    Role addRole(Role role) throws PendingException;

    /**
     * 修改角色信息
     * @throws PendingException 
     */
    Role updateRole(Role role) throws PendingException;
    
    /**
     * 查询角色信息
     * @throws PendingException 
     */
    Role getRole(Role role) throws PendingException;
    
    /**
     * 查询角色信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    Role mustGet(Role role) throws PendingException;

    /**
     * 查询角色信息列表
     * @throws PendingException 
     */
    List<Role> queryRoleList(Role role) throws PendingException;

    /**
     * 查询角色信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<Role> queryRolePage(Role role) throws PendingException;

}