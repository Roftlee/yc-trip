package com.yc.trip.api.business.bo.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.go.api.core.annotation.InsertRequired;
import org.go.api.core.bo.BaseDomain;

import com.yc.trip.api.business.enums.user.MerchantType;
import com.yc.trip.api.business.enums.user.AccountType;
import com.yc.trip.api.core.enums.YesNoStatus;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 商户账号信息Domain类
 * 
 * @author My-Toolkits
 * @since 2019-04-22 20:40
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class MerchantAccountDomain extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1;

    
	/**
	 * 
	 */
	private Long id;

	/**
	 * 供应商id
	 */
	@InsertRequired
	private Long merchantId;

	/**
	 * 用户id
	 */
	@InsertRequired
	private Long userId;

	/**
	 * 商户类型1供应商2门店(枚举:MerchantType)
	 */
	@InsertRequired
	private MerchantType merchantType;

	/**
	 * 账号类型1主账号2子账号(枚举:AccountType)
	 */
	@InsertRequired
	private AccountType accountType;

	/**
	 * 是否删除(枚举:YesNoStatus[core])
	 */
	@InsertRequired
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
