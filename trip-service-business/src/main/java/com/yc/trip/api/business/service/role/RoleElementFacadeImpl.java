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
import com.yc.trip.api.business.dao.role.RoleElementDao;
import com.yc.trip.api.business.dto.role.RoleElement;
import com.yc.trip.api.business.query.role.RoleElementQuery;
import com.yc.trip.api.business.facade.role.RoleElementFacade;

/**
 * 角色权限信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:41
 */
@Service(version = "1.0.0")
public class RoleElementFacadeImpl extends AbstractDubboNativeService implements RoleElementFacade {

    @Autowired
    private RoleElementDao roleElementDao;

    /**
     * 新增角色权限信息
     * 
     * @throws PendingException
     */
    @Override
    public RoleElement add(RoleElement roleElement) throws PendingException {
        try {
            // 新增时对各字段进行非空校验
            roleElement.validateInsertFields();
            // 调数据库接口进行新增操作
            roleElementDao.add(roleElement);
            // 将新增后回返回（包含自增主键值）
            return roleElement;
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.roleElementDBError, "角色权限信息新增失败");
        }
    }

    /**
     * 修改角色权限信息
     * 
     * @throws PendingException
     */
    @Override
    public void update(RoleElement roleElement) throws PendingException {
        try {
            // 更新或删除操作时，不能所有参数都为空
            if (roleElement.isAllFiledsNull()) {
                ResCode.roleElementDBParamInvalid.throwException("角色权限信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            roleElementDao.update(roleElement);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.roleElementDBError, "角色权限信息更新失败");
        }
    }

    /**
     * 查询角色权限信息
     * 
     * @throws PendingException
     */
    @Override
    public RoleElement get(RoleElementQuery roleElementQuery) throws PendingException {
        try {
            // 调数据库接口查询对象
            return roleElementDao.get(roleElementQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.roleElementDBError, "角色权限信息查询失败");
        }
    }
    
    /**
     * 查询角色权限信息信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public RoleElement mustGet(RoleElementQuery roleElementQuery) throws PendingException {
        // 查询单位信息
        RoleElement result = get(roleElementQuery);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.roleElementDBGetNull.throwException("未查询到角色权限信息");
        }
        return result;
    }

    /**
     * 查询角色权限信息列表
     * 
     * @throws PendingException
     */
    @Override
    public List<RoleElement> queryList(RoleElementQuery roleElementQuery) throws PendingException {
        try {
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(roleElementQuery.getOrderby())) {
                PageHelper.orderBy(roleElementQuery.getOrderby());
            }
            // 调数据库接口查询列表
            return roleElementDao.queryList(roleElementQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.roleElementDBError, "角色权限信息列表查询失败");
        }
    }

    /**
     * 查询角色权限信息列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<RoleElement> queryPage(RoleElementQuery roleElementQuery) throws PendingException {
        try {
            // 对请求参数进行校验
            if (roleElementQuery.getPageNo() <= 0 || roleElementQuery.getPageSize() <= 0) {
                ResCode.roleElementDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(roleElementQuery.getPageNo(), roleElementQuery.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(roleElementQuery.getOrderby())) {
                PageHelper.orderBy(roleElementQuery.getOrderby());
            }
            // 调数据库接口查询列表
            List<RoleElement> resultList = roleElementDao.queryList(roleElementQuery);
            // 返回分页结果
            return new PageInfo<>(resultList);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.roleElementDBError, "角色权限信息分页查询失败");
        }
    }

}
