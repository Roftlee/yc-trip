package com.yc.trip.api.business.dto.wx;

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
 * 微信小程序信息Dto类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 19:05
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class WxApp extends AbstractEntityDto implements Serializable {

    private static final long serialVersionUID = -1;

    
	/**
	 * 微信AppId
	 */
	@InsertRequired
	private String wxAppId;

	/**
	 * 微信App原始Id
	 */
	@InsertRequired
	private String wxOrigId;

	/**
	 * 微信应用名称
	 */
	private String wxAppName;

	/**
	 * Token
	 */
	private String wxAppToken;

	/**
	 * 密钥
	 */
	private String wxAppSecret;

	/**
	 * 是否发布版0否1是(枚举:YesNoStatus[core])
	 */
	private YesNoStatus released;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;


}
