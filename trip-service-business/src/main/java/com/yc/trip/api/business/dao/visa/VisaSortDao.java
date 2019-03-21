package com.yc.trip.api.business.dao.visa;

import java.util.List;

import com.yc.trip.api.business.bo.visa.VisaSortDomain;

/**
 * 签证分类信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:37
 *
 */
public interface VisaSortDao {

    /**
     * 新增签证分类信息
     */
    void addVisaSort(VisaSortDomain visaSortDomain);

    /**
     * 修改签证分类信息
     */
    void updateVisaSort(VisaSortDomain visaSortDomain);
    
    /**
     * 查询签证分类信息
     */
    VisaSortDomain getVisaSort(VisaSortDomain visaSortDomain);

    /**
     * 查询签证分类信息列表
     */
    List<VisaSortDomain> queryVisaSortList(VisaSortDomain visaSortDomain);

}