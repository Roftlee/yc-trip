package com.yc.trip.api.business.request.wx;

import com.yc.trip.api.core.constants.ResCode;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBaseRequestDto;

/**
 * @author: AsiQue
 * @date :2018.08.17 15:36
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxDecryptRequest extends AbstractBaseRequestDto {

    /**
     * 加密数据
     */
    private String encryptedData;

    /**
     * 偏移量
     */
    private String iv;

    @Override
    public ResBean validateParam() {
        if (StringUtils.isBlank(encryptedData)) {
            return ResCode.PARA_NULL.info("加密数据不能为空");
        }
        if (StringUtils.isBlank(iv)) {
            return ResCode.PARA_NULL.info("偏移量不能为空");
        }
        return ResCode.success;
    }
}
