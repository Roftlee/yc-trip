package com.yc.trip.api.business.dao.motorcade;

import java.util.List;

import com.yc.trip.api.business.bo.motorcade.MotorcadeDomain;

/**
 * 车队Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 21:39
 *
 */
public interface MotorcadeDao {

    /**
     * 新增车队
     */
    void addMotorcade(MotorcadeDomain motorcadeDomain);

    /**
     * 修改车队
     */
    void updateMotorcade(MotorcadeDomain motorcadeDomain);
    
    /**
     * 查询车队
     */
    MotorcadeDomain getMotorcade(MotorcadeDomain motorcadeDomain);

    /**
     * 查询车队列表
     */
    List<MotorcadeDomain> queryMotorcadeList(MotorcadeDomain motorcadeDomain);

}