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
import com.yc.trip.api.business.bo.trip.TripInteractDomain;
import com.yc.trip.api.business.dao.trip.TripInteractDao;
import com.yc.trip.api.business.dto.trip.TripInteract;
import com.yc.trip.api.business.facade.trip.TripInteractFacade;

/**
 * 旅游互动信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:29
 */
@Service(version = "1.0.0")
public class TripInteractFacadeImpl extends AbstractDubboNativeService implements TripInteractFacade {

    @Autowired
    private TripInteractDao tripInteractDao;

    /**
     * 新增旅游互动信息
     * 
     * @throws PendingException
     */
    @Override
    public TripInteract addTripInteract(TripInteract tripInteract) throws PendingException {
        try {
            // 转换成domain对象
            TripInteractDomain cond = BeanMapping.map(tripInteract, TripInteractDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.tripInteractDBParamInvalid.throwException("旅游互动信息新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            tripInteractDao.addTripInteract(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, TripInteract.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.tripInteractDBError, "旅游互动信息新增失败");
        }
    }

    /**
     * 修改旅游互动信息
     * 
     * @throws PendingException
     */
    @Override
    public TripInteract updateTripInteract(TripInteract tripInteract) throws PendingException {
        try {
            // 转换成domain对象
            TripInteractDomain cond = BeanMapping.map(tripInteract, TripInteractDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.tripInteractDBParamInvalid.throwException("旅游互动信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            tripInteractDao.updateTripInteract(cond);
            return BeanMapping.map(cond, TripInteract.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.tripInteractDBError, "旅游互动信息更新失败");
        }
    }

    /**
     * 查询旅游互动信息
     * 
     * @throws PendingException
     */
    @Override
    public TripInteract getTripInteract(TripInteract tripInteract) throws PendingException {
        try {
            // 转换成Domain对象
            TripInteractDomain cond = BeanMapping.map(tripInteract, TripInteractDomain.class);
            // 调数据库接口查询对象
            TripInteractDomain resultBean = tripInteractDao.getTripInteract(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, TripInteract.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.tripInteractDBError, "旅游互动信息查询失败");
        }
    }
    
    /**
     * 查询旅游互动信息信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public TripInteract mustGet(TripInteract tripInteract) throws PendingException {
        // 查询单位信息
        TripInteract result = getTripInteract(tripInteract);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.tripInteractDBGetNull.throwException("未查询到旅游互动信息");
        }
        return result;
    }

    /**
     * 查询旅游互动信息列表
     * 
     * @throws PendingException
     */
    @Override
    public List<TripInteract> queryTripInteractList(TripInteract tripInteract) throws PendingException {
        try {
            // 转换成Domain对象
            TripInteractDomain cond = BeanMapping.map(tripInteract, TripInteractDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(tripInteract.getOrderby())) {
                PageHelper.orderBy(tripInteract.getOrderby());
            }
            // 调数据库接口查询列表
            List<TripInteractDomain> resultList = tripInteractDao.queryTripInteractList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, TripInteract.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.tripInteractDBError, "旅游互动信息列表查询失败");
        }
    }

    /**
     * 查询旅游互动信息列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<TripInteract> queryTripInteractPage(TripInteract tripInteract) throws PendingException {
        try {
            // 对请求参数进行校验
            if (tripInteract.getPageNo() <= 0 || tripInteract.getPageSize() <= 0) {
                ResCode.tripInteractDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(tripInteract.getPageNo(), tripInteract.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(tripInteract.getOrderby())) {
                PageHelper.orderBy(tripInteract.getOrderby());
            }
            // 转换成Domain对象
            TripInteractDomain cond = BeanMapping.map(tripInteract, TripInteractDomain.class);
            // 调数据库接口查询列表
            List<TripInteractDomain> resultList = tripInteractDao.queryTripInteractList(cond);
            // 生成分页对象
            PageInfo<TripInteractDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, TripInteract.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.tripInteractDBError, "旅游互动信息分页查询失败");
        }
    }

}
