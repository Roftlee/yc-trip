package com.yc.trip.api.business.request.order;



import com.yc.trip.api.core.constants.ResCode;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBaseRequestDto;

/**
 * 订单查询请求
 *
 * @author jason
 * @since 2019/1/9 19:59
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OrderQueryRequest extends AbstractBaseRequestDto {

    /**
     * id
     */
    private Long id;

    private String customer;

    private String storeId;

    private String providerId;

    private String status;

    @Override
    public ResBean validateParam() {
        return ResCode.success;
    }
}
