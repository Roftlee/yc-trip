package com.yc.trip.api.business.service.train;

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
import com.yc.trip.api.business.dao.train.TrainDao;
import com.yc.trip.api.business.dto.train.Train;
import com.yc.trip.api.business.query.train.TrainQuery;
import com.yc.trip.api.business.facade.train.TrainFacade;

/**
 * 培训信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:55
 */
@Service(version = "1.0.0")
public class TrainFacadeImpl extends AbstractDubboNativeService implements TrainFacade {

    @Autowired
    private TrainDao trainDao;

    /**
     * 新增培训信息
     * 
     * @throws PendingException
     */
    @Override
    public Train add(Train train) throws PendingException {
        try {
            // 新增时对各字段进行非空校验
            train.validateInsertFields();
            // 调数据库接口进行新增操作
            trainDao.add(train);
            // 将新增后回返回（包含自增主键值）
            return train;
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.trainDBError, "培训信息新增失败");
        }
    }

    /**
     * 修改培训信息
     * 
     * @throws PendingException
     */
    @Override
    public void update(Train train) throws PendingException {
        try {
            // 更新或删除操作时，不能所有参数都为空
            if (train.isAllFiledsNull()) {
                ResCode.trainDBParamInvalid.throwException("培训信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            trainDao.update(train);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.trainDBError, "培训信息更新失败");
        }
    }

    /**
     * 查询培训信息
     * 
     * @throws PendingException
     */
    @Override
    public Train get(TrainQuery trainQuery) throws PendingException {
        try {
            // 调数据库接口查询对象
            return trainDao.get(trainQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.trainDBError, "培训信息查询失败");
        }
    }
    
    /**
     * 查询培训信息信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public Train mustGet(TrainQuery trainQuery) throws PendingException {
        // 查询单位信息
        Train result = get(trainQuery);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.trainDBGetNull.throwException("未查询到培训信息");
        }
        return result;
    }

    /**
     * 查询培训信息列表
     * 
     * @throws PendingException
     */
    @Override
    public List<Train> queryList(TrainQuery trainQuery) throws PendingException {
        try {
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(trainQuery.getOrderby())) {
                PageHelper.orderBy(trainQuery.getOrderby());
            }
            // 调数据库接口查询列表
            return trainDao.queryList(trainQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.trainDBError, "培训信息列表查询失败");
        }
    }

    /**
     * 查询培训信息列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<Train> queryPage(TrainQuery trainQuery) throws PendingException {
        try {
            // 对请求参数进行校验
            if (trainQuery.getPageNo() <= 0 || trainQuery.getPageSize() <= 0) {
                ResCode.trainDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(trainQuery.getPageNo(), trainQuery.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(trainQuery.getOrderby())) {
                PageHelper.orderBy(trainQuery.getOrderby());
            }
            // 调数据库接口查询列表
            List<Train> resultList = trainDao.queryList(trainQuery);
            // 返回分页结果
            return new PageInfo<>(resultList);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.trainDBError, "培训信息分页查询失败");
        }
    }

}
