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
import com.yc.trip.api.business.bo.notice.NoticeInfoDomain;
import com.yc.trip.api.business.dao.notice.NoticeInfoDao;
import com.yc.trip.api.business.dto.notice.NoticeInfo;
import com.yc.trip.api.business.facade.notice.NoticeInfoFacade;

/**
 * 资讯相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 21:41
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
    public NoticeInfo addNoticeInfo(NoticeInfo noticeInfo) throws PendingException {
        try {
            // 转换成domain对象
            NoticeInfoDomain cond = BeanMapping.map(noticeInfo, NoticeInfoDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.noticeInfoDBParamInvalid.throwException("资讯新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            noticeInfoDao.addNoticeInfo(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, NoticeInfo.class);
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
    public NoticeInfo updateNoticeInfo(NoticeInfo noticeInfo) throws PendingException {
        try {
            // 转换成domain对象
            NoticeInfoDomain cond = BeanMapping.map(noticeInfo, NoticeInfoDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.noticeInfoDBParamInvalid.throwException("资讯更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            noticeInfoDao.updateNoticeInfo(cond);
            return BeanMapping.map(cond, NoticeInfo.class);
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
    public NoticeInfo getNoticeInfo(NoticeInfo noticeInfo) throws PendingException {
        try {
            // 转换成Domain对象
            NoticeInfoDomain cond = BeanMapping.map(noticeInfo, NoticeInfoDomain.class);
            // 调数据库接口查询对象
            NoticeInfoDomain resultBean = noticeInfoDao.getNoticeInfo(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, NoticeInfo.class);
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
    public NoticeInfo mustGet(NoticeInfo noticeInfo) throws PendingException {
        // 查询单位信息
        NoticeInfo result = getNoticeInfo(noticeInfo);
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
    public List<NoticeInfo> queryNoticeInfoList(NoticeInfo noticeInfo) throws PendingException {
        try {
            // 转换成Domain对象
            NoticeInfoDomain cond = BeanMapping.map(noticeInfo, NoticeInfoDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(noticeInfo.getOrderby())) {
                PageHelper.orderBy(noticeInfo.getOrderby());
            }
            // 调数据库接口查询列表
            List<NoticeInfoDomain> resultList = noticeInfoDao.queryNoticeInfoList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, NoticeInfo.class);
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
    public PageInfo<NoticeInfo> queryNoticeInfoPage(NoticeInfo noticeInfo) throws PendingException {
        try {
            // 对请求参数进行校验
            if (noticeInfo.getPageNo() <= 0 || noticeInfo.getPageSize() <= 0) {
                ResCode.noticeInfoDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(noticeInfo.getPageNo(), noticeInfo.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(noticeInfo.getOrderby())) {
                PageHelper.orderBy(noticeInfo.getOrderby());
            }
            // 转换成Domain对象
            NoticeInfoDomain cond = BeanMapping.map(noticeInfo, NoticeInfoDomain.class);
            // 调数据库接口查询列表
            List<NoticeInfoDomain> resultList = noticeInfoDao.queryNoticeInfoList(cond);
            // 生成分页对象
            PageInfo<NoticeInfoDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, NoticeInfo.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.noticeInfoDBError, "资讯分页查询失败");
        }
    }

}
