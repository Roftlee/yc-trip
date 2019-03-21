package com.yc.trip.api.business.facade.visa;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.visa.VisaSort;

/**
 * 签证分类信息相关接口
 * @author My-Toolkits
 * @since 2019-03-21 22:37
 */
public interface VisaSortFacade {

    /**
     * 新增签证分类信息
     * @throws PendingException 
     */
    VisaSort addVisaSort(VisaSort visaSort) throws PendingException;

    /**
     * 修改签证分类信息
     * @throws PendingException 
     */
    VisaSort updateVisaSort(VisaSort visaSort) throws PendingException;
    
    /**
     * 查询签证分类信息
     * @throws PendingException 
     */
    VisaSort getVisaSort(VisaSort visaSort) throws PendingException;
    
    /**
     * 查询签证分类信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    VisaSort mustGet(VisaSort visaSort) throws PendingException;

    /**
     * 查询签证分类信息列表
     * @throws PendingException 
     */
    List<VisaSort> queryVisaSortList(VisaSort visaSort) throws PendingException;

    /**
     * 查询签证分类信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<VisaSort> queryVisaSortPage(VisaSort visaSort) throws PendingException;

}