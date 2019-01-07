package com.yc.trip.api.business.dao.notice;

import java.util.List;

import com.yc.trip.api.business.dto.notice.NoticeInfo;
import com.yc.trip.api.business.query.notice.NoticeInfoQuery;

/**
 * 资讯Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:08
 *
 */
public interface NoticeInfoDao {

    /**
     * 资讯新增
     */
    void add(NoticeInfo noticeInfo);

    /**
     * 资讯修改
     */
    void update(NoticeInfo noticeInfo);
    
    /**
     * 资讯查询
     */
    NoticeInfo get(NoticeInfoQuery noticeInfoQuery);

    /**
     * 资讯列表查询
     */
    List<NoticeInfo> queryList(NoticeInfoQuery noticeInfoQuery);

}