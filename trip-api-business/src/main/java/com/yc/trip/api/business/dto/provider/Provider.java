package com.yc.trip.api.business.dto.provider;

import java.util.Date;
import java.util.List;

import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractEntityDto;
import org.go.api.core.annotation.InsertRequired;

import com.yc.trip.api.core.constants.ResCode;

import java.io.Serializable;

import com.yc.trip.api.core.enums.YesNoStatus;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 供应商信息Dto类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:24
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Provider extends AbstractEntityDto implements Serializable {

    private static final long serialVersionUID = -1;

    
	/**
	 * 
	 */
	private Long id;

	/**
	 * 供应商名称
	 */
	@InsertRequired
	private String providerName;

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
	 * 供应商地址
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
	 * 子账号个数
	 */
	@InsertRequired
	private Integer subCount;

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


}
