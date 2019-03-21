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
import com.yc.trip.api.business.bo.role.RoleElementDomain;
import com.yc.trip.api.business.dao.role.RoleElementDao;
import com.yc.trip.api.business.dto.role.RoleElement;
import com.yc.trip.api.business.facade.role.RoleElementFacade;

/**
 * 角色权限信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:16
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
    public RoleElement addRoleElement(RoleElement roleElement) throws PendingException {
        try {
            // 转换成domain对象
            RoleElementDomain cond = BeanMapping.map(roleElement, RoleElementDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.roleElementDBParamInvalid.throwException("角色权限信息新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            roleElementDao.addRoleElement(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, RoleElement.class);
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
    public RoleElement updateRoleElement(RoleElement roleElement) throws PendingException {
        try {
            // 转换成domain对象
            RoleElementDomain cond = BeanMapping.map(roleElement, RoleElementDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.roleElementDBParamInvalid.throwException("角色权限信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            roleElementDao.updateRoleElement(cond);
            return BeanMapping.map(cond, RoleElement.class);
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
    public RoleElement getRoleElement(RoleElement roleElement) throws PendingException {
        try {
            // 转换成Domain对象
            RoleElementDomain cond = BeanMapping.map(roleElement, RoleElementDomain.class);
            // 调数据库接口查询对象
            RoleElementDomain resultBean = roleElementDao.getRoleElement(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, RoleElement.class);
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
    public RoleElement mustGet(RoleElement roleElement) throws PendingException {
        // 查询单位信息
        RoleElement result = getRoleElement(roleElement);
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
    public List<RoleElement> queryRoleElementList(RoleElement roleElement) throws PendingException {
        try {
            // 转换成Domain对象
            RoleElementDomain cond = BeanMapping.map(roleElement, RoleElementDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(roleElement.getOrderby())) {
                PageHelper.orderBy(roleElement.getOrderby());
            }
            // 调数据库接口查询列表
            List<RoleElementDomain> resultList = roleElementDao.queryRoleElementList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, RoleElement.class);
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
    public PageInfo<RoleElement> queryRoleElementPage(RoleElement roleElement) throws PendingException {
        try {
            // 对请求参数进行校验
            if (roleElement.getPageNo() <= 0 || roleElement.getPageSize() <= 0) {
                ResCode.roleElementDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(roleElement.getPageNo(), roleElement.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(roleElement.getOrderby())) {
                PageHelper.orderBy(roleElement.getOrderby());
            }
            // 转换成Domain对象
            RoleElementDomain cond = BeanMapping.map(roleElement, RoleElementDomain.class);
            // 调数据库接口查询列表
            List<RoleElementDomain> resultList = roleElementDao.queryRoleElementList(cond);
            // 生成分页对象
            PageInfo<RoleElementDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, RoleElement.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.roleElementDBError, "角色权限信息分页查询失败");
        }
    }

}
