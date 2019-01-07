package com.yc.trip.api.business.facade.visa;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.visa.Visa;
import com.yc.trip.api.business.query.visa.VisaQuery;

/**
 * 签证信息相关接口
 * @author My-Toolkits
 * @since 2019-01-06 18:03
 */
public interface VisaFacade {

    /**
     * 新增签证信息
     * @throws PendingException 
     */
    Visa add(Visa visa) throws PendingException;

    /**
     * 修改签证信息
     * @throws PendingException 
     */
    void update(Visa visa) throws PendingException;
    
    /**
     * 查询签证信息
     * @throws PendingException 
     */
    Visa get(VisaQuery visaQuery) throws PendingException;
    
    /**
     * 查询签证信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    Visa mustGet(VisaQuery visaQuery) throws PendingException;

    /**
     * 查询签证信息列表
     * @throws PendingException 
     */
    List<Visa> queryList(VisaQuery visaQuery) throws PendingException;

    /**
     * 查询签证信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<Visa> queryPage(VisaQuery visaQuery) throws PendingException;

}