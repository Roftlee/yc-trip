package com.yc.trip.api.business.query.order;

import java.util.Date;
import java.util.List;

import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBasePageRequestDto;

import com.yc.trip.api.core.constants.ResCode;

import com.yc.trip.api.business.enums.order.AllotStatus;
import com.yc.trip.api.core.enums.YesNoStatus;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 订单积分信息Query类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:12
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class OrderCreditQuery extends AbstractBasePageRequestDto {

    private static final long serialVersionUID = -1;

    
	/**
	 * 
	 */
	private Long id;

	/**
	 * 订单Id
	 */
	private Long orderId;

	/**
	 * 可分配积分
	 */
	private Integer credit;

	/**
	 * 销售推介人分成比例(百分比)
	 */
	private Integer inviteCommissionRate;

	/**
	 * 推介人积分
	 */
	private Integer inviteCredit;

	/**
	 * 销售分成比例(百分比)
	 */
	private Integer salesCommissionRate;

	/**
	 * 销售积分
	 */
	private Integer salesCredit;

	/**
	 * 分配状态0未分配1已分配(枚举:AllotStatus)
	 */
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
     * 当前页面
     */
    private Integer pageNo;

    /**
     * 每页记录数
     */
    private Integer pageSize;

    /**
     * 排序
     */
    private String orderby;

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

        
}
