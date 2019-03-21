package com.yc.trip.api.business.facade.visa;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.visa.Visa;

/**
 * 签证信息相关接口
 * @author My-Toolkits
 * @since 2019-03-21 22:35
 */
public interface VisaFacade {

    /**
     * 新增签证信息
     * @throws PendingException 
     */
    Visa addVisa(Visa visa) throws PendingException;

    /**
     * 修改签证信息
     * @throws PendingException 
     */
    Visa updateVisa(Visa visa) throws PendingException;
    
    /**
     * 查询签证信息
     * @throws PendingException 
     */
    Visa getVisa(Visa visa) throws PendingException;
    
    /**
     * 查询签证信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    Visa mustGet(Visa visa) throws PendingException;

    /**
     * 查询签证信息列表
     * @throws PendingException 
     */
    List<Visa> queryVisaList(Visa visa) throws PendingException;

    /**
     * 查询签证信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<Visa> queryVisaPage(Visa visa) throws PendingException;

}