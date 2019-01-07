package com.yc.trip.api.business.facade.user;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.user.UserRole;
import com.yc.trip.api.business.query.user.UserRoleQuery;

/**
 * 用户角色信息相关接口
 * @author My-Toolkits
 * @since 2019-01-06 18:01
 */
public interface UserRoleFacade {

    /**
     * 新增用户角色信息
     * @throws PendingException 
     */
    UserRole add(UserRole userRole) throws PendingException;

    /**
     * 修改用户角色信息
     * @throws PendingException 
     */
    void update(UserRole userRole) throws PendingException;
    
    /**
     * 查询用户角色信息
     * @throws PendingException 
     */
    UserRole get(UserRoleQuery userRoleQuery) throws PendingException;
    
    /**
     * 查询用户角色信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    UserRole mustGet(UserRoleQuery userRoleQuery) throws PendingException;

    /**
     * 查询用户角色信息列表
     * @throws PendingException 
     */
    List<UserRole> queryList(UserRoleQuery userRoleQuery) throws PendingException;

    /**
     * 查询用户角色信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<UserRole> queryPage(UserRoleQuery userRoleQuery) throws PendingException;

}