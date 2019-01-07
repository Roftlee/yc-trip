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
import com.yc.trip.api.business.dao.user.UserRoleDao;
import com.yc.trip.api.business.dto.user.UserRole;
import com.yc.trip.api.business.query.user.UserRoleQuery;
import com.yc.trip.api.business.facade.user.UserRoleFacade;

/**
 * 用户角色信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-01-06 18:01
 */
@Service(version = "1.0.0")
public class UserRoleFacadeImpl extends AbstractDubboNativeService implements UserRoleFacade {

    @Autowired
    private UserRoleDao userRoleDao;

    /**
     * 新增用户角色信息
     * 
     * @throws PendingException
     */
    @Override
    public UserRole add(UserRole userRole) throws PendingException {
        try {
            // 新增时对各字段进行非空校验
            userRole.validateInsertFields();
            // 调数据库接口进行新增操作
            userRoleDao.add(userRole);
            // 将新增后回返回（包含自增主键值）
            return userRole;
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.userRoleDBError, "用户角色信息新增失败");
        }
    }

    /**
     * 修改用户角色信息
     * 
     * @throws PendingException
     */
    @Override
    public void update(UserRole userRole) throws PendingException {
        try {
            // 更新或删除操作时，不能所有参数都为空
            if (userRole.isAllFiledsNull()) {
                ResCode.userRoleDBParamInvalid.throwException("用户角色信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            userRoleDao.update(userRole);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.userRoleDBError, "用户角色信息更新失败");
        }
    }

    /**
     * 查询用户角色信息
     * 
     * @throws PendingException
     */
    @Override
    public UserRole get(UserRoleQuery userRoleQuery) throws PendingException {
        try {
            // 调数据库接口查询对象
            return userRoleDao.get(userRoleQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.userRoleDBError, "用户角色信息查询失败");
        }
    }
    
    /**
     * 查询用户角色信息信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public UserRole mustGet(UserRoleQuery userRoleQuery) throws PendingException {
        // 查询单位信息
        UserRole result = get(userRoleQuery);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.userRoleDBGetNull.throwException("未查询到用户角色信息");
        }
        return result;
    }

    /**
     * 查询用户角色信息列表
     * 
     * @throws PendingException
     */
    @Override
    public List<UserRole> queryList(UserRoleQuery userRoleQuery) throws PendingException {
        try {
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(userRoleQuery.getOrderby())) {
                PageHelper.orderBy(userRoleQuery.getOrderby());
            }
            // 调数据库接口查询列表
            return userRoleDao.queryList(userRoleQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.userRoleDBError, "用户角色信息列表查询失败");
        }
    }

    /**
     * 查询用户角色信息列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<UserRole> queryPage(UserRoleQuery userRoleQuery) throws PendingException {
        try {
            // 对请求参数进行校验
            if (userRoleQuery.getPageNo() <= 0 || userRoleQuery.getPageSize() <= 0) {
                ResCode.userRoleDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(userRoleQuery.getPageNo(), userRoleQuery.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(userRoleQuery.getOrderby())) {
                PageHelper.orderBy(userRoleQuery.getOrderby());
            }
            // 调数据库接口查询列表
            List<UserRole> resultList = userRoleDao.queryList(userRoleQuery);
            // 返回分页结果
            return new PageInfo<>(resultList);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.userRoleDBError, "用户角色信息分页查询失败");
        }
    }

}
