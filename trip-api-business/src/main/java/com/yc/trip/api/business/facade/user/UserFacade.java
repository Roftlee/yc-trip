package com.yc.trip.api.business.facade.user;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.user.User;

/**
 * 用户信息相关接口
 * @author My-Toolkits
 * @since 2019-03-21 22:31
 */
public interface UserFacade {

    /**
     * 新增用户信息
     * @throws PendingException 
     */
    User addUser(User user) throws PendingException;

    /**
     * 修改用户信息
     * @throws PendingException 
     */
    User updateUser(User user) throws PendingException;
    
    /**
     * 查询用户信息
     * @throws PendingException 
     */
    User getUser(User user) throws PendingException;
    
    /**
     * 查询用户信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    User mustGet(User user) throws PendingException;

    /**
     * 查询用户信息列表
     * @throws PendingException 
     */
    List<User> queryUserList(User user) throws PendingException;

    /**
     * 查询用户信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<User> queryUserPage(User user) throws PendingException;

}