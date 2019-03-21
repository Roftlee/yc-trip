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
import com.yc.trip.api.business.bo.purchase.PurchaseRecordDomain;
import com.yc.trip.api.business.dao.purchase.PurchaseRecordDao;
import com.yc.trip.api.business.dto.purchase.PurchaseRecord;
import com.yc.trip.api.business.facade.purchase.PurchaseRecordFacade;

/**
 * 服务购买信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:11
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
    public PurchaseRecord addPurchaseRecord(PurchaseRecord purchaseRecord) throws PendingException {
        try {
            // 转换成domain对象
            PurchaseRecordDomain cond = BeanMapping.map(purchaseRecord, PurchaseRecordDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.purchaseRecordDBParamInvalid.throwException("服务购买信息新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            purchaseRecordDao.addPurchaseRecord(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, PurchaseRecord.class);
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
    public PurchaseRecord updatePurchaseRecord(PurchaseRecord purchaseRecord) throws PendingException {
        try {
            // 转换成domain对象
            PurchaseRecordDomain cond = BeanMapping.map(purchaseRecord, PurchaseRecordDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.purchaseRecordDBParamInvalid.throwException("服务购买信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            purchaseRecordDao.updatePurchaseRecord(cond);
            return BeanMapping.map(cond, PurchaseRecord.class);
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
    public PurchaseRecord getPurchaseRecord(PurchaseRecord purchaseRecord) throws PendingException {
        try {
            // 转换成Domain对象
            PurchaseRecordDomain cond = BeanMapping.map(purchaseRecord, PurchaseRecordDomain.class);
            // 调数据库接口查询对象
            PurchaseRecordDomain resultBean = purchaseRecordDao.getPurchaseRecord(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, PurchaseRecord.class);
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
    public PurchaseRecord mustGet(PurchaseRecord purchaseRecord) throws PendingException {
        // 查询单位信息
        PurchaseRecord result = getPurchaseRecord(purchaseRecord);
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
    public List<PurchaseRecord> queryPurchaseRecordList(PurchaseRecord purchaseRecord) throws PendingException {
        try {
            // 转换成Domain对象
            PurchaseRecordDomain cond = BeanMapping.map(purchaseRecord, PurchaseRecordDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(purchaseRecord.getOrderby())) {
                PageHelper.orderBy(purchaseRecord.getOrderby());
            }
            // 调数据库接口查询列表
            List<PurchaseRecordDomain> resultList = purchaseRecordDao.queryPurchaseRecordList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, PurchaseRecord.class);
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
    public PageInfo<PurchaseRecord> queryPurchaseRecordPage(PurchaseRecord purchaseRecord) throws PendingException {
        try {
            // 对请求参数进行校验
            if (purchaseRecord.getPageNo() <= 0 || purchaseRecord.getPageSize() <= 0) {
                ResCode.purchaseRecordDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(purchaseRecord.getPageNo(), purchaseRecord.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(purchaseRecord.getOrderby())) {
                PageHelper.orderBy(purchaseRecord.getOrderby());
            }
            // 转换成Domain对象
            PurchaseRecordDomain cond = BeanMapping.map(purchaseRecord, PurchaseRecordDomain.class);
            // 调数据库接口查询列表
            List<PurchaseRecordDomain> resultList = purchaseRecordDao.queryPurchaseRecordList(cond);
            // 生成分页对象
            PageInfo<PurchaseRecordDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, PurchaseRecord.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.purchaseRecordDBError, "服务购买信息分页查询失败");
        }
    }

}
