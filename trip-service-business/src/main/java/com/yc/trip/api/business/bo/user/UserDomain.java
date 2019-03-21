package com.yc.trip.api.business.bo.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.go.api.core.annotation.InsertRequired;
import org.go.api.core.bo.BaseDomain;

import com.yc.trip.api.core.enums.Sex;
import com.yc.trip.api.business.enums.user.UserType;
import com.yc.trip.api.core.enums.YesNoStatus;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 用户信息Domain类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:31
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class UserDomain extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1;

    
	/**
	 * 
	 */
	private Long id;

	/**
	 * 小程序openid
	 */
	private String openId;

	/**
	 * 名称
	 */
	@InsertRequired
	private String name;

	/**
	 * 性别0男1女(枚举:Sex[core])
	 */
	@InsertRequired
	private Sex sex;

	/**
	 * 手机号
	 */
	@InsertRequired
	private String phone;

	/**
	 * 用户类型1超管2供应商4供应商销售人员5门店老板6门店一级销售7门店二级销售8VIP销售9游客(枚举:UserType)
	 */
	@InsertRequired
	private UserType userType;

	/**
	 * 头像
	 */
	private String avatar;

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
