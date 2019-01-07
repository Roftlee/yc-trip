package com.yc.trip.api.business.service.guide;

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
import com.yc.trip.api.business.dao.guide.TripGuideDao;
import com.yc.trip.api.business.dto.guide.TripGuide;
import com.yc.trip.api.business.query.guide.TripGuideQuery;
import com.yc.trip.api.business.facade.guide.TripGuideFacade;

/**
 * 旅游攻略信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:57
 */
@Service(version = "1.0.0")
public class TripGuideFacadeImpl extends AbstractDubboNativeService implements TripGuideFacade {

    @Autowired
    private TripGuideDao tripGuideDao;

    /**
     * 新增旅游攻略信息
     * 
     * @throws PendingException
     */
    @Override
    public TripGuide add(TripGuide tripGuide) throws PendingException {
        try {
            // 新增时对各字段进行非空校验
            tripGuide.validateInsertFields();
            // 调数据库接口进行新增操作
            tripGuideDao.add(tripGuide);
            // 将新增后回返回（包含自增主键值）
            return tripGuide;
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.tripGuideDBError, "旅游攻略信息新增失败");
        }
    }

    /**
     * 修改旅游攻略信息
     * 
     * @throws PendingException
     */
    @Override
    public void update(TripGuide tripGuide) throws PendingException {
        try {
            // 更新或删除操作时，不能所有参数都为空
            if (tripGuide.isAllFiledsNull()) {
                ResCode.tripGuideDBParamInvalid.throwException("旅游攻略信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            tripGuideDao.update(tripGuide);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.tripGuideDBError, "旅游攻略信息更新失败");
        }
    }

    /**
     * 查询旅游攻略信息
     * 
     * @throws PendingException
     */
    @Override
    public TripGuide get(TripGuideQuery tripGuideQuery) throws PendingException {
        try {
            // 调数据库接口查询对象
            return tripGuideDao.get(tripGuideQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.tripGuideDBError, "旅游攻略信息查询失败");
        }
    }
    
    /**
     * 查询旅游攻略信息信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public TripGuide mustGet(TripGuideQuery tripGuideQuery) throws PendingException {
        // 查询单位信息
        TripGuide result = get(tripGuideQuery);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.tripGuideDBGetNull.throwException("未查询到旅游攻略信息");
        }
        return result;
    }

    /**
     * 查询旅游攻略信息列表
     * 
     * @throws PendingException
     */
    @Override
    public List<TripGuide> queryList(TripGuideQuery tripGuideQuery) throws PendingException {
        try {
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(tripGuideQuery.getOrderby())) {
                PageHelper.orderBy(tripGuideQuery.getOrderby());
            }
            // 调数据库接口查询列表
            return tripGuideDao.queryList(tripGuideQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.tripGuideDBError, "旅游攻略信息列表查询失败");
        }
    }

    /**
     * 查询旅游攻略信息列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<TripGuide> queryPage(TripGuideQuery tripGuideQuery) throws PendingException {
        try {
            // 对请求参数进行校验
            if (tripGuideQuery.getPageNo() <= 0 || tripGuideQuery.getPageSize() <= 0) {
                ResCode.tripGuideDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(tripGuideQuery.getPageNo(), tripGuideQuery.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(tripGuideQuery.getOrderby())) {
                PageHelper.orderBy(tripGuideQuery.getOrderby());
            }
            // 调数据库接口查询列表
            List<TripGuide> resultList = tripGuideDao.queryList(tripGuideQuery);
            // 返回分页结果
            return new PageInfo<>(resultList);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.tripGuideDBError, "旅游攻略信息分页查询失败");
        }
    }

}
