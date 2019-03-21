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
 * InteractType枚举类
 *
 * @author My-Toolkits
 * @since 2019-03-21 22:29
 */
public enum InteractType implements BaseEnum<Enum<InteractType>, Integer>{
    ALL(-1, "全部"),
    
    ;
    

    private static Logger logger = LoggerFactory.getLogger(InteractType.class);

    private static final Object _LOCK = new Object();

    private static Map<Integer, InteractType> _MAP;
    private static List<InteractType> _LIST;
    private static List<InteractType> _ALL_LIST;
    
    private Integer value;
    private String name;

    static {
        synchronized (_LOCK) {
            Map<Integer, InteractType> map = new HashMap<>();
            List<InteractType> list = new ArrayList<>();
            List<InteractType> listAll = new ArrayList<>();
            for (InteractType e : InteractType.values()) {
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
    

    InteractType(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public static InteractType get(int value) {
        try {
            return _MAP.get(value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static List<InteractType> list() {
        return _LIST;
    }

    public static List<InteractType> listAll() {
        return _ALL_LIST;
    }
}


