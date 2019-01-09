package com.yc.trip.api.business.request.sales;

import com.yc.trip.api.core.constants.ResCode;
import lombok.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBaseRequestDto;

import java.util.Date;
import java.util.List;

/**
 * 优惠活动新增请求
 *
 * @author AsiQue
 * @since 2019/1/9 21:53
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SpecialOfferAddRequest extends AbstractBaseRequestDto {

    /**
     * 标题
     */
    private String title;

    /**
     * 所属门店Id
     */
    private Long storeId;

    /**
     * 活动图片
     */
    private String imageUrl;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 产品Id集合
     */
    private List<Long> productIds;

    @Override
    public ResBean validateParam() {
        if (StringUtils.isBlank(title)) {
            return ResCode.PARA_NULL.info("标题不能为空");
        }
        if (storeId == null || storeId <= 0) {
            return ResCode.PARA_NULL.info("所属门店Id不能为空");
        }
        if (StringUtils.isBlank(imageUrl)) {
            return ResCode.PARA_NULL.info("活动图片不能为空");
        }
        if (startTime == null) {
            return ResCode.PARA_NULL.info("开始时间不能为空");
        }
        if (endTime == null) {
            return ResCode.PARA_NULL.info("结束时间不能为空");
        }
        if (CollectionUtils.isEmpty(productIds)) {
            return ResCode.PARA_NULL.info("产品Id集合不能为空");
        }
        return ResCode.success;
    }
}
