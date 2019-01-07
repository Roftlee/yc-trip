package com.yc.trip.api.core.util;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class GsonUtil {

    public static Gson getDateFormatGson() {
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(Date.class, new UtilDateSerializer()).setDateFormat(DateFormat.LONG);
        return gb.create();
    }

    public static <T> T fromMap(Map map, Class<T> c) {
        return new Gson().fromJson(new Gson().toJson(map), c);
    }


    public static class UtilDateSerializer implements JsonSerializer<Date> {
        public JsonElement serialize(Date src, Type typeOfSrc,
                                     JsonSerializationContext context) {
            return new JsonPrimitive(new SimpleDateFormat("yyyyMMddHHmmss").format(src));
        }
    }

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        for(int i=0;i<600;i++){
            calendar.add(Calendar.MONTH,1);
            String month = new SimpleDateFormat("yyyyMM").format(calendar.getTime());
            System.out.println("INSERT INTO `cpit_business`.`stat_month`(`month`) VALUES ('"+month+"');");
        }
    }

}
