package com.yc.trip.api.business.bo.provider;

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
 * 供应商信息Domain类
 * 
 * @author My-Toolkits
 * @since 2019-04-22 19:58
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ProviderDomain extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1;

    
	/**
	 * 
	 */
	private Long id;

	/**
	 * 供应商名称
	 */
	@InsertRequired
	private String providerName;

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
	 * 供应商地址
	 */
	@InsertRequired
	private String address;

	/**
	 * 到期时间
	 */
	@InsertRequired
	private Date endTime;

	/**
	 * 子账号个数
	 */
	@InsertRequired
	private Integer subCount;

	/**
	 * 营业执照
	 */
	private String licenseUrl;

	/**
	 * 合同文件
	 */
	private String contractUrl;

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
	 * 品牌id
	 */
	private Long brandId;

	/**
	 * 可用子账号个数
	 */
	private Integer remainSubCount;

	/**
	 * 已使用子账号
	 */
	private Integer usedSubCount;

     /**
     * id列表
     */
    private List<Long> ids;

    /**
     * 关键字
     */
    private String keywords;

	/**
	 * 查询开始时间
	 */
	private Date queryStartTime;

	/**
	 * 查询结束时间
	 */
	private Date queryEndTime;

}
