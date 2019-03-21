package com.yc.trip.api.business.dto.wx;

import java.util.Date;
import java.util.List;

import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBasePageRequestDto;

import com.yc.trip.api.core.constants.ResCode;

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
 * @since 2019-03-21 22:39
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class WxApp extends AbstractBasePageRequestDto {

    private static final long serialVersionUID = -1;

    
	/**
	 * 微信AppId
	 */
	private String wxAppId;

	/**
	 * 微信App原始Id
	 */
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
    
    @Override
    public ResBean validateParam() {
        return ResCode.success;
    }
    
    /**
     * 流式设置排序字段
     * @param orderby
     * @return 
     */
    public WxApp orderBy(String orderby){
        if(getPageNo() == null) setPageNo(1);
        if(getPageSize() == null) setPageSize(1000);
        setOrderby(orderby);
        return this;
    }
        
}
