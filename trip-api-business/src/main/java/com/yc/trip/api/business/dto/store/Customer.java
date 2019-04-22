package com.yc.trip.api.business.dto.store;


import com.yc.trip.api.core.constants.ResCode;
import lombok.*;
import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBasePageRequestDto;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Customer extends AbstractBasePageRequestDto {

    private String customerName;

    private String phone;

    private String address;

    private String store;

    private String userId;

    @Override
    public ResBean validateParam() {
            return ResCode.success;
    }
}
