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
import com.yc.trip.api.business.dao.trip.TripInteractDao;
import com.yc.trip.api.business.dto.trip.TripInteract;
import com.yc.trip.api.business.query.trip.TripInteractQuery;
import com.yc.trip.api.business.facade.trip.TripInteractFacade;

/**
 * 旅游互动信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:58
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
    public TripInteract add(TripInteract tripInteract) throws PendingException {
        try {
            // 新增时对各字段进行非空校验
            tripInteract.validateInsertFields();
            // 调数据库接口进行新增操作
            tripInteractDao.add(tripInteract);
            // 将新增后回返回（包含自增主键值）
            return tripInteract;
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
    public void update(TripInteract tripInteract) throws PendingException {
        try {
            // 更新或删除操作时，不能所有参数都为空
            if (tripInteract.isAllFiledsNull()) {
                ResCode.tripInteractDBParamInvalid.throwException("旅游互动信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            tripInteractDao.update(tripInteract);
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
    public TripInteract get(TripInteractQuery tripInteractQuery) throws PendingException {
        try {
            // 调数据库接口查询对象
            return tripInteractDao.get(tripInteractQuery);
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
    public TripInteract mustGet(TripInteractQuery tripInteractQuery) throws PendingException {
        // 查询单位信息
        TripInteract result = get(tripInteractQuery);
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
    public List<TripInteract> queryList(TripInteractQuery tripInteractQuery) throws PendingException {
        try {
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(tripInteractQuery.getOrderby())) {
                PageHelper.orderBy(tripInteractQuery.getOrderby());
            }
            // 调数据库接口查询列表
            return tripInteractDao.queryList(tripInteractQuery);
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
    public PageInfo<TripInteract> queryPage(TripInteractQuery tripInteractQuery) throws PendingException {
        try {
            // 对请求参数进行校验
            if (tripInteractQuery.getPageNo() <= 0 || tripInteractQuery.getPageSize() <= 0) {
                ResCode.tripInteractDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(tripInteractQuery.getPageNo(), tripInteractQuery.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(tripInteractQuery.getOrderby())) {
                PageHelper.orderBy(tripInteractQuery.getOrderby());
            }
            // 调数据库接口查询列表
            List<TripInteract> resultList = tripInteractDao.queryList(tripInteractQuery);
            // 返回分页结果
            return new PageInfo<>(resultList);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.tripInteractDBError, "旅游互动信息分页查询失败");
        }
    }

}
