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
import com.yc.trip.api.business.dao.visa.VisaAttachmentDao;
import com.yc.trip.api.business.dto.visa.VisaAttachment;
import com.yc.trip.api.business.query.visa.VisaAttachmentQuery;
import com.yc.trip.api.business.facade.visa.VisaAttachmentFacade;

/**
 * 签证附件信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-01-06 18:04
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
    public VisaAttachment add(VisaAttachment visaAttachment) throws PendingException {
        try {
            // 新增时对各字段进行非空校验
            visaAttachment.validateInsertFields();
            // 调数据库接口进行新增操作
            visaAttachmentDao.add(visaAttachment);
            // 将新增后回返回（包含自增主键值）
            return visaAttachment;
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
    public void update(VisaAttachment visaAttachment) throws PendingException {
        try {
            // 更新或删除操作时，不能所有参数都为空
            if (visaAttachment.isAllFiledsNull()) {
                ResCode.visaAttachmentDBParamInvalid.throwException("签证附件信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            visaAttachmentDao.update(visaAttachment);
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
    public VisaAttachment get(VisaAttachmentQuery visaAttachmentQuery) throws PendingException {
        try {
            // 调数据库接口查询对象
            return visaAttachmentDao.get(visaAttachmentQuery);
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
    public VisaAttachment mustGet(VisaAttachmentQuery visaAttachmentQuery) throws PendingException {
        // 查询单位信息
        VisaAttachment result = get(visaAttachmentQuery);
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
    public List<VisaAttachment> queryList(VisaAttachmentQuery visaAttachmentQuery) throws PendingException {
        try {
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(visaAttachmentQuery.getOrderby())) {
                PageHelper.orderBy(visaAttachmentQuery.getOrderby());
            }
            // 调数据库接口查询列表
            return visaAttachmentDao.queryList(visaAttachmentQuery);
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
    public PageInfo<VisaAttachment> queryPage(VisaAttachmentQuery visaAttachmentQuery) throws PendingException {
        try {
            // 对请求参数进行校验
            if (visaAttachmentQuery.getPageNo() <= 0 || visaAttachmentQuery.getPageSize() <= 0) {
                ResCode.visaAttachmentDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(visaAttachmentQuery.getPageNo(), visaAttachmentQuery.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(visaAttachmentQuery.getOrderby())) {
                PageHelper.orderBy(visaAttachmentQuery.getOrderby());
            }
            // 调数据库接口查询列表
            List<VisaAttachment> resultList = visaAttachmentDao.queryList(visaAttachmentQuery);
            // 返回分页结果
            return new PageInfo<>(resultList);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.visaAttachmentDBError, "签证附件信息分页查询失败");
        }
    }

}
