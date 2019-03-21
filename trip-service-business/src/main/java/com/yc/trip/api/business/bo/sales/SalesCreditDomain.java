package com.yc.trip.api.business.bo.sales;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.go.api.core.annotation.InsertRequired;
import org.go.api.core.bo.BaseDomain;

import com.yc.trip.api.business.enums.sales.CreditAllotStatus;
import com.yc.trip.api.core.enums.YesNoStatus;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 销售人员积分信息Domain类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class SalesCreditDomain extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1;

    
	/**
	 * 
	 */
	private Long id;

	/**
	 * 销售人员用户Id
	 */
	@InsertRequired
	private Long userId;

	/**
	 * 分配人Id
	 */
	@InsertRequired
	private Long allotId;

	/**
	 * 订单Id
	 */
	@InsertRequired
	private Long orderId;

	/**
	 * 获得积分
	 */
	@InsertRequired
	private Integer credit;

	/**
	 * 积分分配状态0预分配1分配确认(枚举:CreditAllotStatus)
	 */
	@InsertRequired
	private CreditAllotStatus creditAllotStatus;

	/**
	 * 预分配时间
	 */
	private Date preAllotTime;

	/**
	 * 确认分配时间
	 */
	private Date ackAllotTime;

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
