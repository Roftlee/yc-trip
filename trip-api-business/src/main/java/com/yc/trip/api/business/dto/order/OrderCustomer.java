package com.yc.trip.api.business.dto.order;

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
 * 订单人员信息Dto类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:14
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class OrderCustomer extends AbstractEntityDto implements Serializable {

    private static final long serialVersionUID = -1;

    
	/**
	 * 
	 */
	private Long id;

	/**
	 * 订单号
	 */
	@InsertRequired
	private Long orderId;

	/**
	 * 姓名
	 */
	@InsertRequired
	private String name;

	/**
	 * 电话
	 */
	@InsertRequired
	private String phone;

	/**
	 * 身份证号
	 */
	@InsertRequired
	private String idCard;

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
