package com.yc.trip.api.business.facade.notice;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.notice.NoticeInfo;

/**
 * 资讯相关接口
 * @author My-Toolkits
 * @since 2019-03-21 21:41
 */
public interface NoticeInfoFacade {

    /**
     * 新增资讯
     * @throws PendingException 
     */
    NoticeInfo addNoticeInfo(NoticeInfo noticeInfo) throws PendingException;

    /**
     * 修改资讯
     * @throws PendingException 
     */
    NoticeInfo updateNoticeInfo(NoticeInfo noticeInfo) throws PendingException;
    
    /**
     * 查询资讯
     * @throws PendingException 
     */
    NoticeInfo getNoticeInfo(NoticeInfo noticeInfo) throws PendingException;
    
    /**
     * 查询资讯（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    NoticeInfo mustGet(NoticeInfo noticeInfo) throws PendingException;

    /**
     * 查询资讯列表
     * @throws PendingException 
     */
    List<NoticeInfo> queryNoticeInfoList(NoticeInfo noticeInfo) throws PendingException;

    /**
     * 查询资讯列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<NoticeInfo> queryNoticeInfoPage(NoticeInfo noticeInfo) throws PendingException;

}