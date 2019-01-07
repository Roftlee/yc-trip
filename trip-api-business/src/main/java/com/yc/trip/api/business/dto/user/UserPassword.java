package com.yc.trip.api.business.dto.user;

import java.util.Date;
import java.util.List;

import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractEntityDto;
import org.go.api.core.annotation.InsertRequired;

import com.yc.trip.api.core.constants.ResCode;

import java.io.Serializable;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 用户密码信息Dto类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 18:08
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class UserPassword extends AbstractEntityDto implements Serializable {

    private static final long serialVersionUID = -1;

    
	/**
	 * id
	 */
	private Long id;

	/**
	 * 用户Id
	 */
	@InsertRequired
	private Long userId;

	/**
	 * 盐值
	 */
	@InsertRequired
	private String salt;

	/**
	 * 密码
	 */
	@InsertRequired
	private String password;

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


}
