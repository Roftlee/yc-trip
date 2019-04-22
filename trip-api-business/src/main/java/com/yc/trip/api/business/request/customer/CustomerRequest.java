package com.yc.trip.api.business.request.customer;

import com.yc.trip.api.core.constants.ResCode;
import lombok.*;
import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBaseRequestDto;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CustomerRequest extends AbstractBaseRequestDto {

    private String customer;

    private String store;

    private String phone;

    private String address;

    private String userId;



    @Override
    public ResBean validateParam() {
        return ResCode.success;
    }
}
