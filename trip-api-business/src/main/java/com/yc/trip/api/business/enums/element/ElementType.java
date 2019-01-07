package com.yc.trip.api.business.enums.element;

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
 * ElementType枚举类
 *
 * @author My-Toolkits
 * @since 2019-01-06 18:27
 */
public enum ElementType implements BaseEnum<Enum<ElementType>, Integer>{
    ALL(-1, "全部"),
    
    ;
    

    private static Logger logger = LoggerFactory.getLogger(ElementType.class);

    private static final Object _LOCK = new Object();

    private static Map<Integer, ElementType> _MAP;
    private static List<ElementType> _LIST;
    private static List<ElementType> _ALL_LIST;
    
    private Integer value;
    private String name;

    static {
        synchronized (_LOCK) {
            Map<Integer, ElementType> map = new HashMap<>();
            List<ElementType> list = new ArrayList<>();
            List<ElementType> listAll = new ArrayList<>();
            for (ElementType e : ElementType.values()) {
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
    

    ElementType(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public static ElementType get(int value) {
        try {
            return _MAP.get(value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static List<ElementType> list() {
        return _LIST;
    }

    public static List<ElementType> listAll() {
        return _ALL_LIST;
    }
}


