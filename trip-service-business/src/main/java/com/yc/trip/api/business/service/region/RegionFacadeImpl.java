package com.yc.trip.api.business.service.region;

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
import com.yc.trip.api.business.dao.region.RegionDao;
import com.yc.trip.api.business.dto.region.Region;
import com.yc.trip.api.business.query.region.RegionQuery;
import com.yc.trip.api.business.facade.region.RegionFacade;

/**
 * 地区信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:29
 */
@Service(version = "1.0.0")
public class RegionFacadeImpl extends AbstractDubboNativeService implements RegionFacade {

    @Autowired
    private RegionDao regionDao;

    /**
     * 新增地区信息
     * 
     * @throws PendingException
     */
    @Override
    public Region add(Region region) throws PendingException {
        try {
            // 新增时对各字段进行非空校验
            region.validateInsertFields();
            // 调数据库接口进行新增操作
            regionDao.add(region);
            // 将新增后回返回（包含自增主键值）
            return region;
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.regionDBError, "地区信息新增失败");
        }
    }

    /**
     * 修改地区信息
     * 
     * @throws PendingException
     */
    @Override
    public void update(Region region) throws PendingException {
        try {
            // 更新或删除操作时，不能所有参数都为空
            if (region.isAllFiledsNull()) {
                ResCode.regionDBParamInvalid.throwException("地区信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            regionDao.update(region);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.regionDBError, "地区信息更新失败");
        }
    }

    /**
     * 查询地区信息
     * 
     * @throws PendingException
     */
    @Override
    public Region get(RegionQuery regionQuery) throws PendingException {
        try {
            // 调数据库接口查询对象
            return regionDao.get(regionQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.regionDBError, "地区信息查询失败");
        }
    }
    
    /**
     * 查询地区信息信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public Region mustGet(RegionQuery regionQuery) throws PendingException {
        // 查询单位信息
        Region result = get(regionQuery);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.regionDBGetNull.throwException("未查询到地区信息");
        }
        return result;
    }

    /**
     * 查询地区信息列表
     * 
     * @throws PendingException
     */
    @Override
    public List<Region> queryList(RegionQuery regionQuery) throws PendingException {
        try {
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(regionQuery.getOrderby())) {
                PageHelper.orderBy(regionQuery.getOrderby());
            }
            // 调数据库接口查询列表
            return regionDao.queryList(regionQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.regionDBError, "地区信息列表查询失败");
        }
    }

    /**
     * 查询地区信息列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<Region> queryPage(RegionQuery regionQuery) throws PendingException {
        try {
            // 对请求参数进行校验
            if (regionQuery.getPageNo() <= 0 || regionQuery.getPageSize() <= 0) {
                ResCode.regionDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(regionQuery.getPageNo(), regionQuery.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(regionQuery.getOrderby())) {
                PageHelper.orderBy(regionQuery.getOrderby());
            }
            // 调数据库接口查询列表
            List<Region> resultList = regionDao.queryList(regionQuery);
            // 返回分页结果
            return new PageInfo<>(resultList);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.regionDBError, "地区信息分页查询失败");
        }
    }

}
