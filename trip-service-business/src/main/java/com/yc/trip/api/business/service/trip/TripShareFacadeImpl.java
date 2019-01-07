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
import com.yc.trip.api.business.dao.trip.TripShareDao;
import com.yc.trip.api.business.dto.trip.TripShare;
import com.yc.trip.api.business.query.trip.TripShareQuery;
import com.yc.trip.api.business.facade.trip.TripShareFacade;

/**
 * 旅游分享信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:59
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
    public TripShare add(TripShare tripShare) throws PendingException {
        try {
            // 新增时对各字段进行非空校验
            tripShare.validateInsertFields();
            // 调数据库接口进行新增操作
            tripShareDao.add(tripShare);
            // 将新增后回返回（包含自增主键值）
            return tripShare;
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
    public void update(TripShare tripShare) throws PendingException {
        try {
            // 更新或删除操作时，不能所有参数都为空
            if (tripShare.isAllFiledsNull()) {
                ResCode.tripShareDBParamInvalid.throwException("旅游分享信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            tripShareDao.update(tripShare);
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
    public TripShare get(TripShareQuery tripShareQuery) throws PendingException {
        try {
            // 调数据库接口查询对象
            return tripShareDao.get(tripShareQuery);
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
    public TripShare mustGet(TripShareQuery tripShareQuery) throws PendingException {
        // 查询单位信息
        TripShare result = get(tripShareQuery);
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
    public List<TripShare> queryList(TripShareQuery tripShareQuery) throws PendingException {
        try {
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(tripShareQuery.getOrderby())) {
                PageHelper.orderBy(tripShareQuery.getOrderby());
            }
            // 调数据库接口查询列表
            return tripShareDao.queryList(tripShareQuery);
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
    public PageInfo<TripShare> queryPage(TripShareQuery tripShareQuery) throws PendingException {
        try {
            // 对请求参数进行校验
            if (tripShareQuery.getPageNo() <= 0 || tripShareQuery.getPageSize() <= 0) {
                ResCode.tripShareDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(tripShareQuery.getPageNo(), tripShareQuery.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(tripShareQuery.getOrderby())) {
                PageHelper.orderBy(tripShareQuery.getOrderby());
            }
            // 调数据库接口查询列表
            List<TripShare> resultList = tripShareDao.queryList(tripShareQuery);
            // 返回分页结果
            return new PageInfo<>(resultList);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.tripShareDBError, "旅游分享信息分页查询失败");
        }
    }

}
