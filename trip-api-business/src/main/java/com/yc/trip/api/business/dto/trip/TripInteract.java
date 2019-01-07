package com.yc.trip.api.business.dto.trip;

import java.util.Date;
import java.util.List;

import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractEntityDto;
import org.go.api.core.annotation.InsertRequired;

import com.yc.trip.api.core.constants.ResCode;

import java.io.Serializable;

import com.yc.trip.api.business.enums.trip.InteractType;
import com.yc.trip.api.business.enums.trip.InteractOperType;
import com.yc.trip.api.core.enums.YesNoStatus;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 旅游互动信息Dto类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:58
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class TripInteract extends AbstractEntityDto implements Serializable {

    private static final long serialVersionUID = -1;

    
	/**
	 * 
	 */
	private Long id;

	/**
	 * 业务Id
	 */
	@InsertRequired
	private Long businessId;

	/**
	 * 用户Id
	 */
	@InsertRequired
	private Long userId;

	/**
	 * 互动类型1旅游攻略2趣分享(枚举:InteractType)
	 */
	@InsertRequired
	private InteractType interactType;

	/**
	 * 操作类型1点赞2收藏3评论(枚举:InteractOperType)
	 */
	@InsertRequired
	private InteractOperType interactOperType;

	/**
	 * 评论内容
	 */
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
