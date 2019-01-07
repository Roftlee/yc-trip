package com.yc.trip.api.business.query.order;

import java.util.Date;
import java.util.List;

import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBasePageRequestDto;

import com.yc.trip.api.core.constants.ResCode;

import com.yc.trip.api.business.enums.order.OrderStatus;
import com.yc.trip.api.core.enums.YesNoStatus;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 订单信息Query类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:15
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class OrderInfoQuery extends AbstractBasePageRequestDto {

    private static final long serialVersionUID = -1;

    
	/**
	 * 
	 */
	private Long id;

	/**
	 * 订单名称
	 */
	private String name;

	/**
	 * 产品Id
	 */
	private Long productId;

	/**
	 * 数量
	 */
	private Integer amout;

	/**
	 * 客户姓名
	 */
	private String customer;

	/**
	 * 联系方式
	 */
	private String phone;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 所属供应商Id
	 */
	private Long providerId;

	/**
	 * 所属门店Id
	 */
	private Long storeId;

	/**
	 * 销售人员Id
	 */
	private Long salesId;

	/**
	 * 订单状态0待付款1待签约2待出行3待评价4已完成9已废除(枚举:OrderStatus)
	 */
	private OrderStatus orderStatus;

	/**
	 * 合同
	 */
	private String contract;

	/**
	 * 确认编号
	 */
	private String vertifyCode;

	/**
	 * 通知书
	 */
	private String notification;

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
