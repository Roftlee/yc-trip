package com.yc.trip.web.wx;

import com.yc.trip.web.bean.session.SessionUser;
import lombok.*;

import java.io.Serializable;

/**
 * 小程序code换取后的响应信息
 *
 * @author 小明同学
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Code2SessionResponse implements Serializable {

    private static final long serialVersionUID = -2316430045744178287L;

    /**
     * SessionUser
     */
    private SessionUser sessionUser;

    /**
     * Sid
     */
    private String sessionId;
}
