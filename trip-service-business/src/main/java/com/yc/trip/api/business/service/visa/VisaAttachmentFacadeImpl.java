package com.yc.trip.api.business.service.visa;

import java.util.List;


import org.go.api.core.integration.AbstractDubboNativeService;
import org.go.api.core.util.BeanMapping;
import org.go.framework.core.exception.PendingException;
import org.go.framework.util.common.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.yc.trip.api.core.constants.ResCode;
import com.yc.trip.api.business.bo.visa.VisaAttachmentDomain;
import com.yc.trip.api.business.dao.visa.VisaAttachmentDao;
import com.yc.trip.api.business.dto.visa.VisaAttachment;
import com.yc.trip.api.business.facade.visa.VisaAttachmentFacade;

/**
 * 签证附件信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:36
 */
@Service(version = "1.0.0")
public class VisaAttachmentFacadeImpl extends AbstractDubboNativeService implements VisaAttachmentFacade {

    @Autowired
    private VisaAttachmentDao visaAttachmentDao;

    /**
     * 新增签证附件信息
     * 
     * @throws PendingException
     */
    @Override
    public VisaAttachment addVisaAttachment(VisaAttachment visaAttachment) throws PendingException {
        try {
            // 转换成domain对象
            VisaAttachmentDomain cond = BeanMapping.map(visaAttachment, VisaAttachmentDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.visaAttachmentDBParamInvalid.throwException("签证附件信息新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            visaAttachmentDao.addVisaAttachment(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, VisaAttachment.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.visaAttachmentDBError, "签证附件信息新增失败");
        }
    }

    /**
     * 修改签证附件信息
     * 
     * @throws PendingException
     */
    @Override
    public VisaAttachment updateVisaAttachment(VisaAttachment visaAttachment) throws PendingException {
        try {
            // 转换成domain对象
            VisaAttachmentDomain cond = BeanMapping.map(visaAttachment, VisaAttachmentDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.visaAttachmentDBParamInvalid.throwException("签证附件信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            visaAttachmentDao.updateVisaAttachment(cond);
            return BeanMapping.map(cond, VisaAttachment.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.visaAttachmentDBError, "签证附件信息更新失败");
        }
    }

    /**
     * 查询签证附件信息
     * 
     * @throws PendingException
     */
    @Override
    public VisaAttachment getVisaAttachment(VisaAttachment visaAttachment) throws PendingException {
        try {
            // 转换成Domain对象
            VisaAttachmentDomain cond = BeanMapping.map(visaAttachment, VisaAttachmentDomain.class);
            // 调数据库接口查询对象
            VisaAttachmentDomain resultBean = visaAttachmentDao.getVisaAttachment(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, VisaAttachment.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.visaAttachmentDBError, "签证附件信息查询失败");
        }
    }
    
    /**
     * 查询签证附件信息信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public VisaAttachment mustGet(VisaAttachment visaAttachment) throws PendingException {
        // 查询单位信息
        VisaAttachment result = getVisaAttachment(visaAttachment);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.visaAttachmentDBGetNull.throwException("未查询到签证附件信息");
        }
        return result;
    }

    /**
     * 查询签证附件信息列表
     * 
     * @throws PendingException
     */
    @Override
    public List<VisaAttachment> queryVisaAttachmentList(VisaAttachment visaAttachment) throws PendingException {
        try {
            // 转换成Domain对象
            VisaAttachmentDomain cond = BeanMapping.map(visaAttachment, VisaAttachmentDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(visaAttachment.getOrderby())) {
                PageHelper.orderBy(visaAttachment.getOrderby());
            }
            // 调数据库接口查询列表
            List<VisaAttachmentDomain> resultList = visaAttachmentDao.queryVisaAttachmentList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, VisaAttachment.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.visaAttachmentDBError, "签证附件信息列表查询失败");
        }
    }

    /**
     * 查询签证附件信息列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<VisaAttachment> queryVisaAttachmentPage(VisaAttachment visaAttachment) throws PendingException {
        try {
            // 对请求参数进行校验
            if (visaAttachment.getPageNo() <= 0 || visaAttachment.getPageSize() <= 0) {
                ResCode.visaAttachmentDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(visaAttachment.getPageNo(), visaAttachment.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(visaAttachment.getOrderby())) {
                PageHelper.orderBy(visaAttachment.getOrderby());
            }
            // 转换成Domain对象
            VisaAttachmentDomain cond = BeanMapping.map(visaAttachment, VisaAttachmentDomain.class);
            // 调数据库接口查询列表
            List<VisaAttachmentDomain> resultList = visaAttachmentDao.queryVisaAttachmentList(cond);
            // 生成分页对象
            PageInfo<VisaAttachmentDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, VisaAttachment.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.visaAttachmentDBError, "签证附件信息分页查询失败");
        }
    }

}
