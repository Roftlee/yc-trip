package com.yc.trip.api.business.dto.motorcade;

import java.util.Date;
import java.util.List;

import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractEntityDto;
import org.go.api.core.annotation.InsertRequired;

import com.yc.trip.api.core.constants.ResCode;

import java.io.Serializable;

import com.yc.trip.api.business.enums.motorcade.MotorcadeType;
import com.yc.trip.api.core.enums.YesNoStatus;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 车队Dto类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:06
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Motorcade extends AbstractEntityDto implements Serializable {

    private static final long serialVersionUID = -1;

    
	/**
	 * 
	 */
	private Long id;

	/**
	 * 供应商Id
	 */
	private Long providerId;

	/**
	 * 门店Id
	 */
	private Long storeId;

	/**
	 * 车型(枚举:MotorcadeType)
	 */
	@InsertRequired
	private MotorcadeType motorcadeType;

	/**
	 * 联系人
	 */
	@InsertRequired
	private String linkMan;

	/**
	 * 联系电话
	 */
	@InsertRequired
	private String linkNum;

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
