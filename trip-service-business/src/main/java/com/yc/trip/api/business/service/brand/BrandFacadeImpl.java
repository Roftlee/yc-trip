package com.yc.trip.api.business.service.brand;

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
import com.yc.trip.api.business.dao.brand.BrandDao;
import com.yc.trip.api.business.dto.brand.Brand;
import com.yc.trip.api.business.query.brand.BrandQuery;
import com.yc.trip.api.business.facade.brand.BrandFacade;

/**
 * 品牌相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:03
 */
@Service(version = "1.0.0")
public class BrandFacadeImpl extends AbstractDubboNativeService implements BrandFacade {

    @Autowired
    private BrandDao brandDao;

    /**
     * 新增品牌
     * 
     * @throws PendingException
     */
    @Override
    public Brand add(Brand brand) throws PendingException {
        try {
            // 新增时对各字段进行非空校验
            brand.validateInsertFields();
            // 调数据库接口进行新增操作
            brandDao.add(brand);
            // 将新增后回返回（包含自增主键值）
            return brand;
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.brandDBError, "品牌新增失败");
        }
    }

    /**
     * 修改品牌
     * 
     * @throws PendingException
     */
    @Override
    public void update(Brand brand) throws PendingException {
        try {
            // 更新或删除操作时，不能所有参数都为空
            if (brand.isAllFiledsNull()) {
                ResCode.brandDBParamInvalid.throwException("品牌更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            brandDao.update(brand);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.brandDBError, "品牌更新失败");
        }
    }

    /**
     * 查询品牌
     * 
     * @throws PendingException
     */
    @Override
    public Brand get(BrandQuery brandQuery) throws PendingException {
        try {
            // 调数据库接口查询对象
            return brandDao.get(brandQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.brandDBError, "品牌查询失败");
        }
    }
    
    /**
     * 查询品牌信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public Brand mustGet(BrandQuery brandQuery) throws PendingException {
        // 查询单位信息
        Brand result = get(brandQuery);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.brandDBGetNull.throwException("未查询到品牌");
        }
        return result;
    }

    /**
     * 查询品牌列表
     * 
     * @throws PendingException
     */
    @Override
    public List<Brand> queryList(BrandQuery brandQuery) throws PendingException {
        try {
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(brandQuery.getOrderby())) {
                PageHelper.orderBy(brandQuery.getOrderby());
            }
            // 调数据库接口查询列表
            return brandDao.queryList(brandQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.brandDBError, "品牌列表查询失败");
        }
    }

    /**
     * 查询品牌列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<Brand> queryPage(BrandQuery brandQuery) throws PendingException {
        try {
            // 对请求参数进行校验
            if (brandQuery.getPageNo() <= 0 || brandQuery.getPageSize() <= 0) {
                ResCode.brandDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(brandQuery.getPageNo(), brandQuery.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(brandQuery.getOrderby())) {
                PageHelper.orderBy(brandQuery.getOrderby());
            }
            // 调数据库接口查询列表
            List<Brand> resultList = brandDao.queryList(brandQuery);
            // 返回分页结果
            return new PageInfo<>(resultList);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.brandDBError, "品牌分页查询失败");
        }
    }

}
