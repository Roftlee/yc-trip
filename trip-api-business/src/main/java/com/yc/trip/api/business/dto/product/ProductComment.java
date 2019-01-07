package com.yc.trip.api.business.dto.product;

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
 * 产品评论信息Dto类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:17
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ProductComment extends AbstractEntityDto implements Serializable {

    private static final long serialVersionUID = -1;

    
	/**
	 * 
	 */
	private Long id;

	/**
	 * 订单Id
	 */
	@InsertRequired
	private Long orderId;

	/**
	 * 产品Id
	 */
	@InsertRequired
	private Long productId;

	/**
	 * 用户Id
	 */
	@InsertRequired
	private Long userId;

	/**
	 * 评分
	 */
	@InsertRequired
	private Double credit;

	/**
	 * 评价内容
	 */
	@InsertRequired
	private String comment;

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
