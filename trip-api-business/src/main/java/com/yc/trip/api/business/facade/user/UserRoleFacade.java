package com.yc.trip.api.business.facade.user;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.user.UserRole;

/**
 * 用户角色信息相关接口
 * @author My-Toolkits
 * @since 2019-03-21 22:34
 */
public interface UserRoleFacade {

    /**
     * 新增用户角色信息
     * @throws PendingException 
     */
    UserRole addUserRole(UserRole userRole) throws PendingException;

    /**
     * 修改用户角色信息
     * @throws PendingException 
     */
    UserRole updateUserRole(UserRole userRole) throws PendingException;
    
    /**
     * 查询用户角色信息
     * @throws PendingException 
     */
    UserRole getUserRole(UserRole userRole) throws PendingException;
    
    /**
     * 查询用户角色信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    UserRole mustGet(UserRole userRole) throws PendingException;

    /**
     * 查询用户角色信息列表
     * @throws PendingException 
     */
    List<UserRole> queryUserRoleList(UserRole userRole) throws PendingException;

    /**
     * 查询用户角色信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<UserRole> queryUserRolePage(UserRole userRole) throws PendingException;

}