package com.yc.trip.api.business.enums.motorcade;

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
 * MotorcadeType枚举类
 *
 * @author My-Toolkits
 * @since 2019-01-06 17:06
 */
public enum MotorcadeType implements BaseEnum<Enum<MotorcadeType>, Integer>{
    ALL(-1, "全部"),
    
    ;
    

    private static Logger logger = LoggerFactory.getLogger(MotorcadeType.class);

    private static final Object _LOCK = new Object();

    private static Map<Integer, MotorcadeType> _MAP;
    private static List<MotorcadeType> _LIST;
    private static List<MotorcadeType> _ALL_LIST;
    
    private Integer value;
    private String name;

    static {
        synchronized (_LOCK) {
            Map<Integer, MotorcadeType> map = new HashMap<>();
            List<MotorcadeType> list = new ArrayList<>();
            List<MotorcadeType> listAll = new ArrayList<>();
            for (MotorcadeType e : MotorcadeType.values()) {
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
    

    MotorcadeType(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public static MotorcadeType get(int value) {
        try {
            return _MAP.get(value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static List<MotorcadeType> list() {
        return _LIST;
    }

    public static List<MotorcadeType> listAll() {
        return _ALL_LIST;
    }
}


