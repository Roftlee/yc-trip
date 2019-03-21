package com.yc.trip.api.business.bo.trip;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.go.api.core.annotation.InsertRequired;
import org.go.api.core.bo.BaseDomain;

import com.yc.trip.api.business.enums.trip.InteractType;
import com.yc.trip.api.business.enums.trip.InteractOperType;
import com.yc.trip.api.core.enums.YesNoStatus;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 旅游互动信息Domain类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class TripInteractDomain extends BaseDomain implements Serializable {

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

    
    //-------------------- 扩展属性--------------------------
     /**
     * id列表
     */
    private List<Long> ids;

    /**
     * 关键字
     */
    private String keywords;

}
