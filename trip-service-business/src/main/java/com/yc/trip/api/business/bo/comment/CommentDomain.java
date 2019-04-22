package com.yc.trip.api.business.bo.comment;

import com.yc.trip.api.core.enums.YesNoStatus;
import lombok.*;
import org.go.api.core.bo.BaseDomain;

import java.io.Serializable;
import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CommentDomain extends BaseDomain implements Serializable {

    private Long id;

    private String orderId;

    private String comment;

    private Date createdTime;;
    private YesNoStatus isDelete;

    private String commentStatus;
    private String commentPic1;
    private String commentPic2;
    private String commentPic3;
    private String commentPic4;
    private String commentPic5;

    private String grade;

    private String planGrade;

    private String guideGrade;

    private String goodsGrade;

    private String trafficGrade;

}
