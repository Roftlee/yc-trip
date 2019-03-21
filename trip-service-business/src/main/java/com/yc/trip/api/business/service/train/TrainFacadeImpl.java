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
import com.yc.trip.api.business.bo.train.TrainDomain;
import com.yc.trip.api.business.dao.train.TrainDao;
import com.yc.trip.api.business.dto.train.Train;
import com.yc.trip.api.business.facade.train.TrainFacade;

/**
 * 培训信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:26
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
    public Train addTrain(Train train) throws PendingException {
        try {
            // 转换成domain对象
            TrainDomain cond = BeanMapping.map(train, TrainDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.trainDBParamInvalid.throwException("培训信息新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            trainDao.addTrain(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, Train.class);
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
    public Train updateTrain(Train train) throws PendingException {
        try {
            // 转换成domain对象
            TrainDomain cond = BeanMapping.map(train, TrainDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.trainDBParamInvalid.throwException("培训信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            trainDao.updateTrain(cond);
            return BeanMapping.map(cond, Train.class);
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
    public Train getTrain(Train train) throws PendingException {
        try {
            // 转换成Domain对象
            TrainDomain cond = BeanMapping.map(train, TrainDomain.class);
            // 调数据库接口查询对象
            TrainDomain resultBean = trainDao.getTrain(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, Train.class);
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
    public Train mustGet(Train train) throws PendingException {
        // 查询单位信息
        Train result = getTrain(train);
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
    public List<Train> queryTrainList(Train train) throws PendingException {
        try {
            // 转换成Domain对象
            TrainDomain cond = BeanMapping.map(train, TrainDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(train.getOrderby())) {
                PageHelper.orderBy(train.getOrderby());
            }
            // 调数据库接口查询列表
            List<TrainDomain> resultList = trainDao.queryTrainList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, Train.class);
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
    public PageInfo<Train> queryTrainPage(Train train) throws PendingException {
        try {
            // 对请求参数进行校验
            if (train.getPageNo() <= 0 || train.getPageSize() <= 0) {
                ResCode.trainDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(train.getPageNo(), train.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(train.getOrderby())) {
                PageHelper.orderBy(train.getOrderby());
            }
            // 转换成Domain对象
            TrainDomain cond = BeanMapping.map(train, TrainDomain.class);
            // 调数据库接口查询列表
            List<TrainDomain> resultList = trainDao.queryTrainList(cond);
            // 生成分页对象
            PageInfo<TrainDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, Train.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.trainDBError, "培训信息分页查询失败");
        }
    }

}
