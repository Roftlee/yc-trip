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
import com.yc.trip.api.business.bo.user.UserPasswordDomain;
import com.yc.trip.api.business.dao.user.UserPasswordDao;
import com.yc.trip.api.business.dto.user.UserPassword;
import com.yc.trip.api.business.facade.user.UserPasswordFacade;

/**
 * 用户密码相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:33
 */
@Service(version = "1.0.0")
public class UserPasswordFacadeImpl extends AbstractDubboNativeService implements UserPasswordFacade {

    @Autowired
    private UserPasswordDao userPasswordDao;

    /**
     * 新增用户密码
     * 
     * @throws PendingException
     */
    @Override
    public UserPassword addUserPassword(UserPassword userPassword) throws PendingException {
        try {
            // 转换成domain对象
            UserPasswordDomain cond = BeanMapping.map(userPassword, UserPasswordDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.userPasswordDBParamInvalid.throwException("用户密码新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            userPasswordDao.addUserPassword(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, UserPassword.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.userPasswordDBError, "用户密码新增失败");
        }
    }

    /**
     * 修改用户密码
     * 
     * @throws PendingException
     */
    @Override
    public UserPassword updateUserPassword(UserPassword userPassword) throws PendingException {
        try {
            // 转换成domain对象
            UserPasswordDomain cond = BeanMapping.map(userPassword, UserPasswordDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.userPasswordDBParamInvalid.throwException("用户密码更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            userPasswordDao.updateUserPassword(cond);
            return BeanMapping.map(cond, UserPassword.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.userPasswordDBError, "用户密码更新失败");
        }
    }

    /**
     * 查询用户密码
     * 
     * @throws PendingException
     */
    @Override
    public UserPassword getUserPassword(UserPassword userPassword) throws PendingException {
        try {
            // 转换成Domain对象
            UserPasswordDomain cond = BeanMapping.map(userPassword, UserPasswordDomain.class);
            // 调数据库接口查询对象
            UserPasswordDomain resultBean = userPasswordDao.getUserPassword(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, UserPassword.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.userPasswordDBError, "用户密码查询失败");
        }
    }
    
    /**
     * 查询用户密码信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public UserPassword mustGet(UserPassword userPassword) throws PendingException {
        // 查询单位信息
        UserPassword result = getUserPassword(userPassword);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.userPasswordDBGetNull.throwException("未查询到用户密码");
        }
        return result;
    }

    /**
     * 查询用户密码列表
     * 
     * @throws PendingException
     */
    @Override
    public List<UserPassword> queryUserPasswordList(UserPassword userPassword) throws PendingException {
        try {
            // 转换成Domain对象
            UserPasswordDomain cond = BeanMapping.map(userPassword, UserPasswordDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(userPassword.getOrderby())) {
                PageHelper.orderBy(userPassword.getOrderby());
            }
            // 调数据库接口查询列表
            List<UserPasswordDomain> resultList = userPasswordDao.queryUserPasswordList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, UserPassword.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.userPasswordDBError, "用户密码列表查询失败");
        }
    }

    /**
     * 查询用户密码列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<UserPassword> queryUserPasswordPage(UserPassword userPassword) throws PendingException {
        try {
            // 对请求参数进行校验
            if (userPassword.getPageNo() <= 0 || userPassword.getPageSize() <= 0) {
                ResCode.userPasswordDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(userPassword.getPageNo(), userPassword.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(userPassword.getOrderby())) {
                PageHelper.orderBy(userPassword.getOrderby());
            }
            // 转换成Domain对象
            UserPasswordDomain cond = BeanMapping.map(userPassword, UserPasswordDomain.class);
            // 调数据库接口查询列表
            List<UserPasswordDomain> resultList = userPasswordDao.queryUserPasswordList(cond);
            // 生成分页对象
            PageInfo<UserPasswordDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, UserPassword.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.userPasswordDBError, "用户密码分页查询失败");
        }
    }

}
