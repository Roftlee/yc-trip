package com.yc.trip.api.business.query.store;

import java.util.Date;
import java.util.List;

import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBasePageRequestDto;

import com.yc.trip.api.core.constants.ResCode;

import com.yc.trip.api.core.enums.YesNoStatus;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 门店客户信息Query类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:53
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class StoreCustomerQuery extends AbstractBasePageRequestDto {

    private static final long serialVersionUID = -1;

    
	/**
	 * 
	 */
	private Long id;

	/**
	 * 门店Id
	 */
	private Long storeId;

	/**
	 * 是否VIP0否1是(枚举:YesNoStatus[core])
	 * 默认值：0
	 */
	private YesNoStatus isVip;

	/**
	 * 用户Id
	 */
	private Long userId;

	/**
	 * 创建时间
	 * 默认值：CURRENT_TIMESTAMP
	 */
	private Date createdTime;

	/**
	 * 更新时间
	 * 默认值：CURRENT_TIMESTAMP
	 */
	private Date updatedTime;

    
    //-------------------- 扩展属性--------------------------

    /**
     * 当前页面
     */
    private Integer pageNo;

    /**
     * 每页记录数
     */
    private Integer pageSize;

    /**
     * 排序
     */
    private String orderby;

    /**
     * id列表
     */
    private List<Long> ids;

    /**
     * 关键字
     */
    private String keywords;
    
    @Override
    public ResBean validateParam() {
        return ResCode.success;
    }

        
}
