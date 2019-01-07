package com.yc.trip.api.business.enums.system;

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
 * PlatformType枚举类
 *
 * @author My-Toolkits
 * @since 2019-01-06 17:11
 */
public enum PlatformType implements BaseEnum<Enum<PlatformType>, Integer>{
    ALL(-1, "全部"),
    BACKSTAGE(1, "管理后台"),
    MINI_PROGRAM(2, "小程序"),
    ;
    

    private static Logger logger = LoggerFactory.getLogger(PlatformType.class);

    private static final Object _LOCK = new Object();

    private static Map<Integer, PlatformType> _MAP;
    private static List<PlatformType> _LIST;
    private static List<PlatformType> _ALL_LIST;
    
    private Integer value;
    private String name;

    static {
        synchronized (_LOCK) {
            Map<Integer, PlatformType> map = new HashMap<>();
            List<PlatformType> list = new ArrayList<>();
            List<PlatformType> listAll = new ArrayList<>();
            for (PlatformType e : PlatformType.values()) {
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
    

    PlatformType(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public static PlatformType get(int value) {
        try {
            return _MAP.get(value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static List<PlatformType> list() {
        return _LIST;
    }

    public static List<PlatformType> listAll() {
        return _ALL_LIST;
    }
}


