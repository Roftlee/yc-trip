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
import com.yc.trip.api.business.dao.region.RegionSortDao;
import com.yc.trip.api.business.dto.region.RegionSort;
import com.yc.trip.api.business.query.region.RegionSortQuery;
import com.yc.trip.api.business.facade.region.RegionSortFacade;

/**
 * 地区分类信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:30
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
    public RegionSort add(RegionSort regionSort) throws PendingException {
        try {
            // 新增时对各字段进行非空校验
            regionSort.validateInsertFields();
            // 调数据库接口进行新增操作
            regionSortDao.add(regionSort);
            // 将新增后回返回（包含自增主键值）
            return regionSort;
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
    public void update(RegionSort regionSort) throws PendingException {
        try {
            // 更新或删除操作时，不能所有参数都为空
            if (regionSort.isAllFiledsNull()) {
                ResCode.regionSortDBParamInvalid.throwException("地区分类信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            regionSortDao.update(regionSort);
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
    public RegionSort get(RegionSortQuery regionSortQuery) throws PendingException {
        try {
            // 调数据库接口查询对象
            return regionSortDao.get(regionSortQuery);
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
    public RegionSort mustGet(RegionSortQuery regionSortQuery) throws PendingException {
        // 查询单位信息
        RegionSort result = get(regionSortQuery);
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
    public List<RegionSort> queryList(RegionSortQuery regionSortQuery) throws PendingException {
        try {
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(regionSortQuery.getOrderby())) {
                PageHelper.orderBy(regionSortQuery.getOrderby());
            }
            // 调数据库接口查询列表
            return regionSortDao.queryList(regionSortQuery);
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
    public PageInfo<RegionSort> queryPage(RegionSortQuery regionSortQuery) throws PendingException {
        try {
            // 对请求参数进行校验
            if (regionSortQuery.getPageNo() <= 0 || regionSortQuery.getPageSize() <= 0) {
                ResCode.regionSortDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(regionSortQuery.getPageNo(), regionSortQuery.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(regionSortQuery.getOrderby())) {
                PageHelper.orderBy(regionSortQuery.getOrderby());
            }
            // 调数据库接口查询列表
            List<RegionSort> resultList = regionSortDao.queryList(regionSortQuery);
            // 返回分页结果
            return new PageInfo<>(resultList);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.regionSortDBError, "地区分类信息分页查询失败");
        }
    }

}
