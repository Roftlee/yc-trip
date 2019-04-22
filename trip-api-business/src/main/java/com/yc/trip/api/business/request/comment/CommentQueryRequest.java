package com.yc.trip.api.business.request.comment;



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
public class CommentQueryRequest extends AbstractBaseRequestDto {



    private String customer;

    private String storeId;

    private String providerId;

    private String grade;

    private String commentStatus;

    private String orderId;

    @Override
    public ResBean validateParam() {
        if (StringUtils.isBlank(orderId)) {
            return ResCode.PARA_NULL.info("订单编号值不合法");
        }
        if (StringUtils.isBlank(commentStatus)) {
            return ResCode.PARA_NULL.info("订单状态值不合法");
        }
        return ResCode.success;
    }
}
