package com.yc.trip.api.core.util;

import com.yc.trip.api.core.constants.ResCode;
import org.go.framework.base.SpringContextHolder;
import org.go.framework.core.exception.PendingException;

public class BeanFactoryByEnumUtil {


    public static <T> T getBean(String beanName, Class<T> clazz) throws PendingException {
        T t = SpringContextHolder.getBean(beanName, clazz);
        if (t == null) {
            ResCode.SYS_FAIL.throwException("没有找到目标Bean");
        }
        return t;
    }
}
