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
import com.yc.trip.api.business.bo.visa.VisaDomain;
import com.yc.trip.api.business.dao.visa.VisaDao;
import com.yc.trip.api.business.dto.visa.Visa;
import com.yc.trip.api.business.facade.visa.VisaFacade;

/**
 * 签证信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:35
 */
@Service(version = "1.0.0")
public class VisaFacadeImpl extends AbstractDubboNativeService implements VisaFacade {

    @Autowired
    private VisaDao visaDao;

    /**
     * 新增签证信息
     * 
     * @throws PendingException
     */
    @Override
    public Visa addVisa(Visa visa) throws PendingException {
        try {
            // 转换成domain对象
            VisaDomain cond = BeanMapping.map(visa, VisaDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.visaDBParamInvalid.throwException("签证信息新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            visaDao.addVisa(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, Visa.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.visaDBError, "签证信息新增失败");
        }
    }

    /**
     * 修改签证信息
     * 
     * @throws PendingException
     */
    @Override
    public Visa updateVisa(Visa visa) throws PendingException {
        try {
            // 转换成domain对象
            VisaDomain cond = BeanMapping.map(visa, VisaDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.visaDBParamInvalid.throwException("签证信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            visaDao.updateVisa(cond);
            return BeanMapping.map(cond, Visa.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.visaDBError, "签证信息更新失败");
        }
    }

    /**
     * 查询签证信息
     * 
     * @throws PendingException
     */
    @Override
    public Visa getVisa(Visa visa) throws PendingException {
        try {
            // 转换成Domain对象
            VisaDomain cond = BeanMapping.map(visa, VisaDomain.class);
            // 调数据库接口查询对象
            VisaDomain resultBean = visaDao.getVisa(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, Visa.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.visaDBError, "签证信息查询失败");
        }
    }
    
    /**
     * 查询签证信息信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public Visa mustGet(Visa visa) throws PendingException {
        // 查询单位信息
        Visa result = getVisa(visa);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.visaDBGetNull.throwException("未查询到签证信息");
        }
        return result;
    }

    /**
     * 查询签证信息列表
     * 
     * @throws PendingException
     */
    @Override
    public List<Visa> queryVisaList(Visa visa) throws PendingException {
        try {
            // 转换成Domain对象
            VisaDomain cond = BeanMapping.map(visa, VisaDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(visa.getOrderby())) {
                PageHelper.orderBy(visa.getOrderby());
            }
            // 调数据库接口查询列表
            List<VisaDomain> resultList = visaDao.queryVisaList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, Visa.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.visaDBError, "签证信息列表查询失败");
        }
    }

    /**
     * 查询签证信息列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<Visa> queryVisaPage(Visa visa) throws PendingException {
        try {
            // 对请求参数进行校验
            if (visa.getPageNo() <= 0 || visa.getPageSize() <= 0) {
                ResCode.visaDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(visa.getPageNo(), visa.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(visa.getOrderby())) {
                PageHelper.orderBy(visa.getOrderby());
            }
            // 转换成Domain对象
            VisaDomain cond = BeanMapping.map(visa, VisaDomain.class);
            // 调数据库接口查询列表
            List<VisaDomain> resultList = visaDao.queryVisaList(cond);
            // 生成分页对象
            PageInfo<VisaDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, Visa.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.visaDBError, "签证信息分页查询失败");
        }
    }

}
