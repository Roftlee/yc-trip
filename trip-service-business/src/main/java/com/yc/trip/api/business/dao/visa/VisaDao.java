package com.yc.trip.api.business.dao.visa;

import java.util.List;

import com.yc.trip.api.business.dto.visa.Visa;
import com.yc.trip.api.business.query.visa.VisaQuery;

/**
 * 签证信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 18:03
 *
 */
public interface VisaDao {

    /**
     * 签证信息新增
     */
    void add(Visa visa);

    /**
     * 签证信息修改
     */
    void update(Visa visa);
    
    /**
     * 签证信息查询
     */
    Visa get(VisaQuery visaQuery);

    /**
     * 签证信息列表查询
     */
    List<Visa> queryList(VisaQuery visaQuery);

}