package com.yc.trip.api.business.dto.purchase;

import java.util.Date;
import java.util.List;

import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBasePageRequestDto;

import com.yc.trip.api.core.constants.ResCode;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 服务购买信息Dto类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:11
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class PurchaseRecord extends AbstractBasePageRequestDto {

    private static final long serialVersionUID = -1;

    
	/**
	 * 
	 */
	private Long id;

	/**
	 * 角色Id
	 */
	private Long roleId;

	/**
	 * 业务主键Id
	 */
	private Long businessId;

	/**
	 * 购买时长
	 */
	private Integer totalDays;

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
    public PurchaseRecord orderBy(String orderby){
        if(getPageNo() == null) setPageNo(1);
        if(getPageSize() == null) setPageSize(1000);
        setOrderby(orderby);
        return this;
    }
        
}
