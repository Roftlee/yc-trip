package com.yc.trip.api.business.bo.system;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.go.api.core.annotation.InsertRequired;
import org.go.api.core.bo.BaseDomain;

import com.yc.trip.api.business.enums.system.PlatformType;
import com.yc.trip.api.core.enums.OperTargetType;
import com.yc.trip.api.core.enums.OperType;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 操作日志Domain类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 21:44
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class OperateLogDomain extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1;

    
	/**
	 * 
	 */
	private Long id;

	/**
	 * 用户Id
	 */
	@InsertRequired
	private Long userId;

	/**
	 * 用户名
	 */
	@InsertRequired
	private String userName;

	/**
	 * 平台类型1管理后台(枚举:PlatformType)
	 */
	private PlatformType platformType;

	/**
	 * 功能模块(枚举:OperTargetType[core])
	 */
	@InsertRequired
	private OperTargetType operTargetType;

	/**
	 * 操作类型(枚举:OperType[core])
	 */
	@InsertRequired
	private OperType operType;

	/**
	 * 操作描述
	 */
	private String operDesc;

	/**
	 * 访问IP
	 */
	private String remoteIp;

	/**
	 * 请求地址
	 */
	private String requestUrl;

	/**
	 * 请求数据
	 */
	private String requestData;

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
