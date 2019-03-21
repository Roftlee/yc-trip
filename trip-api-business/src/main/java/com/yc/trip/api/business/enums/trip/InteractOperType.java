package com.yc.trip.api.business.enums.trip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.go.api.core.enums.BaseEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

/** 
 * InteractOperType枚举类
 *
 * @author My-Toolkits
 * @since 2019-03-21 22:29
 */
public enum InteractOperType implements BaseEnum<Enum<InteractOperType>, Integer>{
    ALL(-1, "全部"),
    
    ;
    

    private static Logger logger = LoggerFactory.getLogger(InteractOperType.class);

    private static final Object _LOCK = new Object();

    private static Map<Integer, InteractOperType> _MAP;
    private static List<InteractOperType> _LIST;
    private static List<InteractOperType> _ALL_LIST;
    
    private Integer value;
    private String name;

    static {
        synchronized (_LOCK) {
            Map<Integer, InteractOperType> map = new HashMap<>();
            List<InteractOperType> list = new ArrayList<>();
            List<InteractOperType> listAll = new ArrayList<>();
            for (InteractOperType e : InteractOperType.values()) {
                map.put(e.getValue(), e);
                listAll.add(e);
                if (!e.equals(ALL)) {
                    list.add(e);
                }
            }

            _MAP = ImmutableMap.copyOf(map);
            _LIST = ImmutableList.copyOf(list);
            _ALL_LIST = ImmutableList.copyOf(listAll);
        }
    }
    

    InteractOperType(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public static InteractOperType get(int value) {
        try {
            return _MAP.get(value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static List<InteractOperType> list() {
        return _LIST;
    }

    public static List<InteractOperType> listAll() {
        return _ALL_LIST;
    }
}


