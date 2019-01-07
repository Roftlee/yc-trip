package com.yc.trip.api.business.service.purchase;

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
import com.yc.trip.api.business.dao.purchase.PurchaseRecordDao;
import com.yc.trip.api.business.dto.purchase.PurchaseRecord;
import com.yc.trip.api.business.query.purchase.PurchaseRecordQuery;
import com.yc.trip.api.business.facade.purchase.PurchaseRecordFacade;

/**
 * 服务购买信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:27
 */
@Service(version = "1.0.0")
public class PurchaseRecordFacadeImpl extends AbstractDubboNativeService implements PurchaseRecordFacade {

    @Autowired
    private PurchaseRecordDao purchaseRecordDao;

    /**
     * 新增服务购买信息
     * 
     * @throws PendingException
     */
    @Override
    public PurchaseRecord add(PurchaseRecord purchaseRecord) throws PendingException {
        try {
            // 新增时对各字段进行非空校验
            purchaseRecord.validateInsertFields();
            // 调数据库接口进行新增操作
            purchaseRecordDao.add(purchaseRecord);
            // 将新增后回返回（包含自增主键值）
            return purchaseRecord;
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.purchaseRecordDBError, "服务购买信息新增失败");
        }
    }

    /**
     * 修改服务购买信息
     * 
     * @throws PendingException
     */
    @Override
    public void update(PurchaseRecord purchaseRecord) throws PendingException {
        try {
            // 更新或删除操作时，不能所有参数都为空
            if (purchaseRecord.isAllFiledsNull()) {
                ResCode.purchaseRecordDBParamInvalid.throwException("服务购买信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            purchaseRecordDao.update(purchaseRecord);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.purchaseRecordDBError, "服务购买信息更新失败");
        }
    }

    /**
     * 查询服务购买信息
     * 
     * @throws PendingException
     */
    @Override
    public PurchaseRecord get(PurchaseRecordQuery purchaseRecordQuery) throws PendingException {
        try {
            // 调数据库接口查询对象
            return purchaseRecordDao.get(purchaseRecordQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.purchaseRecordDBError, "服务购买信息查询失败");
        }
    }
    
    /**
     * 查询服务购买信息信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public PurchaseRecord mustGet(PurchaseRecordQuery purchaseRecordQuery) throws PendingException {
        // 查询单位信息
        PurchaseRecord result = get(purchaseRecordQuery);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.purchaseRecordDBGetNull.throwException("未查询到服务购买信息");
        }
        return result;
    }

    /**
     * 查询服务购买信息列表
     * 
     * @throws PendingException
     */
    @Override
    public List<PurchaseRecord> queryList(PurchaseRecordQuery purchaseRecordQuery) throws PendingException {
        try {
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(purchaseRecordQuery.getOrderby())) {
                PageHelper.orderBy(purchaseRecordQuery.getOrderby());
            }
            // 调数据库接口查询列表
            return purchaseRecordDao.queryList(purchaseRecordQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.purchaseRecordDBError, "服务购买信息列表查询失败");
        }
    }

    /**
     * 查询服务购买信息列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<PurchaseRecord> queryPage(PurchaseRecordQuery purchaseRecordQuery) throws PendingException {
        try {
            // 对请求参数进行校验
            if (purchaseRecordQuery.getPageNo() <= 0 || purchaseRecordQuery.getPageSize() <= 0) {
                ResCode.purchaseRecordDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(purchaseRecordQuery.getPageNo(), purchaseRecordQuery.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(purchaseRecordQuery.getOrderby())) {
                PageHelper.orderBy(purchaseRecordQuery.getOrderby());
            }
            // 调数据库接口查询列表
            List<PurchaseRecord> resultList = purchaseRecordDao.queryList(purchaseRecordQuery);
            // 返回分页结果
            return new PageInfo<>(resultList);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.purchaseRecordDBError, "服务购买信息分页查询失败");
        }
    }

}
