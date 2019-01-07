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
 * OrderStatus枚举类
 *
 * @author My-Toolkits
 * @since 2019-01-06 17:15
 */
public enum OrderStatus implements BaseEnum<Enum<OrderStatus>, Integer>{
    ALL(-1, "全部"),
    
    ;
    

    private static Logger logger = LoggerFactory.getLogger(OrderStatus.class);

    private static final Object _LOCK = new Object();

    private static Map<Integer, OrderStatus> _MAP;
    private static List<OrderStatus> _LIST;
    private static List<OrderStatus> _ALL_LIST;
    
    private Integer value;
    private String name;

    static {
        synchronized (_LOCK) {
            Map<Integer, OrderStatus> map = new HashMap<>();
            List<OrderStatus> list = new ArrayList<>();
            List<OrderStatus> listAll = new ArrayList<>();
            for (OrderStatus e : OrderStatus.values()) {
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
    

    OrderStatus(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public static OrderStatus get(int value) {
        try {
            return _MAP.get(value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static List<OrderStatus> list() {
        return _LIST;
    }

    public static List<OrderStatus> listAll() {
        return _ALL_LIST;
    }
}


