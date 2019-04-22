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
 * AccountType枚举类
 *
 * @author My-Toolkits
 * @since 2019-04-22 20:40
 */
public enum AccountType implements BaseEnum<Enum<AccountType>, Integer>{
    ALL(-1, "全部"),
    PRIMARY(1, "主账号"),
    SUB(2, "子账号"),
    ;
    

    private static Logger logger = LoggerFactory.getLogger(AccountType.class);

    private static final Object _LOCK = new Object();

    private static Map<Integer, AccountType> _MAP;
    private static List<AccountType> _LIST;
    private static List<AccountType> _ALL_LIST;
    
    private Integer value;
    private String name;

    static {
        synchronized (_LOCK) {
            Map<Integer, AccountType> map = new HashMap<>();
            List<AccountType> list = new ArrayList<>();
            List<AccountType> listAll = new ArrayList<>();
            for (AccountType e : AccountType.values()) {
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
    

    AccountType(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public static AccountType get(int value) {
        try {
            return _MAP.get(value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static List<AccountType> list() {
        return _LIST;
    }

    public static List<AccountType> listAll() {
        return _ALL_LIST;
    }
}


