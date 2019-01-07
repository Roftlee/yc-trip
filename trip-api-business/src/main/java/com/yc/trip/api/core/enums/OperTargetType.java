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
 * @author 操作对象类型
 * @since 2015-05-12 14:30
 */
public enum OperTargetType implements BaseEnum<Enum<OperTargetType>, Integer> {

    ALL(-1, "全部"),
    LOGIN(1, "登录"),
    OUT(2, "登出"),
    CERTIFI(3,"证书"),
    COUNTRY(4,"国别"),
    CPIT(5,"机构"),
    HS(6,"HS产品库"),
    PROVINCE(7,"区域"),
    USER(8,"用户"),
    VERIFI(9,"核查信息"),
    LOG(10,"核查日志"),
    PROGRESS(11,"核查进度"),
    LABEL(12,"标签"),
    ARTICLE(13,"文章"),
    TEMPLATE(14,"模板")
    ;

    private static Logger logger = LoggerFactory.getLogger(OperTargetType.class);

    private static final Object _LOCK = new Object();

    private static Map<Integer, OperTargetType> _MAP;
    private static List<OperTargetType> _LIST;
    private static List<OperTargetType> _ALL_LIST;

    static {
        synchronized (_LOCK) {
            Map<Integer, OperTargetType> map = new HashMap<>();
            List<OperTargetType> list = new ArrayList<>();
            List<OperTargetType> listAll = new ArrayList<>();
            for (OperTargetType yesNoStatus : OperTargetType.values()) {
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

    OperTargetType(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public static OperTargetType get(Integer value) {
        try {
            return _MAP.get(value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static List<OperTargetType> list() {
        return _LIST;
    }

    public static List<OperTargetType> listAll() {
        return _ALL_LIST;
    }
}
