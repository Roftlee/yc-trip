package com.yc.trip.api.business.query.provider;

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
 * 供应商信息Query类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:24
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ProviderQuery extends AbstractBasePageRequestDto {

    private static final long serialVersionUID = -1;

    
	/**
	 * 
	 */
	private Long id;

	/**
	 * 供应商名称
	 */
	private String providerName;

	/**
	 * 联系人
	 */
	private String linkMan;

	/**
	 * 联系方式
	 */
	private String linkNum;

	/**
	 * 供应商地址
	 */
	private String address;

	/**
	 * 有效天数
	 */
	private Integer totalDays;

	/**
	 * 到期时间
	 */
	private Date endTime;

	/**
	 * 子账号个数
	 */
	private Integer subCount;

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
     * 当前页面
     */
    private Integer pageNo;

    /**
     * 每页记录数
     */
    private Integer pageSize;

    /**
     * 排序
     */
    private String orderby;

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

        
}
