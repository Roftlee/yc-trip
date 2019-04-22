package com.yc.trip.api.business.dto.sales;

import com.yc.trip.api.core.constants.ResCode;
import lombok.*;
import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBasePageRequestDto;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Sales extends AbstractBasePageRequestDto {



    private String name;

    private String phone;

    private String address;

    private String store;

    private String inviteUserId;

    private String salesLevel;

    private String commisionRate;

    private int orderCnt;

    private  long userId;


    @Override
    public ResBean validateParam() {
        return ResCode.success;
    }
}
