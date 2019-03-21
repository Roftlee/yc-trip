package com.yc.trip.api.business.service.visa;

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
import com.yc.trip.api.business.bo.visa.VisaSortDomain;
import com.yc.trip.api.business.dao.visa.VisaSortDao;
import com.yc.trip.api.business.dto.visa.VisaSort;
import com.yc.trip.api.business.facade.visa.VisaSortFacade;

/**
 * 签证分类信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:37
 */
@Service(version = "1.0.0")
public class VisaSortFacadeImpl extends AbstractDubboNativeService implements VisaSortFacade {

    @Autowired
    private VisaSortDao visaSortDao;

    /**
     * 新增签证分类信息
     * 
     * @throws PendingException
     */
    @Override
    public VisaSort addVisaSort(VisaSort visaSort) throws PendingException {
        try {
            // 转换成domain对象
            VisaSortDomain cond = BeanMapping.map(visaSort, VisaSortDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.visaSortDBParamInvalid.throwException("签证分类信息新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            visaSortDao.addVisaSort(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, VisaSort.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.visaSortDBError, "签证分类信息新增失败");
        }
    }

    /**
     * 修改签证分类信息
     * 
     * @throws PendingException
     */
    @Override
    public VisaSort updateVisaSort(VisaSort visaSort) throws PendingException {
        try {
            // 转换成domain对象
            VisaSortDomain cond = BeanMapping.map(visaSort, VisaSortDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.visaSortDBParamInvalid.throwException("签证分类信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            visaSortDao.updateVisaSort(cond);
            return BeanMapping.map(cond, VisaSort.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.visaSortDBError, "签证分类信息更新失败");
        }
    }

    /**
     * 查询签证分类信息
     * 
     * @throws PendingException
     */
    @Override
    public VisaSort getVisaSort(VisaSort visaSort) throws PendingException {
        try {
            // 转换成Domain对象
            VisaSortDomain cond = BeanMapping.map(visaSort, VisaSortDomain.class);
            // 调数据库接口查询对象
            VisaSortDomain resultBean = visaSortDao.getVisaSort(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, VisaSort.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.visaSortDBError, "签证分类信息查询失败");
        }
    }
    
    /**
     * 查询签证分类信息信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public VisaSort mustGet(VisaSort visaSort) throws PendingException {
        // 查询单位信息
        VisaSort result = getVisaSort(visaSort);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.visaSortDBGetNull.throwException("未查询到签证分类信息");
        }
        return result;
    }

    /**
     * 查询签证分类信息列表
     * 
     * @throws PendingException
     */
    @Override
    public List<VisaSort> queryVisaSortList(VisaSort visaSort) throws PendingException {
        try {
            // 转换成Domain对象
            VisaSortDomain cond = BeanMapping.map(visaSort, VisaSortDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(visaSort.getOrderby())) {
                PageHelper.orderBy(visaSort.getOrderby());
            }
            // 调数据库接口查询列表
            List<VisaSortDomain> resultList = visaSortDao.queryVisaSortList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, VisaSort.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.visaSortDBError, "签证分类信息列表查询失败");
        }
    }

    /**
     * 查询签证分类信息列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<VisaSort> queryVisaSortPage(VisaSort visaSort) throws PendingException {
        try {
            // 对请求参数进行校验
            if (visaSort.getPageNo() <= 0 || visaSort.getPageSize() <= 0) {
                ResCode.visaSortDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(visaSort.getPageNo(), visaSort.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(visaSort.getOrderby())) {
                PageHelper.orderBy(visaSort.getOrderby());
            }
            // 转换成Domain对象
            VisaSortDomain cond = BeanMapping.map(visaSort, VisaSortDomain.class);
            // 调数据库接口查询列表
            List<VisaSortDomain> resultList = visaSortDao.queryVisaSortList(cond);
            // 生成分页对象
            PageInfo<VisaSortDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, VisaSort.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.visaSortDBError, "签证分类信息分页查询失败");
        }
    }

}
