package com.yc.trip.api.business.facade.element;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.element.Element;

/**
 * 权限信息相关接口
 * @author My-Toolkits
 * @since 2019-03-21 21:36
 */
public interface ElementFacade {

    /**
     * 新增权限信息
     * @throws PendingException 
     */
    Element addElement(Element element) throws PendingException;

    /**
     * 修改权限信息
     * @throws PendingException 
     */
    Element updateElement(Element element) throws PendingException;
    
    /**
     * 查询权限信息
     * @throws PendingException 
     */
    Element getElement(Element element) throws PendingException;
    
    /**
     * 查询权限信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    Element mustGet(Element element) throws PendingException;

    /**
     * 查询权限信息列表
     * @throws PendingException 
     */
    List<Element> queryElementList(Element element) throws PendingException;

    /**
     * 查询权限信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<Element> queryElementPage(Element element) throws PendingException;

}