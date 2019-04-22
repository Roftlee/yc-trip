package com.yc.trip.api.business.bo.store;

import lombok.*;
import org.go.api.core.bo.BaseDomain;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CustomerDomain extends BaseDomain implements Serializable {

    private String customer;

    private  String phone;

    private String address;

    private String store;

    private String userId;
}
