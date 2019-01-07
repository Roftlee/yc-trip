package com.yc.trip.api.business.dto.store;

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
 * 门店信息Dto类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:47
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Store extends AbstractEntityDto implements Serializable {

    private static final long serialVersionUID = -1;

    
	/**
	 * 
	 */
	private Long id;

	/**
	 * 门店名称
	 */
	@InsertRequired
	private String storeName;

	/**
	 * 联系人
	 */
	@InsertRequired
	private String linkMan;

	/**
	 * 联系方式
	 */
	@InsertRequired
	private String linkNum;

	/**
	 * 门店地址
	 */
	@InsertRequired
	private String address;

	/**
	 * 有效天数
	 */
	@InsertRequired
	private Integer totalDays;

	/**
	 * 到期时间
	 */
	@InsertRequired
	private Date endTime;

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
