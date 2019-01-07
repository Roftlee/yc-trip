package com.yc.trip.api.business.service.element;

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
import com.yc.trip.api.business.dao.element.ElementDao;
import com.yc.trip.api.business.dto.element.Element;
import com.yc.trip.api.business.query.element.ElementQuery;
import com.yc.trip.api.business.facade.element.ElementFacade;

/**
 * 权限信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-01-06 18:27
 */
@Service(version = "1.0.0")
public class ElementFacadeImpl extends AbstractDubboNativeService implements ElementFacade {

    @Autowired
    private ElementDao elementDao;

    /**
     * 新增权限信息
     * 
     * @throws PendingException
     */
    @Override
    public Element add(Element element) throws PendingException {
        try {
            // 新增时对各字段进行非空校验
            element.validateInsertFields();
            // 调数据库接口进行新增操作
            elementDao.add(element);
            // 将新增后回返回（包含自增主键值）
            return element;
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.elementDBError, "权限信息新增失败");
        }
    }

    /**
     * 修改权限信息
     * 
     * @throws PendingException
     */
    @Override
    public void update(Element element) throws PendingException {
        try {
            // 更新或删除操作时，不能所有参数都为空
            if (element.isAllFiledsNull()) {
                ResCode.elementDBParamInvalid.throwException("权限信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            elementDao.update(element);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.elementDBError, "权限信息更新失败");
        }
    }

    /**
     * 查询权限信息
     * 
     * @throws PendingException
     */
    @Override
    public Element get(ElementQuery elementQuery) throws PendingException {
        try {
            // 调数据库接口查询对象
            return elementDao.get(elementQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.elementDBError, "权限信息查询失败");
        }
    }
    
    /**
     * 查询权限信息信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public Element mustGet(ElementQuery elementQuery) throws PendingException {
        // 查询单位信息
        Element result = get(elementQuery);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.elementDBGetNull.throwException("未查询到权限信息");
        }
        return result;
    }

    /**
     * 查询权限信息列表
     * 
     * @throws PendingException
     */
    @Override
    public List<Element> queryList(ElementQuery elementQuery) throws PendingException {
        try {
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(elementQuery.getOrderby())) {
                PageHelper.orderBy(elementQuery.getOrderby());
            }
            // 调数据库接口查询列表
            return elementDao.queryList(elementQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.elementDBError, "权限信息列表查询失败");
        }
    }

    /**
     * 查询权限信息列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<Element> queryPage(ElementQuery elementQuery) throws PendingException {
        try {
            // 对请求参数进行校验
            if (elementQuery.getPageNo() <= 0 || elementQuery.getPageSize() <= 0) {
                ResCode.elementDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(elementQuery.getPageNo(), elementQuery.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(elementQuery.getOrderby())) {
                PageHelper.orderBy(elementQuery.getOrderby());
            }
            // 调数据库接口查询列表
            List<Element> resultList = elementDao.queryList(elementQuery);
            // 返回分页结果
            return new PageInfo<>(resultList);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.elementDBError, "权限信息分页查询失败");
        }
    }

}
