package com.yc.trip.api.business.bo.wx;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.go.api.core.annotation.InsertRequired;
import org.go.api.core.bo.BaseDomain;

import com.yc.trip.api.core.enums.YesNoStatus;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 微信小程序信息Domain类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:39
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class WxAppDomain extends BaseDomain implements Serializable {

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
