package com.yc.trip.api.business.bo.product;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.go.api.core.annotation.InsertRequired;
import org.go.api.core.bo.BaseDomain;

import com.yc.trip.api.core.enums.YesNoStatus;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 产品信息Domain类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 21:50
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ProductDomain extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1;

    
	/**
	 * 
	 */
	private Long id;

	/**
	 * 产品名称
	 */
	@InsertRequired
	private String name;

	/**
	 * 产品简介
	 */
	private String briefDesc;

	/**
	 * 所属分类Id
	 */
	@InsertRequired
	private Long sortId;

	/**
	 * 所属地区Id
	 */
	@InsertRequired
	private Long regionId;

	/**
	 * 所属地区分类Id
	 */
	@InsertRequired
	private Long regionSortId;

	/**
	 * 所属供应商Id
	 */
	@InsertRequired
	private Long providerId;

	/**
	 * 商品成本
	 */
	private Double cost;

	/**
	 * 成交价
	 */
	private Double strikePrice;

	/**
	 * 售价
	 */
	@InsertRequired
	private Double price;

	/**
	 * 费用说明
	 */
	private String priceDesc;

	/**
	 * 门店分成比例(百分比)
	 */
	@InsertRequired
	private Integer commissionRate;

	/**
	 * 图片链接
	 */
	private String imageUrl;

	/**
	 * 视频链接
	 */
	private String videoUrl;

	/**
	 * 行程介绍
	 */
	private String routeIntroduce;

	/**
	 * 行程特色
	 */
	private String routeFeature;

	/**
	 * 产品描述
	 */
	@InsertRequired
	private String description;

	/**
	 * 备注
	 */
	private String remark;

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
