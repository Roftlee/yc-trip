package com.yc.trip.api.business.facade.role;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.role.RoleElement;

/**
 * 角色权限信息相关接口
 * @author My-Toolkits
 * @since 2019-03-21 22:16
 */
public interface RoleElementFacade {

    /**
     * 新增角色权限信息
     * @throws PendingException 
     */
    RoleElement addRoleElement(RoleElement roleElement) throws PendingException;

    /**
     * 修改角色权限信息
     * @throws PendingException 
     */
    RoleElement updateRoleElement(RoleElement roleElement) throws PendingException;
    
    /**
     * 查询角色权限信息
     * @throws PendingException 
     */
    RoleElement getRoleElement(RoleElement roleElement) throws PendingException;
    
    /**
     * 查询角色权限信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    RoleElement mustGet(RoleElement roleElement) throws PendingException;

    /**
     * 查询角色权限信息列表
     * @throws PendingException 
     */
    List<RoleElement> queryRoleElementList(RoleElement roleElement) throws PendingException;

    /**
     * 查询角色权限信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<RoleElement> queryRoleElementPage(RoleElement roleElement) throws PendingException;

}