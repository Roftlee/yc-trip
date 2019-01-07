package com.yc.trip.api.business.facade.element;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.element.Element;
import com.yc.trip.api.business.query.element.ElementQuery;

/**
 * 权限信息相关接口
 * @author My-Toolkits
 * @since 2019-01-06 18:27
 */
public interface ElementFacade {

    /**
     * 新增权限信息
     * @throws PendingException 
     */
    Element add(Element element) throws PendingException;

    /**
     * 修改权限信息
     * @throws PendingException 
     */
    void update(Element element) throws PendingException;
    
    /**
     * 查询权限信息
     * @throws PendingException 
     */
    Element get(ElementQuery elementQuery) throws PendingException;
    
    /**
     * 查询权限信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    Element mustGet(ElementQuery elementQuery) throws PendingException;

    /**
     * 查询权限信息列表
     * @throws PendingException 
     */
    List<Element> queryList(ElementQuery elementQuery) throws PendingException;

    /**
     * 查询权限信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<Element> queryPage(ElementQuery elementQuery) throws PendingException;

}