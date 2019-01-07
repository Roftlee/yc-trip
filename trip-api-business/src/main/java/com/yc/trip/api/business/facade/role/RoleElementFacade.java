package com.yc.trip.api.business.facade.role;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.role.RoleElement;
import com.yc.trip.api.business.query.role.RoleElementQuery;

/**
 * 角色权限信息相关接口
 * @author My-Toolkits
 * @since 2019-01-06 17:41
 */
public interface RoleElementFacade {

    /**
     * 新增角色权限信息
     * @throws PendingException 
     */
    RoleElement add(RoleElement roleElement) throws PendingException;

    /**
     * 修改角色权限信息
     * @throws PendingException 
     */
    void update(RoleElement roleElement) throws PendingException;
    
    /**
     * 查询角色权限信息
     * @throws PendingException 
     */
    RoleElement get(RoleElementQuery roleElementQuery) throws PendingException;
    
    /**
     * 查询角色权限信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    RoleElement mustGet(RoleElementQuery roleElementQuery) throws PendingException;

    /**
     * 查询角色权限信息列表
     * @throws PendingException 
     */
    List<RoleElement> queryList(RoleElementQuery roleElementQuery) throws PendingException;

    /**
     * 查询角色权限信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<RoleElement> queryPage(RoleElementQuery roleElementQuery) throws PendingException;

}