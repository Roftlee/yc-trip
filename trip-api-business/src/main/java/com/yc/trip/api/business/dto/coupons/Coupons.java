package com.yc.trip.api.business.dto.coupons;

import java.util.Date;
import java.util.List;

import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractEntityDto;
import org.go.api.core.annotation.InsertRequired;

import com.yc.trip.api.core.constants.ResCode;

import java.io.Serializable;

import com.yc.trip.api.core.enums.YesNoStatus;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 优惠券Dto类
 * 
 * @author My-Toolkits
 * @since 2019-01-08 23:25
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Coupons extends AbstractEntityDto implements Serializable {

    private static final long serialVersionUID = -1;

    
	/**
	 * id
	 */
	@InsertRequired
	private Long id;

	/**
	 * 优惠券名称
	 */
	@InsertRequired
	private String name;

	/**
	 * 优惠券描述
	 */
	@InsertRequired
	private String description;

	/**
	 * 适用消费额
	 */
	@InsertRequired
	private Double limitCost;

	/**
	 * 优惠券金额
	 */
	@InsertRequired
	private Double amount;

	/**
	 * 是否删除0否1是(枚举:YesNoStatus[core])
	 * 默认值：0
	 */
	private YesNoStatus isDelete;

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


}
