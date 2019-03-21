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
import com.yc.trip.api.business.bo.region.RegionDomain;
import com.yc.trip.api.business.dao.region.RegionDao;
import com.yc.trip.api.business.dto.region.Region;
import com.yc.trip.api.business.facade.region.RegionFacade;

/**
 * 地区信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:13
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
    public Region addRegion(Region region) throws PendingException {
        try {
            // 转换成domain对象
            RegionDomain cond = BeanMapping.map(region, RegionDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.regionDBParamInvalid.throwException("地区信息新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            regionDao.addRegion(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, Region.class);
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
    public Region updateRegion(Region region) throws PendingException {
        try {
            // 转换成domain对象
            RegionDomain cond = BeanMapping.map(region, RegionDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.regionDBParamInvalid.throwException("地区信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            regionDao.updateRegion(cond);
            return BeanMapping.map(cond, Region.class);
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
    public Region getRegion(Region region) throws PendingException {
        try {
            // 转换成Domain对象
            RegionDomain cond = BeanMapping.map(region, RegionDomain.class);
            // 调数据库接口查询对象
            RegionDomain resultBean = regionDao.getRegion(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, Region.class);
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
    public Region mustGet(Region region) throws PendingException {
        // 查询单位信息
        Region result = getRegion(region);
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
    public List<Region> queryRegionList(Region region) throws PendingException {
        try {
            // 转换成Domain对象
            RegionDomain cond = BeanMapping.map(region, RegionDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(region.getOrderby())) {
                PageHelper.orderBy(region.getOrderby());
            }
            // 调数据库接口查询列表
            List<RegionDomain> resultList = regionDao.queryRegionList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, Region.class);
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
    public PageInfo<Region> queryRegionPage(Region region) throws PendingException {
        try {
            // 对请求参数进行校验
            if (region.getPageNo() <= 0 || region.getPageSize() <= 0) {
                ResCode.regionDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(region.getPageNo(), region.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(region.getOrderby())) {
                PageHelper.orderBy(region.getOrderby());
            }
            // 转换成Domain对象
            RegionDomain cond = BeanMapping.map(region, RegionDomain.class);
            // 调数据库接口查询列表
            List<RegionDomain> resultList = regionDao.queryRegionList(cond);
            // 生成分页对象
            PageInfo<RegionDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, Region.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.regionDBError, "地区信息分页查询失败");
        }
    }

}
