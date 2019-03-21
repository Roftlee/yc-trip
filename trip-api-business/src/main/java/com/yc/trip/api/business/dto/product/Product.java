package com.yc.trip.api.business.dto.product;

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
 * 产品信息Dto类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 21:50
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Product extends AbstractBasePageRequestDto {

    private static final long serialVersionUID = -1;

    
	/**
	 * 
	 */
	private Long id;

	/**
	 * 产品名称
	 */
	private String name;

	/**
	 * 产品简介
	 */
	private String briefDesc;

	/**
	 * 所属分类Id
	 */
	private Long sortId;

	/**
	 * 所属地区Id
	 */
	private Long regionId;

	/**
	 * 所属地区分类Id
	 */
	private Long regionSortId;

	/**
	 * 所属供应商Id
	 */
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
	private Double price;

	/**
	 * 费用说明
	 */
	private String priceDesc;

	/**
	 * 门店分成比例(百分比)
	 */
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
    
    @Override
    public ResBean validateParam() {
        return ResCode.success;
    }
    
    /**
     * 流式设置排序字段
     * @param orderby
     * @return 
     */
    public Product orderBy(String orderby){
        if(getPageNo() == null) setPageNo(1);
        if(getPageSize() == null) setPageSize(1000);
        setOrderby(orderby);
        return this;
    }
        
}
