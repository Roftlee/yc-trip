package com.yc.trip.api.business.dto.comment;

import com.yc.trip.api.core.constants.ResCode;
import lombok.*;
import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBasePageRequestDto;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Comment extends AbstractBasePageRequestDto {

    private String customer;

    private String storeId;

    private String providerId;



    private String commentStatus;



    private String productname;

    private String grade;

    private String guideGrade;

    private String goodsGrade;
    private String planGrade;

    private String traficGrade;



    private String amout;

    private String orderid;

    private String commentTime;

    private String comment;

    private String phone;

    private String address;

    private String salesId;

    private String orderStatus;

    private String pic1;
    private String pic2;
    private String pic3;
    private String pic4;
    private String pic5;



    @Override
    public ResBean validateParam() {
        return ResCode.success;
    }
}
