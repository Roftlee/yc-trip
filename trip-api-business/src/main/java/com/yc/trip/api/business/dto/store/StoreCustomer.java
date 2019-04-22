package com.yc.trip.api.business.dto.store;

import java.util.Date;
import java.util.List;

import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBasePageRequestDto;

import com.yc.trip.api.core.constants.ResCode;

import com.yc.trip.api.core.enums.YesNoStatus;
import com.yc.trip.api.business.enums.store.SalesLevel;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 门店客户信息Dto类
 * 
 * @author My-Toolkits
 * @since 2019-04-22 22:08
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class StoreCustomer extends AbstractBasePageRequestDto {

    private static final long serialVersionUID = -1;

    
	/**
	 * 
	 */
	private Long id;

	/**
	 * 门店Id
	 */
	private Long storeId;

	/**
	 * 用户Id
	 */
	private Long userId;

	/**
	 * 是否VIP0否1是(枚举:YesNoStatus[core])
	 * 默认值：0
	 */
	private YesNoStatus isVip;

	/**
	 * 销售等级0普通用户1一级销售2二级销售3VIP销售(枚举:SalesLevel)
	 * 默认值：0
	 */
	private SalesLevel salesLevel;

	/**
	 * 利润分成(百分比)
	 * 默认值：0
	 */
	private Integer commissionRate;

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
    public StoreCustomer orderBy(String orderby){
        if(getPageNo() == null) setPageNo(1);
        if(getPageSize() == null) setPageSize(1000);
        setOrderby(orderby);
        return this;
    }
        
}
