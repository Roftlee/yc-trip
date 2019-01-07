package com.yc.trip.api.business.facade.user;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.user.UserPassword;
import com.yc.trip.api.business.query.user.UserPasswordQuery;

/**
 * 用户密码信息相关接口
 * @author My-Toolkits
 * @since 2019-01-06 18:08
 */
public interface UserPasswordFacade {

    /**
     * 新增用户密码信息
     * @throws PendingException 
     */
    UserPassword add(UserPassword userPassword) throws PendingException;

    /**
     * 修改用户密码信息
     * @throws PendingException 
     */
    void update(UserPassword userPassword) throws PendingException;
    
    /**
     * 查询用户密码信息
     * @throws PendingException 
     */
    UserPassword get(UserPasswordQuery userPasswordQuery) throws PendingException;
    
    /**
     * 查询用户密码信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    UserPassword mustGet(UserPasswordQuery userPasswordQuery) throws PendingException;

    /**
     * 查询用户密码信息列表
     * @throws PendingException 
     */
    List<UserPassword> queryList(UserPasswordQuery userPasswordQuery) throws PendingException;

    /**
     * 查询用户密码信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<UserPassword> queryPage(UserPasswordQuery userPasswordQuery) throws PendingException;

}