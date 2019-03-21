package com.yc.trip.api.business.service.role;

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
import com.yc.trip.api.business.bo.role.RoleDomain;
import com.yc.trip.api.business.dao.role.RoleDao;
import com.yc.trip.api.business.dto.role.Role;
import com.yc.trip.api.business.facade.role.RoleFacade;

/**
 * 角色信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:15
 */
@Service(version = "1.0.0")
public class RoleFacadeImpl extends AbstractDubboNativeService implements RoleFacade {

    @Autowired
    private RoleDao roleDao;

    /**
     * 新增角色信息
     * 
     * @throws PendingException
     */
    @Override
    public Role addRole(Role role) throws PendingException {
        try {
            // 转换成domain对象
            RoleDomain cond = BeanMapping.map(role, RoleDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.roleDBParamInvalid.throwException("角色信息新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            roleDao.addRole(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, Role.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.roleDBError, "角色信息新增失败");
        }
    }

    /**
     * 修改角色信息
     * 
     * @throws PendingException
     */
    @Override
    public Role updateRole(Role role) throws PendingException {
        try {
            // 转换成domain对象
            RoleDomain cond = BeanMapping.map(role, RoleDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.roleDBParamInvalid.throwException("角色信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            roleDao.updateRole(cond);
            return BeanMapping.map(cond, Role.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.roleDBError, "角色信息更新失败");
        }
    }

    /**
     * 查询角色信息
     * 
     * @throws PendingException
     */
    @Override
    public Role getRole(Role role) throws PendingException {
        try {
            // 转换成Domain对象
            RoleDomain cond = BeanMapping.map(role, RoleDomain.class);
            // 调数据库接口查询对象
            RoleDomain resultBean = roleDao.getRole(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, Role.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.roleDBError, "角色信息查询失败");
        }
    }
    
    /**
     * 查询角色信息信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public Role mustGet(Role role) throws PendingException {
        // 查询单位信息
        Role result = getRole(role);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.roleDBGetNull.throwException("未查询到角色信息");
        }
        return result;
    }

    /**
     * 查询角色信息列表
     * 
     * @throws PendingException
     */
    @Override
    public List<Role> queryRoleList(Role role) throws PendingException {
        try {
            // 转换成Domain对象
            RoleDomain cond = BeanMapping.map(role, RoleDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(role.getOrderby())) {
                PageHelper.orderBy(role.getOrderby());
            }
            // 调数据库接口查询列表
            List<RoleDomain> resultList = roleDao.queryRoleList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, Role.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.roleDBError, "角色信息列表查询失败");
        }
    }

    /**
     * 查询角色信息列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<Role> queryRolePage(Role role) throws PendingException {
        try {
            // 对请求参数进行校验
            if (role.getPageNo() <= 0 || role.getPageSize() <= 0) {
                ResCode.roleDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(role.getPageNo(), role.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(role.getOrderby())) {
                PageHelper.orderBy(role.getOrderby());
            }
            // 转换成Domain对象
            RoleDomain cond = BeanMapping.map(role, RoleDomain.class);
            // 调数据库接口查询列表
            List<RoleDomain> resultList = roleDao.queryRoleList(cond);
            // 生成分页对象
            PageInfo<RoleDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, Role.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.roleDBError, "角色信息分页查询失败");
        }
    }

}
