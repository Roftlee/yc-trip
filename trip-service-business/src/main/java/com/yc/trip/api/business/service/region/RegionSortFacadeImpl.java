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
import com.yc.trip.api.business.bo.region.RegionSortDomain;
import com.yc.trip.api.business.dao.region.RegionSortDao;
import com.yc.trip.api.business.dto.region.RegionSort;
import com.yc.trip.api.business.facade.region.RegionSortFacade;

/**
 * 地区分类信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:14
 */
@Service(version = "1.0.0")
public class RegionSortFacadeImpl extends AbstractDubboNativeService implements RegionSortFacade {

    @Autowired
    private RegionSortDao regionSortDao;

    /**
     * 新增地区分类信息
     * 
     * @throws PendingException
     */
    @Override
    public RegionSort addRegionSort(RegionSort regionSort) throws PendingException {
        try {
            // 转换成domain对象
            RegionSortDomain cond = BeanMapping.map(regionSort, RegionSortDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.regionSortDBParamInvalid.throwException("地区分类信息新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            regionSortDao.addRegionSort(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, RegionSort.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.regionSortDBError, "地区分类信息新增失败");
        }
    }

    /**
     * 修改地区分类信息
     * 
     * @throws PendingException
     */
    @Override
    public RegionSort updateRegionSort(RegionSort regionSort) throws PendingException {
        try {
            // 转换成domain对象
            RegionSortDomain cond = BeanMapping.map(regionSort, RegionSortDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.regionSortDBParamInvalid.throwException("地区分类信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            regionSortDao.updateRegionSort(cond);
            return BeanMapping.map(cond, RegionSort.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.regionSortDBError, "地区分类信息更新失败");
        }
    }

    /**
     * 查询地区分类信息
     * 
     * @throws PendingException
     */
    @Override
    public RegionSort getRegionSort(RegionSort regionSort) throws PendingException {
        try {
            // 转换成Domain对象
            RegionSortDomain cond = BeanMapping.map(regionSort, RegionSortDomain.class);
            // 调数据库接口查询对象
            RegionSortDomain resultBean = regionSortDao.getRegionSort(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, RegionSort.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.regionSortDBError, "地区分类信息查询失败");
        }
    }
    
    /**
     * 查询地区分类信息信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public RegionSort mustGet(RegionSort regionSort) throws PendingException {
        // 查询单位信息
        RegionSort result = getRegionSort(regionSort);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.regionSortDBGetNull.throwException("未查询到地区分类信息");
        }
        return result;
    }

    /**
     * 查询地区分类信息列表
     * 
     * @throws PendingException
     */
    @Override
    public List<RegionSort> queryRegionSortList(RegionSort regionSort) throws PendingException {
        try {
            // 转换成Domain对象
            RegionSortDomain cond = BeanMapping.map(regionSort, RegionSortDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(regionSort.getOrderby())) {
                PageHelper.orderBy(regionSort.getOrderby());
            }
            // 调数据库接口查询列表
            List<RegionSortDomain> resultList = regionSortDao.queryRegionSortList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, RegionSort.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.regionSortDBError, "地区分类信息列表查询失败");
        }
    }

    /**
     * 查询地区分类信息列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<RegionSort> queryRegionSortPage(RegionSort regionSort) throws PendingException {
        try {
            // 对请求参数进行校验
            if (regionSort.getPageNo() <= 0 || regionSort.getPageSize() <= 0) {
                ResCode.regionSortDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(regionSort.getPageNo(), regionSort.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(regionSort.getOrderby())) {
                PageHelper.orderBy(regionSort.getOrderby());
            }
            // 转换成Domain对象
            RegionSortDomain cond = BeanMapping.map(regionSort, RegionSortDomain.class);
            // 调数据库接口查询列表
            List<RegionSortDomain> resultList = regionSortDao.queryRegionSortList(cond);
            // 生成分页对象
            PageInfo<RegionSortDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, RegionSort.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.regionSortDBError, "地区分类信息分页查询失败");
        }
    }

}
