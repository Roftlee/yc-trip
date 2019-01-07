package com.yc.trip.api.business.service.user;

import java.util.List;


import org.go.api.core.integration.AbstractDubboNativeService;
import org.go.api.core.util.BeanMapping;
import org.go.framework.core.exception.PendingException;
import org.go.framework.util.common.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.yc.trip.api.core.constants.ResCode;
import com.yc.trip.api.business.dao.user.UserPasswordDao;
import com.yc.trip.api.business.dto.user.UserPassword;
import com.yc.trip.api.business.query.user.UserPasswordQuery;
import com.yc.trip.api.business.facade.user.UserPasswordFacade;

/**
 * 用户密码信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-01-06 18:08
 */
@Service(version = "1.0.0")
public class UserPasswordFacadeImpl extends AbstractDubboNativeService implements UserPasswordFacade {

    @Autowired
    private UserPasswordDao userPasswordDao;

    /**
     * 新增用户密码信息
     * 
     * @throws PendingException
     */
    @Override
    public UserPassword add(UserPassword userPassword) throws PendingException {
        try {
            // 新增时对各字段进行非空校验
            userPassword.validateInsertFields();
            // 调数据库接口进行新增操作
            userPasswordDao.add(userPassword);
            // 将新增后回返回（包含自增主键值）
            return userPassword;
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.userPasswordDBError, "用户密码信息新增失败");
        }
    }

    /**
     * 修改用户密码信息
     * 
     * @throws PendingException
     */
    @Override
    public void update(UserPassword userPassword) throws PendingException {
        try {
            // 更新或删除操作时，不能所有参数都为空
            if (userPassword.isAllFiledsNull()) {
                ResCode.userPasswordDBParamInvalid.throwException("用户密码信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            userPasswordDao.update(userPassword);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.userPasswordDBError, "用户密码信息更新失败");
        }
    }

    /**
     * 查询用户密码信息
     * 
     * @throws PendingException
     */
    @Override
    public UserPassword get(UserPasswordQuery userPasswordQuery) throws PendingException {
        try {
            // 调数据库接口查询对象
            return userPasswordDao.get(userPasswordQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.userPasswordDBError, "用户密码信息查询失败");
        }
    }
    
    /**
     * 查询用户密码信息信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public UserPassword mustGet(UserPasswordQuery userPasswordQuery) throws PendingException {
        // 查询单位信息
        UserPassword result = get(userPasswordQuery);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.userPasswordDBGetNull.throwException("未查询到用户密码信息");
        }
        return result;
    }

    /**
     * 查询用户密码信息列表
     * 
     * @throws PendingException
     */
    @Override
    public List<UserPassword> queryList(UserPasswordQuery userPasswordQuery) throws PendingException {
        try {
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(userPasswordQuery.getOrderby())) {
                PageHelper.orderBy(userPasswordQuery.getOrderby());
            }
            // 调数据库接口查询列表
            return userPasswordDao.queryList(userPasswordQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.userPasswordDBError, "用户密码信息列表查询失败");
        }
    }

    /**
     * 查询用户密码信息列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<UserPassword> queryPage(UserPasswordQuery userPasswordQuery) throws PendingException {
        try {
            // 对请求参数进行校验
            if (userPasswordQuery.getPageNo() <= 0 || userPasswordQuery.getPageSize() <= 0) {
                ResCode.userPasswordDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(userPasswordQuery.getPageNo(), userPasswordQuery.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(userPasswordQuery.getOrderby())) {
                PageHelper.orderBy(userPasswordQuery.getOrderby());
            }
            // 调数据库接口查询列表
            List<UserPassword> resultList = userPasswordDao.queryList(userPasswordQuery);
            // 返回分页结果
            return new PageInfo<>(resultList);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.userPasswordDBError, "用户密码信息分页查询失败");
        }
    }

}
