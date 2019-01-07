package com.yc.trip.api.business.dao.visa;

import java.util.List;

import com.yc.trip.api.business.dto.visa.VisaSort;
import com.yc.trip.api.business.query.visa.VisaSortQuery;

/**
 * 签证分类信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 18:06
 *
 */
public interface VisaSortDao {

    /**
     * 签证分类信息新增
     */
    void add(VisaSort visaSort);

    /**
     * 签证分类信息修改
     */
    void update(VisaSort visaSort);
    
    /**
     * 签证分类信息查询
     */
    VisaSort get(VisaSortQuery visaSortQuery);

    /**
     * 签证分类信息列表查询
     */
    List<VisaSort> queryList(VisaSortQuery visaSortQuery);

}