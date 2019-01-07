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
 * @author qatang
 * @since 2015-05-12 14:30
 */
public enum YesNoStatus implements BaseEnum<Enum<YesNoStatus>, Integer> {
    ALL(-1, "全部"),
    YES(1, "是"),
    NO(0, "否");

    private static Logger logger = LoggerFactory.getLogger(YesNoStatus.class);

    private static final Object _LOCK = new Object();

    private static Map<Integer, YesNoStatus> _MAP;
    private static List<YesNoStatus> _LIST;
    private static List<YesNoStatus> _ALL_LIST;

    static {
        synchronized (_LOCK) {
            Map<Integer, YesNoStatus> map = new HashMap<>();
            List<YesNoStatus> list = new ArrayList<>();
            List<YesNoStatus> listAll = new ArrayList<>();
            for (YesNoStatus yesNoStatus : YesNoStatus.values()) {
                map.put(yesNoStatus.getValue(), yesNoStatus);
                listAll.add(yesNoStatus);
                if (!yesNoStatus.equals(ALL)) {
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

    YesNoStatus(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public static YesNoStatus get(Integer value) {
        try {
            return _MAP.get(value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static YesNoStatus from(Boolean b) {
        return b ? YES : NO;
    }

    public static List<YesNoStatus> list() {
        return _LIST;
    }

    public static List<YesNoStatus> listAll() {
        return _ALL_LIST;
    }
}
