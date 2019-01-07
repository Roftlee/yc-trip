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
import com.yc.trip.api.business.dao.visa.VisaSortDao;
import com.yc.trip.api.business.dto.visa.VisaSort;
import com.yc.trip.api.business.query.visa.VisaSortQuery;
import com.yc.trip.api.business.facade.visa.VisaSortFacade;

/**
 * 签证分类信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-01-06 18:06
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
    public VisaSort add(VisaSort visaSort) throws PendingException {
        try {
            // 新增时对各字段进行非空校验
            visaSort.validateInsertFields();
            // 调数据库接口进行新增操作
            visaSortDao.add(visaSort);
            // 将新增后回返回（包含自增主键值）
            return visaSort;
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
    public void update(VisaSort visaSort) throws PendingException {
        try {
            // 更新或删除操作时，不能所有参数都为空
            if (visaSort.isAllFiledsNull()) {
                ResCode.visaSortDBParamInvalid.throwException("签证分类信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            visaSortDao.update(visaSort);
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
    public VisaSort get(VisaSortQuery visaSortQuery) throws PendingException {
        try {
            // 调数据库接口查询对象
            return visaSortDao.get(visaSortQuery);
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
    public VisaSort mustGet(VisaSortQuery visaSortQuery) throws PendingException {
        // 查询单位信息
        VisaSort result = get(visaSortQuery);
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
    public List<VisaSort> queryList(VisaSortQuery visaSortQuery) throws PendingException {
        try {
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(visaSortQuery.getOrderby())) {
                PageHelper.orderBy(visaSortQuery.getOrderby());
            }
            // 调数据库接口查询列表
            return visaSortDao.queryList(visaSortQuery);
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
    public PageInfo<VisaSort> queryPage(VisaSortQuery visaSortQuery) throws PendingException {
        try {
            // 对请求参数进行校验
            if (visaSortQuery.getPageNo() <= 0 || visaSortQuery.getPageSize() <= 0) {
                ResCode.visaSortDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(visaSortQuery.getPageNo(), visaSortQuery.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(visaSortQuery.getOrderby())) {
                PageHelper.orderBy(visaSortQuery.getOrderby());
            }
            // 调数据库接口查询列表
            List<VisaSort> resultList = visaSortDao.queryList(visaSortQuery);
            // 返回分页结果
            return new PageInfo<>(resultList);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.visaSortDBError, "签证分类信息分页查询失败");
        }
    }

}
