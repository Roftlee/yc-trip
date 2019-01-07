package com.yc.trip.api.business.service.shop;

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
import com.yc.trip.api.business.dao.shop.ShoppingCarDao;
import com.yc.trip.api.business.dto.shop.ShoppingCar;
import com.yc.trip.api.business.query.shop.ShoppingCarQuery;
import com.yc.trip.api.business.facade.shop.ShoppingCarFacade;

/**
 * 购物车信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:44
 */
@Service(version = "1.0.0")
public class ShoppingCarFacadeImpl extends AbstractDubboNativeService implements ShoppingCarFacade {

    @Autowired
    private ShoppingCarDao shoppingCarDao;

    /**
     * 新增购物车信息
     * 
     * @throws PendingException
     */
    @Override
    public ShoppingCar add(ShoppingCar shoppingCar) throws PendingException {
        try {
            // 新增时对各字段进行非空校验
            shoppingCar.validateInsertFields();
            // 调数据库接口进行新增操作
            shoppingCarDao.add(shoppingCar);
            // 将新增后回返回（包含自增主键值）
            return shoppingCar;
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.shoppingCarDBError, "购物车信息新增失败");
        }
    }

    /**
     * 修改购物车信息
     * 
     * @throws PendingException
     */
    @Override
    public void update(ShoppingCar shoppingCar) throws PendingException {
        try {
            // 更新或删除操作时，不能所有参数都为空
            if (shoppingCar.isAllFiledsNull()) {
                ResCode.shoppingCarDBParamInvalid.throwException("购物车信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            shoppingCarDao.update(shoppingCar);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.shoppingCarDBError, "购物车信息更新失败");
        }
    }

    /**
     * 查询购物车信息
     * 
     * @throws PendingException
     */
    @Override
    public ShoppingCar get(ShoppingCarQuery shoppingCarQuery) throws PendingException {
        try {
            // 调数据库接口查询对象
            return shoppingCarDao.get(shoppingCarQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.shoppingCarDBError, "购物车信息查询失败");
        }
    }
    
    /**
     * 查询购物车信息信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public ShoppingCar mustGet(ShoppingCarQuery shoppingCarQuery) throws PendingException {
        // 查询单位信息
        ShoppingCar result = get(shoppingCarQuery);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.shoppingCarDBGetNull.throwException("未查询到购物车信息");
        }
        return result;
    }

    /**
     * 查询购物车信息列表
     * 
     * @throws PendingException
     */
    @Override
    public List<ShoppingCar> queryList(ShoppingCarQuery shoppingCarQuery) throws PendingException {
        try {
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(shoppingCarQuery.getOrderby())) {
                PageHelper.orderBy(shoppingCarQuery.getOrderby());
            }
            // 调数据库接口查询列表
            return shoppingCarDao.queryList(shoppingCarQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.shoppingCarDBError, "购物车信息列表查询失败");
        }
    }

    /**
     * 查询购物车信息列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<ShoppingCar> queryPage(ShoppingCarQuery shoppingCarQuery) throws PendingException {
        try {
            // 对请求参数进行校验
            if (shoppingCarQuery.getPageNo() <= 0 || shoppingCarQuery.getPageSize() <= 0) {
                ResCode.shoppingCarDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(shoppingCarQuery.getPageNo(), shoppingCarQuery.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(shoppingCarQuery.getOrderby())) {
                PageHelper.orderBy(shoppingCarQuery.getOrderby());
            }
            // 调数据库接口查询列表
            List<ShoppingCar> resultList = shoppingCarDao.queryList(shoppingCarQuery);
            // 返回分页结果
            return new PageInfo<>(resultList);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.shoppingCarDBError, "购物车信息分页查询失败");
        }
    }

}
