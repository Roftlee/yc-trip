package com.yc.trip.api.business.dao.motorcade;

import java.util.List;

import com.yc.trip.api.business.dto.motorcade.Motorcade;
import com.yc.trip.api.business.query.motorcade.MotorcadeQuery;

/**
 * 车队Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:06
 *
 */
public interface MotorcadeDao {

    /**
     * 车队新增
     */
    void add(Motorcade motorcade);

    /**
     * 车队修改
     */
    void update(Motorcade motorcade);
    
    /**
     * 车队查询
     */
    Motorcade get(MotorcadeQuery motorcadeQuery);

    /**
     * 车队列表查询
     */
    List<Motorcade> queryList(MotorcadeQuery motorcadeQuery);

}