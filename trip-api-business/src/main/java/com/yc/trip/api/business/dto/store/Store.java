package com.yc.trip.api.business.dto.store;

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
 * 门店信息Dto类
 * 
 * @author My-Toolkits
 * @since 2019-04-23 22:03
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Store extends AbstractBasePageRequestDto {

    private static final long serialVersionUID = -1;

    
	/**
	 * 
	 */
	private Long id;

	/**
	 * 门店名称
	 */
	private String storeName;

	/**
	 * 关联品牌id
	 */
	private Long brandId;

	/**
	 * 联系人
	 */
	private String linkMan;

	/**
	 * 联系方式
	 */
	private String linkNum;

	/**
	 * 门店地址
	 */
	private String address;

	/**
	 * 到期时间
	 */
	private Date endTime;

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
	 * 开始时间
	 */
	private Date queryStartTime;

	/**
	 * 结束时间
	 */
	private Date queryEndTime;

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
    public Store orderBy(String orderby){
        if(getPageNo() == null) setPageNo(1);
        if(getPageSize() == null) setPageSize(1000);
        setOrderby(orderby);
        return this;
    }
        
}
