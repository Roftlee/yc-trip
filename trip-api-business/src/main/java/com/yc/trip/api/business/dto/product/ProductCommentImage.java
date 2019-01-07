package com.yc.trip.api.business.dto.product;

import java.util.Date;
import java.util.List;

import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractEntityDto;
import org.go.api.core.annotation.InsertRequired;

import com.yc.trip.api.core.constants.ResCode;

import java.io.Serializable;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 产品评论图片信息Dto类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:20
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ProductCommentImage extends AbstractEntityDto implements Serializable {

    private static final long serialVersionUID = -1;

    
	/**
	 * 
	 */
	private Long id;

	/**
	 * 点评记录Id
	 */
	@InsertRequired
	private Long commentId;

	/**
	 * 图片链接
	 */
	@InsertRequired
	private String imageUrl;

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
