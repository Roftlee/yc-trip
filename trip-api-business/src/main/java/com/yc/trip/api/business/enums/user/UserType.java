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
 * UserType枚举类
 *
 * @author My-Toolkits
 * @since 2019-01-07 20:48
 */
public enum UserType implements BaseEnum<Enum<UserType>, Integer>{
    ALL(-1, "全部"),
    ADMIN(1, "超管"),
    PROVIDER(2, "供应商"),
    STORE_MANGER(3, "门店老板"),
    PROVIDER_SALES(4, "供应商运营人员"),
    STORE_SALES(5, "门店运营人员"),
    STORE_SALES_LEVEL_ONE(6, "门店一级销售"),
    STORE_SALES_LEVEL_TWO(7, "门店二级销售"),
    SALES_VIP(8, "VIP销售"),
    CUSTOMER(9, "游客"),
    ;
    

    private static Logger logger = LoggerFactory.getLogger(UserType.class);

    private static final Object _LOCK = new Object();

    private static Map<Integer, UserType> _MAP;
    private static List<UserType> _LIST;
    private static List<UserType> _ALL_LIST;
    
    private Integer value;
    private String name;

    static {
        synchronized (_LOCK) {
            Map<Integer, UserType> map = new HashMap<>();
            List<UserType> list = new ArrayList<>();
            List<UserType> listAll = new ArrayList<>();
            for (UserType e : UserType.values()) {
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
    

    UserType(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public static UserType get(int value) {
        try {
            return _MAP.get(value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static List<UserType> list() {
        return _LIST;
    }

    public static List<UserType> listAll() {
        return _ALL_LIST;
    }
}


