package com.yc.trip.api.business.dao.visa;

import java.util.List;

import com.yc.trip.api.business.dto.visa.VisaAttachment;
import com.yc.trip.api.business.query.visa.VisaAttachmentQuery;

/**
 * 签证附件信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 18:04
 *
 */
public interface VisaAttachmentDao {

    /**
     * 签证附件信息新增
     */
    void add(VisaAttachment visaAttachment);

    /**
     * 签证附件信息修改
     */
    void update(VisaAttachment visaAttachment);
    
    /**
     * 签证附件信息查询
     */
    VisaAttachment get(VisaAttachmentQuery visaAttachmentQuery);

    /**
     * 签证附件信息列表查询
     */
    List<VisaAttachment> queryList(VisaAttachmentQuery visaAttachmentQuery);

}