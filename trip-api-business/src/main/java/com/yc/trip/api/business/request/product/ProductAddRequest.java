package com.yc.trip.api.business.request.product;

import com.yc.trip.api.core.constants.ResCode;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBaseRequestDto;
import org.go.framework.base.annotation.MvcOptional;

/**
 * ProductAddRequest
 *
 * @author: AsiQue
 * @date: 2019.03.28 22:11
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProductAddRequest extends AbstractBaseRequestDto {

    /**
     * 产品名称(标题)
     */
    private String name;

    /**
     * 产品简介
     */
    @MvcOptional
    private String briefDesc;

    /**
     * 所属分类Id
     */
    private Long sortId;

    /**
     * 所属地区Id
     */
    private Long regionId;

    /**
     * 所属供应商Id
     */
    private Long providerId;

    /**
     * 商品成本
     */
    private Double cost;

    /**
     * 售价
     */
    private Double price;

    /**
     * 费用说明
     */
    @MvcOptional
    private String priceDesc;

    /**
     * 门店分成比例(百分比)
     */
    private Integer commissionRate;

    /**
     * 图片链接
     */
    @MvcOptional
    private String imageUrl;

    /**
     * 视频链接
     */
    @MvcOptional
    private String videoUrl;

    /**
     * 行程介绍
     */
    @MvcOptional
    private String routeIntroduce;

    /**
     * 行程特色
     */
    @MvcOptional
    private String routeFeature;

    @Override
    public ResBean validateParam() {
        if (StringUtils.isBlank(name)) {
            return ResCode.PARA_NULL.info("产品名称(标题)不能为空");
        }
        if (sortId == null || sortId <= 0) {
            return ResCode.PARA_NULL.info("所属分类Id不能为空");
        }
        if (regionId == null || regionId <= 0) {
            return ResCode.PARA_NULL.info("所属地区Id不能为空");
        }
        if (providerId == null || providerId <= 0) {
            return ResCode.PARA_NULL.info("所属供应商Id不能为空");
        }
        if (cost == null) {
            return ResCode.PARA_NULL.info("商品成本不能为空");
        }
        if (price == null) {
            return ResCode.PARA_NULL.info("售价不能为空");
        }
        if (commissionRate == null || commissionRate <= 0) {
            return ResCode.PARA_NULL.info("门店分成比例(百分比)不能为空");
        }
        return ResCode.success;
    }
}
