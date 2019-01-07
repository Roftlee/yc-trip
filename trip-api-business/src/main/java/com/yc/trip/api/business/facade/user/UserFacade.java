package com.yc.trip.api.business.facade.user;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.user.User;
import com.yc.trip.api.business.query.user.UserQuery;

/**
 * 用户信息相关接口
 * @author My-Toolkits
 * @since 2019-01-07 20:48
 */
public interface UserFacade {

    /**
     * 新增用户信息
     * @throws PendingException 
     */
    User add(User user) throws PendingException;

    /**
     * 修改用户信息
     * @throws PendingException 
     */
    void update(User user) throws PendingException;
    
    /**
     * 查询用户信息
     * @throws PendingException 
     */
    User get(UserQuery userQuery) throws PendingException;
    
    /**
     * 查询用户信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    User mustGet(UserQuery userQuery) throws PendingException;

    /**
     * 查询用户信息列表
     * @throws PendingException 
     */
    List<User> queryList(UserQuery userQuery) throws PendingException;

    /**
     * 查询用户信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<User> queryPage(UserQuery userQuery) throws PendingException;

}