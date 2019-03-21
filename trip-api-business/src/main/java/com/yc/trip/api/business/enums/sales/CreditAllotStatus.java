package com.yc.trip.api.business.enums.sales;

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
 * CreditAllotStatus枚举类
 *
 * @author My-Toolkits
 * @since 2019-03-21 22:18
 */
public enum CreditAllotStatus implements BaseEnum<Enum<CreditAllotStatus>, Integer>{
    ALL(-1, "全部"),
    
    ;
    

    private static Logger logger = LoggerFactory.getLogger(CreditAllotStatus.class);

    private static final Object _LOCK = new Object();

    private static Map<Integer, CreditAllotStatus> _MAP;
    private static List<CreditAllotStatus> _LIST;
    private static List<CreditAllotStatus> _ALL_LIST;
    
    private Integer value;
    private String name;

    static {
        synchronized (_LOCK) {
            Map<Integer, CreditAllotStatus> map = new HashMap<>();
            List<CreditAllotStatus> list = new ArrayList<>();
            List<CreditAllotStatus> listAll = new ArrayList<>();
            for (CreditAllotStatus e : CreditAllotStatus.values()) {
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
    

    CreditAllotStatus(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public static CreditAllotStatus get(int value) {
        try {
            return _MAP.get(value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static List<CreditAllotStatus> list() {
        return _LIST;
    }

    public static List<CreditAllotStatus> listAll() {
        return _ALL_LIST;
    }
}


