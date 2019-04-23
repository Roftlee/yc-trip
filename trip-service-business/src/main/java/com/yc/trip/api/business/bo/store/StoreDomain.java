package com.yc.trip.api.business.bo.store;

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
 * 门店信息Domain类
 * 
 * @author My-Toolkits
 * @since 2019-04-23 22:03
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class StoreDomain extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1;

    
	/**
	 * 
	 */
	private Long id;

	/**
	 * 门店名称
	 */
	@InsertRequired
	private String storeName;

	/**
	 * 关联品牌id
	 */
	@InsertRequired
	private Long brandId;

	/**
	 * 联系人
	 */
	@InsertRequired
	private String linkMan;

	/**
	 * 联系方式
	 */
	@InsertRequired
	private String linkNum;

	/**
	 * 门店地址
	 */
	@InsertRequired
	private String address;

	/**
	 * 有效天数
	 */
	@InsertRequired
	private Integer totalDays;

	/**
	 * 到期时间
	 */
	@InsertRequired
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
     * id列表
     */
    private List<Long> ids;

    /**
     * 关键字
     */
    private String keywords;

}
