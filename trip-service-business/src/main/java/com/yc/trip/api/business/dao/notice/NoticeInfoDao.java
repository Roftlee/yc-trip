package com.yc.trip.api.business.dao.notice;

import java.util.List;

import com.yc.trip.api.business.bo.notice.NoticeInfoDomain;

/**
 * 资讯Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 21:41
 *
 */
public interface NoticeInfoDao {

    /**
     * 新增资讯
     */
    void addNoticeInfo(NoticeInfoDomain noticeInfoDomain);

    /**
     * 修改资讯
     */
    void updateNoticeInfo(NoticeInfoDomain noticeInfoDomain);
    
    /**
     * 查询资讯
     */
    NoticeInfoDomain getNoticeInfo(NoticeInfoDomain noticeInfoDomain);

    /**
     * 查询资讯列表
     */
    List<NoticeInfoDomain> queryNoticeInfoList(NoticeInfoDomain noticeInfoDomain);

}