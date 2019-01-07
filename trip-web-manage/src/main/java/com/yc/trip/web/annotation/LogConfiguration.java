package com.yc.trip.web.annotation;

import com.yc.trip.api.business.enums.system.PlatformType;
import com.yc.trip.api.core.enums.OperTargetType;
import com.yc.trip.api.core.enums.OperType;

import java.lang.annotation.*;

/**
 * 该字段允许为空
 *
 * @author shuzhan
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogConfiguration {

    /**
     * 操作平台类型
     */
    PlatformType platformType() default PlatformType.BACKSTAGE;

    /**
     * 操作类型
     *
     * @return
     */
    OperType operType();

    /**
     * 操作对象类型
     *
     * @return
     */
    OperTargetType operTargetType();

    /**
     * 操作描述
     *
     * @return
     */
    String description() default "";
}
