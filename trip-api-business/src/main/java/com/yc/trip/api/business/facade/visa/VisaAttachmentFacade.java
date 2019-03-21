package com.yc.trip.api.business.facade.visa;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.visa.VisaAttachment;

/**
 * 签证附件信息相关接口
 * @author My-Toolkits
 * @since 2019-03-21 22:36
 */
public interface VisaAttachmentFacade {

    /**
     * 新增签证附件信息
     * @throws PendingException 
     */
    VisaAttachment addVisaAttachment(VisaAttachment visaAttachment) throws PendingException;

    /**
     * 修改签证附件信息
     * @throws PendingException 
     */
    VisaAttachment updateVisaAttachment(VisaAttachment visaAttachment) throws PendingException;
    
    /**
     * 查询签证附件信息
     * @throws PendingException 
     */
    VisaAttachment getVisaAttachment(VisaAttachment visaAttachment) throws PendingException;
    
    /**
     * 查询签证附件信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    VisaAttachment mustGet(VisaAttachment visaAttachment) throws PendingException;

    /**
     * 查询签证附件信息列表
     * @throws PendingException 
     */
    List<VisaAttachment> queryVisaAttachmentList(VisaAttachment visaAttachment) throws PendingException;

    /**
     * 查询签证附件信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<VisaAttachment> queryVisaAttachmentPage(VisaAttachment visaAttachment) throws PendingException;

}