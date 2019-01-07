/**
 * 云畅网络科技
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.yc.trip.api.core.annotation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.go.framework.util.common.DateUtil;

import java.io.IOException;
import java.util.Date;

/**
 * 日期时间json格式转换格式化
 * <br> yyyy-MM-dd HH:mm:ss
 * 
 * @author dawei.liu
 * @version $Id: DateTimeJsonSerializer.java,v 0.1 2016年8月12日 上午9:50:21 xxx$Exp
 */
public class DateTimeJsonSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date value, JsonGenerator jGen, SerializerProvider provider) throws IOException, JsonProcessingException {
        String dateStr = ""; 
        if(value != null){
            dateStr = DateUtil.getNewFormatDateString(value);
        }
        jGen.writeString(dateStr);
    }

}
