package com.yc.trip.api.business.dao.visa;

import java.util.List;

import com.yc.trip.api.business.bo.visa.VisaAttachmentDomain;

/**
 * 签证附件信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:36
 *
 */
public interface VisaAttachmentDao {

    /**
     * 新增签证附件信息
     */
    void addVisaAttachment(VisaAttachmentDomain visaAttachmentDomain);

    /**
     * 修改签证附件信息
     */
    void updateVisaAttachment(VisaAttachmentDomain visaAttachmentDomain);
    
    /**
     * 查询签证附件信息
     */
    VisaAttachmentDomain getVisaAttachment(VisaAttachmentDomain visaAttachmentDomain);

    /**
     * 查询签证附件信息列表
     */
    List<VisaAttachmentDomain> queryVisaAttachmentList(VisaAttachmentDomain visaAttachmentDomain);

}