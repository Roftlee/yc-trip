package com.yc.trip.api.business.dao.element;

import java.util.List;

import com.yc.trip.api.business.bo.element.ElementDomain;

/**
 * 权限信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 21:36
 *
 */
public interface ElementDao {

    /**
     * 新增权限信息
     */
    void addElement(ElementDomain elementDomain);

    /**
     * 修改权限信息
     */
    void updateElement(ElementDomain elementDomain);
    
    /**
     * 查询权限信息
     */
    ElementDomain getElement(ElementDomain elementDomain);

    /**
     * 查询权限信息列表
     */
    List<ElementDomain> queryElementList(ElementDomain elementDomain);

}