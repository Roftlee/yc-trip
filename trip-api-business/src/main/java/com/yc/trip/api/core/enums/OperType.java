package com.yc.trip.api.core.enums;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.go.api.core.enums.BaseEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 操作类型枚举
 * @since 2015-05-12 14:30
 */
public enum OperType implements BaseEnum<Enum<OperType>, Integer> {

    ALL(-1, "全部"),
    ADD(1, "添加"),
    UPDATE(2, "更新"),
    DELETE(3, "删除"),
    SEARCH(4, "查询"),
    LOGIN(5, "登录"),
    LOGOUT(6, "登出"),
    ;

    private static Logger logger = LoggerFactory.getLogger(OperType.class);

    private static final Object _LOCK = new Object();

    private static Map<Integer, OperType> _MAP;
    private static List<OperType> _LIST;
    private static List<OperType> _ALL_LIST;

    static {
        synchronized (_LOCK) {
            Map<Integer, OperType> map = new HashMap<>();
            List<OperType> list = new ArrayList<>();
            List<OperType> listAll = new ArrayList<>();
            for (OperType yesNoStatus : OperType.values()) {
                map.put(yesNoStatus.getValue(), yesNoStatus);
                listAll.add(yesNoStatus);
                if(!yesNoStatus.equals(ALL)) {
                    list.add(yesNoStatus);
                }
            }

            _MAP = ImmutableMap.copyOf(map);
            _LIST = ImmutableList.copyOf(list);
            _ALL_LIST = ImmutableList.copyOf(listAll);
        }
    }

    private Integer value;
    private String name;

    OperType(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public static OperType get(Integer value) {
        try {
            return _MAP.get(value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static List<OperType> list() {
        return _LIST;
    }

    public static List<OperType> listAll() {
        return _ALL_LIST;
    }
}
