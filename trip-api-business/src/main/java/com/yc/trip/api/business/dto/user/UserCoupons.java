package com.yc.trip.api.business.dto.user;

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
 * 用户优惠券Dto类
 * 
 * @author My-Toolkits
 * @since 2019-01-08 23:28
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class UserCoupons extends AbstractEntityDto implements Serializable {

    private static final long serialVersionUID = -1;

    
	/**
	 * id
	 */
	@InsertRequired
	private Long id;

	/**
	 * 用户Id
	 */
	@InsertRequired
	private Long userId;

	/**
	 * 优惠券Id
	 */
	@InsertRequired
	private Long counponsId;

	/**
	 * 是否使用0否1是(枚举:YesNoStatus[core])
	 */
	@InsertRequired
	private YesNoStatus isUsed;

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
