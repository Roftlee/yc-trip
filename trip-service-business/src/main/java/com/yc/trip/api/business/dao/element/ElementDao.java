package com.yc.trip.api.business.dao.element;

import java.util.List;

import com.yc.trip.api.business.dto.element.Element;
import com.yc.trip.api.business.query.element.ElementQuery;

/**
 * 权限信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 18:27
 *
 */
public interface ElementDao {

    /**
     * 权限信息新增
     */
    void add(Element element);

    /**
     * 权限信息修改
     */
    void update(Element element);
    
    /**
     * 权限信息查询
     */
    Element get(ElementQuery elementQuery);

    /**
     * 权限信息列表查询
     */
    List<Element> queryList(ElementQuery elementQuery);

}