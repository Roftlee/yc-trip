package com.yc.trip.api.business.service.guide;

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
import com.yc.trip.api.business.bo.guide.TripGuideDomain;
import com.yc.trip.api.business.dao.guide.TripGuideDao;
import com.yc.trip.api.business.dto.guide.TripGuide;
import com.yc.trip.api.business.facade.guide.TripGuideFacade;

/**
 * 旅游攻略信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:27
 */
@Service(version = "1.0.0")
public class TripGuideFacadeImpl extends AbstractDubboNativeService implements TripGuideFacade {

    @Autowired
    private TripGuideDao tripGuideDao;

    /**
     * 新增旅游攻略信息
     * 
     * @throws PendingException
     */
    @Override
    public TripGuide addTripGuide(TripGuide tripGuide) throws PendingException {
        try {
            // 转换成domain对象
            TripGuideDomain cond = BeanMapping.map(tripGuide, TripGuideDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.tripGuideDBParamInvalid.throwException("旅游攻略信息新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            tripGuideDao.addTripGuide(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, TripGuide.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.tripGuideDBError, "旅游攻略信息新增失败");
        }
    }

    /**
     * 修改旅游攻略信息
     * 
     * @throws PendingException
     */
    @Override
    public TripGuide updateTripGuide(TripGuide tripGuide) throws PendingException {
        try {
            // 转换成domain对象
            TripGuideDomain cond = BeanMapping.map(tripGuide, TripGuideDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.tripGuideDBParamInvalid.throwException("旅游攻略信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            tripGuideDao.updateTripGuide(cond);
            return BeanMapping.map(cond, TripGuide.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.tripGuideDBError, "旅游攻略信息更新失败");
        }
    }

    /**
     * 查询旅游攻略信息
     * 
     * @throws PendingException
     */
    @Override
    public TripGuide getTripGuide(TripGuide tripGuide) throws PendingException {
        try {
            // 转换成Domain对象
            TripGuideDomain cond = BeanMapping.map(tripGuide, TripGuideDomain.class);
            // 调数据库接口查询对象
            TripGuideDomain resultBean = tripGuideDao.getTripGuide(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, TripGuide.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.tripGuideDBError, "旅游攻略信息查询失败");
        }
    }
    
    /**
     * 查询旅游攻略信息信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public TripGuide mustGet(TripGuide tripGuide) throws PendingException {
        // 查询单位信息
        TripGuide result = getTripGuide(tripGuide);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.tripGuideDBGetNull.throwException("未查询到旅游攻略信息");
        }
        return result;
    }

    /**
     * 查询旅游攻略信息列表
     * 
     * @throws PendingException
     */
    @Override
    public List<TripGuide> queryTripGuideList(TripGuide tripGuide) throws PendingException {
        try {
            // 转换成Domain对象
            TripGuideDomain cond = BeanMapping.map(tripGuide, TripGuideDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(tripGuide.getOrderby())) {
                PageHelper.orderBy(tripGuide.getOrderby());
            }
            // 调数据库接口查询列表
            List<TripGuideDomain> resultList = tripGuideDao.queryTripGuideList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, TripGuide.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.tripGuideDBError, "旅游攻略信息列表查询失败");
        }
    }

    /**
     * 查询旅游攻略信息列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<TripGuide> queryTripGuidePage(TripGuide tripGuide) throws PendingException {
        try {
            // 对请求参数进行校验
            if (tripGuide.getPageNo() <= 0 || tripGuide.getPageSize() <= 0) {
                ResCode.tripGuideDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(tripGuide.getPageNo(), tripGuide.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(tripGuide.getOrderby())) {
                PageHelper.orderBy(tripGuide.getOrderby());
            }
            // 转换成Domain对象
            TripGuideDomain cond = BeanMapping.map(tripGuide, TripGuideDomain.class);
            // 调数据库接口查询列表
            List<TripGuideDomain> resultList = tripGuideDao.queryTripGuideList(cond);
            // 生成分页对象
            PageInfo<TripGuideDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, TripGuide.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.tripGuideDBError, "旅游攻略信息分页查询失败");
        }
    }

}
