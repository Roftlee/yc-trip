package com.yc.trip.api.business.dto.order;


import com.yc.trip.api.core.constants.ResCode;
import lombok.*;
import org.go.api.core.bean.ResBean;
import org.go.api.core.dto.AbstractBasePageRequestDto;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class OrderManagerInfo extends AbstractBasePageRequestDto {

    private Long id;

    private String name;



    private String amout;

    private String customer;

    private String phone;

    private String address;

    private String storeId;

    private String providerId;

    private String salesId;

    private String orderStatus;


    private List<String> fileName;

    private List<String> realFileName;

    private List<String> picUrl;

    private Long userId;


    @Override
    public ResBean validateParam() {
        return ResCode.success;
    }

    /**
     * 流式设置排序字段
     * @param orderby
     * @return
     */
    public OrderManagerInfo orderBy(String orderby){
        if(getPageNo() == null) setPageNo(1);
        if(getPageSize() == null) setPageSize(1000);
        setOrderby(orderby);
        return this;
    }


}
