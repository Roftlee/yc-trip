package com.yc.trip.api.business.service.trip;

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
import com.yc.trip.api.business.bo.trip.TripShareDomain;
import com.yc.trip.api.business.dao.trip.TripShareDao;
import com.yc.trip.api.business.dto.trip.TripShare;
import com.yc.trip.api.business.facade.trip.TripShareFacade;

/**
 * 旅游分享信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:30
 */
@Service(version = "1.0.0")
public class TripShareFacadeImpl extends AbstractDubboNativeService implements TripShareFacade {

    @Autowired
    private TripShareDao tripShareDao;

    /**
     * 新增旅游分享信息
     * 
     * @throws PendingException
     */
    @Override
    public TripShare addTripShare(TripShare tripShare) throws PendingException {
        try {
            // 转换成domain对象
            TripShareDomain cond = BeanMapping.map(tripShare, TripShareDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.tripShareDBParamInvalid.throwException("旅游分享信息新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            tripShareDao.addTripShare(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, TripShare.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.tripShareDBError, "旅游分享信息新增失败");
        }
    }

    /**
     * 修改旅游分享信息
     * 
     * @throws PendingException
     */
    @Override
    public TripShare updateTripShare(TripShare tripShare) throws PendingException {
        try {
            // 转换成domain对象
            TripShareDomain cond = BeanMapping.map(tripShare, TripShareDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.tripShareDBParamInvalid.throwException("旅游分享信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            tripShareDao.updateTripShare(cond);
            return BeanMapping.map(cond, TripShare.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.tripShareDBError, "旅游分享信息更新失败");
        }
    }

    /**
     * 查询旅游分享信息
     * 
     * @throws PendingException
     */
    @Override
    public TripShare getTripShare(TripShare tripShare) throws PendingException {
        try {
            // 转换成Domain对象
            TripShareDomain cond = BeanMapping.map(tripShare, TripShareDomain.class);
            // 调数据库接口查询对象
            TripShareDomain resultBean = tripShareDao.getTripShare(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, TripShare.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.tripShareDBError, "旅游分享信息查询失败");
        }
    }
    
    /**
     * 查询旅游分享信息信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public TripShare mustGet(TripShare tripShare) throws PendingException {
        // 查询单位信息
        TripShare result = getTripShare(tripShare);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.tripShareDBGetNull.throwException("未查询到旅游分享信息");
        }
        return result;
    }

    /**
     * 查询旅游分享信息列表
     * 
     * @throws PendingException
     */
    @Override
    public List<TripShare> queryTripShareList(TripShare tripShare) throws PendingException {
        try {
            // 转换成Domain对象
            TripShareDomain cond = BeanMapping.map(tripShare, TripShareDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(tripShare.getOrderby())) {
                PageHelper.orderBy(tripShare.getOrderby());
            }
            // 调数据库接口查询列表
            List<TripShareDomain> resultList = tripShareDao.queryTripShareList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, TripShare.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.tripShareDBError, "旅游分享信息列表查询失败");
        }
    }

    /**
     * 查询旅游分享信息列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<TripShare> queryTripSharePage(TripShare tripShare) throws PendingException {
        try {
            // 对请求参数进行校验
            if (tripShare.getPageNo() <= 0 || tripShare.getPageSize() <= 0) {
                ResCode.tripShareDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(tripShare.getPageNo(), tripShare.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(tripShare.getOrderby())) {
                PageHelper.orderBy(tripShare.getOrderby());
            }
            // 转换成Domain对象
            TripShareDomain cond = BeanMapping.map(tripShare, TripShareDomain.class);
            // 调数据库接口查询列表
            List<TripShareDomain> resultList = tripShareDao.queryTripShareList(cond);
            // 生成分页对象
            PageInfo<TripShareDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, TripShare.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.tripShareDBError, "旅游分享信息分页查询失败");
        }
    }

}
