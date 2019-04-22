package com.yc.trip.api.business.bo.sales;

import com.yc.trip.api.business.enums.sales.CreditAllotStatus;
import com.yc.trip.api.core.enums.YesNoStatus;
import lombok.*;
import org.go.api.core.annotation.InsertRequired;
import org.go.api.core.bo.BaseDomain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
public class SalesDomain extends BaseDomain implements Serializable {

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


	private String store;

	private String name;

	private String phone;

	private String address;


	private String userName;

	/**
	 * 是否删除0否1是(枚举:YesNoStatus[core])
	 * 默认值：0
	 */
	private YesNoStatus isDelete;


	private String inviteUserId;

	private String commissionRate;

	private String salesLevel;

	private int orderCnt;
}
