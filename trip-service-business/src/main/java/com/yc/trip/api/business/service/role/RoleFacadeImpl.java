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
import com.yc.trip.api.business.dao.role.RoleDao;
import com.yc.trip.api.business.dto.role.Role;
import com.yc.trip.api.business.query.role.RoleQuery;
import com.yc.trip.api.business.facade.role.RoleFacade;

/**
 * 角色信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:31
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
    public Role add(Role role) throws PendingException {
        try {
            // 新增时对各字段进行非空校验
            role.validateInsertFields();
            // 调数据库接口进行新增操作
            roleDao.add(role);
            // 将新增后回返回（包含自增主键值）
            return role;
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
    public void update(Role role) throws PendingException {
        try {
            // 更新或删除操作时，不能所有参数都为空
            if (role.isAllFiledsNull()) {
                ResCode.roleDBParamInvalid.throwException("角色信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            roleDao.update(role);
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
    public Role get(RoleQuery roleQuery) throws PendingException {
        try {
            // 调数据库接口查询对象
            return roleDao.get(roleQuery);
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
    public Role mustGet(RoleQuery roleQuery) throws PendingException {
        // 查询单位信息
        Role result = get(roleQuery);
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
    public List<Role> queryList(RoleQuery roleQuery) throws PendingException {
        try {
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(roleQuery.getOrderby())) {
                PageHelper.orderBy(roleQuery.getOrderby());
            }
            // 调数据库接口查询列表
            return roleDao.queryList(roleQuery);
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
    public PageInfo<Role> queryPage(RoleQuery roleQuery) throws PendingException {
        try {
            // 对请求参数进行校验
            if (roleQuery.getPageNo() <= 0 || roleQuery.getPageSize() <= 0) {
                ResCode.roleDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(roleQuery.getPageNo(), roleQuery.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(roleQuery.getOrderby())) {
                PageHelper.orderBy(roleQuery.getOrderby());
            }
            // 调数据库接口查询列表
            List<Role> resultList = roleDao.queryList(roleQuery);
            // 返回分页结果
            return new PageInfo<>(resultList);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.roleDBError, "角色信息分页查询失败");
        }
    }

}
