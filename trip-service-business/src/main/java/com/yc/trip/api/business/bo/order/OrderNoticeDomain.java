package com.yc.trip.api.business.bo.order;


import lombok.*;
import org.go.api.core.bo.BaseDomain;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class OrderNoticeDomain extends BaseDomain implements Serializable {
    private Long id;

    private Long orderid;

    private String noticeFileName;

    private String realFileName;

    private Long userId;

    private Date uploadTime;

}
