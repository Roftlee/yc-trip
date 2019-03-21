package com.yc.trip.api.business.enums.store;

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
 * SalesLevel枚举类
 *
 * @author My-Toolkits
 * @since 2019-03-21 22:24
 */
public enum SalesLevel implements BaseEnum<Enum<SalesLevel>, Integer>{
    ALL(-1, "全部"),
    
    ;
    

    private static Logger logger = LoggerFactory.getLogger(SalesLevel.class);

    private static final Object _LOCK = new Object();

    private static Map<Integer, SalesLevel> _MAP;
    private static List<SalesLevel> _LIST;
    private static List<SalesLevel> _ALL_LIST;
    
    private Integer value;
    private String name;

    static {
        synchronized (_LOCK) {
            Map<Integer, SalesLevel> map = new HashMap<>();
            List<SalesLevel> list = new ArrayList<>();
            List<SalesLevel> listAll = new ArrayList<>();
            for (SalesLevel e : SalesLevel.values()) {
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
    

    SalesLevel(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public static SalesLevel get(int value) {
        try {
            return _MAP.get(value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static List<SalesLevel> list() {
        return _LIST;
    }

    public static List<SalesLevel> listAll() {
        return _ALL_LIST;
    }
}


