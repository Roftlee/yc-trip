package com.yc.trip.api.business.enums.user;

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
 * MerchantType枚举类
 *
 * @author My-Toolkits
 * @since 2019-04-22 20:40
 */
public enum MerchantType implements BaseEnum<Enum<MerchantType>, Integer>{
    ALL(-1, "全部"),
    PROVIDER(1, "供应商"),
    STORE(2, "门店"),
    ;
    

    private static Logger logger = LoggerFactory.getLogger(MerchantType.class);

    private static final Object _LOCK = new Object();

    private static Map<Integer, MerchantType> _MAP;
    private static List<MerchantType> _LIST;
    private static List<MerchantType> _ALL_LIST;
    
    private Integer value;
    private String name;

    static {
        synchronized (_LOCK) {
            Map<Integer, MerchantType> map = new HashMap<>();
            List<MerchantType> list = new ArrayList<>();
            List<MerchantType> listAll = new ArrayList<>();
            for (MerchantType e : MerchantType.values()) {
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
    

    MerchantType(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public static MerchantType get(int value) {
        try {
            return _MAP.get(value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static List<MerchantType> list() {
        return _LIST;
    }

    public static List<MerchantType> listAll() {
        return _ALL_LIST;
    }
}


