package com.yc.trip.api.business.facade.notice;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.notice.NoticeInfo;
import com.yc.trip.api.business.query.notice.NoticeInfoQuery;

/**
 * 资讯相关接口
 * @author My-Toolkits
 * @since 2019-01-06 17:08
 */
public interface NoticeInfoFacade {

    /**
     * 新增资讯
     * @throws PendingException 
     */
    NoticeInfo add(NoticeInfo noticeInfo) throws PendingException;

    /**
     * 修改资讯
     * @throws PendingException 
     */
    void update(NoticeInfo noticeInfo) throws PendingException;
    
    /**
     * 查询资讯
     * @throws PendingException 
     */
    NoticeInfo get(NoticeInfoQuery noticeInfoQuery) throws PendingException;
    
    /**
     * 查询资讯（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    NoticeInfo mustGet(NoticeInfoQuery noticeInfoQuery) throws PendingException;

    /**
     * 查询资讯列表
     * @throws PendingException 
     */
    List<NoticeInfo> queryList(NoticeInfoQuery noticeInfoQuery) throws PendingException;

    /**
     * 查询资讯列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<NoticeInfo> queryPage(NoticeInfoQuery noticeInfoQuery) throws PendingException;

}