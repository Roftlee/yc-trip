package com.yc.trip.api.business.bo.order;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.go.api.core.annotation.InsertRequired;
import org.go.api.core.bo.BaseDomain;

import com.yc.trip.api.business.enums.order.OrderStatus;
import com.yc.trip.api.core.enums.YesNoStatus;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 订单信息Domain类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 21:48
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class OrderInfoDomain extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1;

    
	/**
	 * 
	 */
	private Long id;

	/**
	 * 订单名称
	 */
	@InsertRequired
	private String name;

	/**
	 * 产品Id
	 */
	@InsertRequired
	private Long productId;

	/**
	 * 数量
	 */
	@InsertRequired
	private Integer amout;

	/**
	 * 客户姓名
	 */
	@InsertRequired
	private String customer;

	/**
	 * 联系方式
	 */
	@InsertRequired
	private String phone;

	/**
	 * 地址
	 */
	@InsertRequired
	private String address;

	/**
	 * 所属供应商Id
	 */
	@InsertRequired
	private Long providerId;

	/**
	 * 所属门店Id
	 */
	@InsertRequired
	private Long storeId;

	/**
	 * 销售人员Id
	 */
	@InsertRequired
	private Long salesId;

	/**
	 * 订单状态0待付款1待签约2待出行3待评价4已完成9已废除(枚举:OrderStatus)
	 */
	@InsertRequired
	private OrderStatus orderStatus;

	/**
	 * 合同
	 */
	private String contract;

	/**
	 * 确认编号
	 */
	private String vertifyCode;

	/**
	 * 通知书
	 */
	private String notification;

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
