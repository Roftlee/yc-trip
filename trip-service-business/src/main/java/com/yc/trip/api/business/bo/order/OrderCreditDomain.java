package com.yc.trip.api.business.bo.order;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.go.api.core.annotation.InsertRequired;
import org.go.api.core.bo.BaseDomain;

import com.yc.trip.api.business.enums.order.AllotStatus;
import com.yc.trip.api.core.enums.YesNoStatus;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 订单积分信息Domain类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 21:45
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class OrderCreditDomain extends BaseDomain implements Serializable {

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
	 * 可分配积分
	 */
	@InsertRequired
	private Integer credit;

	/**
	 * 销售推介人分成比例(百分比)
	 */
	@InsertRequired
	private Integer inviteCommissionRate;

	/**
	 * 推介人积分
	 */
	@InsertRequired
	private Integer inviteCredit;

	/**
	 * 销售分成比例(百分比)
	 */
	@InsertRequired
	private Integer salesCommissionRate;

	/**
	 * 销售积分
	 */
	@InsertRequired
	private Integer salesCredit;

	/**
	 * 分配状态0未分配1已分配(枚举:AllotStatus)
	 */
	@InsertRequired
	private AllotStatus allotStatus;

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
