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
import com.yc.trip.api.business.bo.element.ElementDomain;
import com.yc.trip.api.business.dao.element.ElementDao;
import com.yc.trip.api.business.dto.element.Element;
import com.yc.trip.api.business.facade.element.ElementFacade;

/**
 * 权限信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 21:36
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
    public Element addElement(Element element) throws PendingException {
        try {
            // 转换成domain对象
            ElementDomain cond = BeanMapping.map(element, ElementDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.elementDBParamInvalid.throwException("权限信息新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            elementDao.addElement(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, Element.class);
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
    public Element updateElement(Element element) throws PendingException {
        try {
            // 转换成domain对象
            ElementDomain cond = BeanMapping.map(element, ElementDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.elementDBParamInvalid.throwException("权限信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            elementDao.updateElement(cond);
            return BeanMapping.map(cond, Element.class);
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
    public Element getElement(Element element) throws PendingException {
        try {
            // 转换成Domain对象
            ElementDomain cond = BeanMapping.map(element, ElementDomain.class);
            // 调数据库接口查询对象
            ElementDomain resultBean = elementDao.getElement(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, Element.class);
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
    public Element mustGet(Element element) throws PendingException {
        // 查询单位信息
        Element result = getElement(element);
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
    public List<Element> queryElementList(Element element) throws PendingException {
        try {
            // 转换成Domain对象
            ElementDomain cond = BeanMapping.map(element, ElementDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(element.getOrderby())) {
                PageHelper.orderBy(element.getOrderby());
            }
            // 调数据库接口查询列表
            List<ElementDomain> resultList = elementDao.queryElementList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, Element.class);
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
    public PageInfo<Element> queryElementPage(Element element) throws PendingException {
        try {
            // 对请求参数进行校验
            if (element.getPageNo() <= 0 || element.getPageSize() <= 0) {
                ResCode.elementDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(element.getPageNo(), element.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(element.getOrderby())) {
                PageHelper.orderBy(element.getOrderby());
            }
            // 转换成Domain对象
            ElementDomain cond = BeanMapping.map(element, ElementDomain.class);
            // 调数据库接口查询列表
            List<ElementDomain> resultList = elementDao.queryElementList(cond);
            // 生成分页对象
            PageInfo<ElementDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, Element.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.elementDBError, "权限信息分页查询失败");
        }
    }

}
