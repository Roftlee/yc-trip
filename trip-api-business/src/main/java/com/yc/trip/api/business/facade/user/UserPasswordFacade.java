package com.yc.trip.api.business.facade.user;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.user.UserPassword;

/**
 * 用户密码相关接口
 * @author My-Toolkits
 * @since 2019-03-21 22:33
 */
public interface UserPasswordFacade {

    /**
     * 新增用户密码
     * @throws PendingException 
     */
    UserPassword addUserPassword(UserPassword userPassword) throws PendingException;

    /**
     * 修改用户密码
     * @throws PendingException 
     */
    UserPassword updateUserPassword(UserPassword userPassword) throws PendingException;
    
    /**
     * 查询用户密码
     * @throws PendingException 
     */
    UserPassword getUserPassword(UserPassword userPassword) throws PendingException;
    
    /**
     * 查询用户密码（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    UserPassword mustGet(UserPassword userPassword) throws PendingException;

    /**
     * 查询用户密码列表
     * @throws PendingException 
     */
    List<UserPassword> queryUserPasswordList(UserPassword userPassword) throws PendingException;

    /**
     * 查询用户密码列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<UserPassword> queryUserPasswordPage(UserPassword userPassword) throws PendingException;

}