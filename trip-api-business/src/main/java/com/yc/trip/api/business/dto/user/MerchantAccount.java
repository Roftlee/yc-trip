package com.yc.trip.api.business.dto.user;

import java.util.Date;
import java.util.List;

import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBasePageRequestDto;

import com.yc.trip.api.core.constants.ResCode;

import com.yc.trip.api.business.enums.user.MerchantType;
import com.yc.trip.api.business.enums.user.AccountType;
import com.yc.trip.api.core.enums.YesNoStatus;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 商户账号信息Dto类
 * 
 * @author My-Toolkits
 * @since 2019-04-22 20:40
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class MerchantAccount extends AbstractBasePageRequestDto {

    private static final long serialVersionUID = -1;

    
	/**
	 * 
	 */
	private Long id;

	/**
	 * 供应商id
	 */
	private Long merchantId;

	/**
	 * 用户id
	 */
	private Long userId;

	/**
	 * 商户类型1供应商2门店(枚举:MerchantType)
	 */
	private MerchantType merchantType;

	/**
	 * 账号类型1主账号2子账号(枚举:AccountType)
	 */
	private AccountType accountType;

	/**
	 * 是否删除(枚举:YesNoStatus[core])
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
    
    @Override
    public ResBean validateParam() {
        return ResCode.success;
    }
    
    /**
     * 流式设置排序字段
     * @param orderby
     * @return 
     */
    public MerchantAccount orderBy(String orderby){
        if(getPageNo() == null) setPageNo(1);
        if(getPageSize() == null) setPageSize(1000);
        setOrderby(orderby);
        return this;
    }
        
}
