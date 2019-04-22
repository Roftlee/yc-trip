package com.yc.trip.api.business.request.provider;

import com.yc.trip.api.business.dto.brand.Brand;
import lombok.*;
import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBaseRequestDto;

import java.util.Date;
import java.util.List;

/**
 * ProviderAddRequest
 *
 * @author: AsiQue
 * @date: 2019.04.22 19:49
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProviderAddRequest extends AbstractBaseRequestDto {

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
     * 到期时间
     */
    private Date endTime;

    /**
     * 子账号个数
     */
    private Integer subCount;

    /**
     * 关联品牌id列表
     */
    private List<Long> brandIds;

    @Override
    public ResBean validateParam() {
        return null;
    }
}
