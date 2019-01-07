package com.yc.trip.api.business.facade.visa;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.visa.VisaSort;
import com.yc.trip.api.business.query.visa.VisaSortQuery;

/**
 * 签证分类信息相关接口
 * @author My-Toolkits
 * @since 2019-01-06 18:06
 */
public interface VisaSortFacade {

    /**
     * 新增签证分类信息
     * @throws PendingException 
     */
    VisaSort add(VisaSort visaSort) throws PendingException;

    /**
     * 修改签证分类信息
     * @throws PendingException 
     */
    void update(VisaSort visaSort) throws PendingException;
    
    /**
     * 查询签证分类信息
     * @throws PendingException 
     */
    VisaSort get(VisaSortQuery visaSortQuery) throws PendingException;
    
    /**
     * 查询签证分类信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    VisaSort mustGet(VisaSortQuery visaSortQuery) throws PendingException;

    /**
     * 查询签证分类信息列表
     * @throws PendingException 
     */
    List<VisaSort> queryList(VisaSortQuery visaSortQuery) throws PendingException;

    /**
     * 查询签证分类信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<VisaSort> queryPage(VisaSortQuery visaSortQuery) throws PendingException;

}