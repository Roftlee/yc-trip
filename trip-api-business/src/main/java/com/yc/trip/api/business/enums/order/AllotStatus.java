package com.yc.trip.api.business.enums.order;

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
 * AllotStatus枚举类
 *
 * @author My-Toolkits
 * @since 2019-03-21 21:45
 */
public enum AllotStatus implements BaseEnum<Enum<AllotStatus>, Integer>{
    ALL(-1, "全部"),
    
    ;
    

    private static Logger logger = LoggerFactory.getLogger(AllotStatus.class);

    private static final Object _LOCK = new Object();

    private static Map<Integer, AllotStatus> _MAP;
    private static List<AllotStatus> _LIST;
    private static List<AllotStatus> _ALL_LIST;
    
    private Integer value;
    private String name;

    static {
        synchronized (_LOCK) {
            Map<Integer, AllotStatus> map = new HashMap<>();
            List<AllotStatus> list = new ArrayList<>();
            List<AllotStatus> listAll = new ArrayList<>();
            for (AllotStatus e : AllotStatus.values()) {
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
    

    AllotStatus(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public static AllotStatus get(int value) {
        try {
            return _MAP.get(value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static List<AllotStatus> list() {
        return _LIST;
    }

    public static List<AllotStatus> listAll() {
        return _ALL_LIST;
    }
}


