package com.yc.trip.api.business.service.notice;

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
import com.yc.trip.api.business.dao.notice.NoticeInfoDao;
import com.yc.trip.api.business.dto.notice.NoticeInfo;
import com.yc.trip.api.business.query.notice.NoticeInfoQuery;
import com.yc.trip.api.business.facade.notice.NoticeInfoFacade;

/**
 * 资讯相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:08
 */
@Service(version = "1.0.0")
public class NoticeInfoFacadeImpl extends AbstractDubboNativeService implements NoticeInfoFacade {

    @Autowired
    private NoticeInfoDao noticeInfoDao;

    /**
     * 新增资讯
     * 
     * @throws PendingException
     */
    @Override
    public NoticeInfo add(NoticeInfo noticeInfo) throws PendingException {
        try {
            // 新增时对各字段进行非空校验
            noticeInfo.validateInsertFields();
            // 调数据库接口进行新增操作
            noticeInfoDao.add(noticeInfo);
            // 将新增后回返回（包含自增主键值）
            return noticeInfo;
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.noticeInfoDBError, "资讯新增失败");
        }
    }

    /**
     * 修改资讯
     * 
     * @throws PendingException
     */
    @Override
    public void update(NoticeInfo noticeInfo) throws PendingException {
        try {
            // 更新或删除操作时，不能所有参数都为空
            if (noticeInfo.isAllFiledsNull()) {
                ResCode.noticeInfoDBParamInvalid.throwException("资讯更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            noticeInfoDao.update(noticeInfo);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.noticeInfoDBError, "资讯更新失败");
        }
    }

    /**
     * 查询资讯
     * 
     * @throws PendingException
     */
    @Override
    public NoticeInfo get(NoticeInfoQuery noticeInfoQuery) throws PendingException {
        try {
            // 调数据库接口查询对象
            return noticeInfoDao.get(noticeInfoQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.noticeInfoDBError, "资讯查询失败");
        }
    }
    
    /**
     * 查询资讯信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public NoticeInfo mustGet(NoticeInfoQuery noticeInfoQuery) throws PendingException {
        // 查询单位信息
        NoticeInfo result = get(noticeInfoQuery);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.noticeInfoDBGetNull.throwException("未查询到资讯");
        }
        return result;
    }

    /**
     * 查询资讯列表
     * 
     * @throws PendingException
     */
    @Override
    public List<NoticeInfo> queryList(NoticeInfoQuery noticeInfoQuery) throws PendingException {
        try {
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(noticeInfoQuery.getOrderby())) {
                PageHelper.orderBy(noticeInfoQuery.getOrderby());
            }
            // 调数据库接口查询列表
            return noticeInfoDao.queryList(noticeInfoQuery);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.noticeInfoDBError, "资讯列表查询失败");
        }
    }

    /**
     * 查询资讯列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<NoticeInfo> queryPage(NoticeInfoQuery noticeInfoQuery) throws PendingException {
        try {
            // 对请求参数进行校验
            if (noticeInfoQuery.getPageNo() <= 0 || noticeInfoQuery.getPageSize() <= 0) {
                ResCode.noticeInfoDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(noticeInfoQuery.getPageNo(), noticeInfoQuery.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(noticeInfoQuery.getOrderby())) {
                PageHelper.orderBy(noticeInfoQuery.getOrderby());
            }
            // 调数据库接口查询列表
            List<NoticeInfo> resultList = noticeInfoDao.queryList(noticeInfoQuery);
            // 返回分页结果
            return new PageInfo<>(resultList);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.noticeInfoDBError, "资讯分页查询失败");
        }
    }

}
