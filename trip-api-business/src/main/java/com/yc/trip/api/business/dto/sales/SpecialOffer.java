package com.yc.trip.api.business.dto.sales;

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
 * 优惠活动信息Dto类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:46
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class SpecialOffer extends AbstractEntityDto implements Serializable {

    private static final long serialVersionUID = -1;

    
	/**
	 * 
	 */
	private Long id;

	/**
	 * 标题
	 */
	@InsertRequired
	private String title;

	/**
	 * 所属供应商Id
	 */
	@InsertRequired
	private Long providerId;

	/**
	 * 产品Id
	 */
	@InsertRequired
	private Long productId;

	/**
	 * 活动图片
	 */
	private String imageUrl;

	/**
	 * 开始时间
	 */
	@InsertRequired
	private Date startTime;

	/**
	 * 结束时间
	 */
	@InsertRequired
	private Date endTime;

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
