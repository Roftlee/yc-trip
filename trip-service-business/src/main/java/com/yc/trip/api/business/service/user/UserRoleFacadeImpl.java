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
import com.yc.trip.api.business.bo.user.UserRoleDomain;
import com.yc.trip.api.business.dao.user.UserRoleDao;
import com.yc.trip.api.business.dto.user.UserRole;
import com.yc.trip.api.business.facade.user.UserRoleFacade;

/**
 * 用户角色信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:34
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
    public UserRole addUserRole(UserRole userRole) throws PendingException {
        try {
            // 转换成domain对象
            UserRoleDomain cond = BeanMapping.map(userRole, UserRoleDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.userRoleDBParamInvalid.throwException("用户角色信息新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            userRoleDao.addUserRole(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, UserRole.class);
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
    public UserRole updateUserRole(UserRole userRole) throws PendingException {
        try {
            // 转换成domain对象
            UserRoleDomain cond = BeanMapping.map(userRole, UserRoleDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.userRoleDBParamInvalid.throwException("用户角色信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            userRoleDao.updateUserRole(cond);
            return BeanMapping.map(cond, UserRole.class);
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
    public UserRole getUserRole(UserRole userRole) throws PendingException {
        try {
            // 转换成Domain对象
            UserRoleDomain cond = BeanMapping.map(userRole, UserRoleDomain.class);
            // 调数据库接口查询对象
            UserRoleDomain resultBean = userRoleDao.getUserRole(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, UserRole.class);
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
    public UserRole mustGet(UserRole userRole) throws PendingException {
        // 查询单位信息
        UserRole result = getUserRole(userRole);
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
    public List<UserRole> queryUserRoleList(UserRole userRole) throws PendingException {
        try {
            // 转换成Domain对象
            UserRoleDomain cond = BeanMapping.map(userRole, UserRoleDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(userRole.getOrderby())) {
                PageHelper.orderBy(userRole.getOrderby());
            }
            // 调数据库接口查询列表
            List<UserRoleDomain> resultList = userRoleDao.queryUserRoleList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, UserRole.class);
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
    public PageInfo<UserRole> queryUserRolePage(UserRole userRole) throws PendingException {
        try {
            // 对请求参数进行校验
            if (userRole.getPageNo() <= 0 || userRole.getPageSize() <= 0) {
                ResCode.userRoleDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(userRole.getPageNo(), userRole.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(userRole.getOrderby())) {
                PageHelper.orderBy(userRole.getOrderby());
            }
            // 转换成Domain对象
            UserRoleDomain cond = BeanMapping.map(userRole, UserRoleDomain.class);
            // 调数据库接口查询列表
            List<UserRoleDomain> resultList = userRoleDao.queryUserRoleList(cond);
            // 生成分页对象
            PageInfo<UserRoleDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, UserRole.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.userRoleDBError, "用户角色信息分页查询失败");
        }
    }

}
