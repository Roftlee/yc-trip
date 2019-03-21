package com.yc.trip.api.business.dao.visa;

import java.util.List;

import com.yc.trip.api.business.bo.visa.VisaDomain;

/**
 * 签证信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:35
 *
 */
public interface VisaDao {

    /**
     * 新增签证信息
     */
    void addVisa(VisaDomain visaDomain);

    /**
     * 修改签证信息
     */
    void updateVisa(VisaDomain visaDomain);
    
    /**
     * 查询签证信息
     */
    VisaDomain getVisa(VisaDomain visaDomain);

    /**
     * 查询签证信息列表
     */
    List<VisaDomain> queryVisaList(VisaDomain visaDomain);

}