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
 *
 * @author qatang
 * @since 2015-05-12 14:30
 */
public enum Month implements BaseEnum<Enum<Month>, Integer> {
	ALL(-1, "全部"),
	Jan(1, "一月"), 
	Feb(2, "二月"), 
	Mar(3, "三月"), 
	Apr(4, "四月"),
	May(5, "五月"), 
	Jun(6, "六月"), 
	Jul(7, "七月"),
	Aug(8, "八月"), 
	Sep(9, "九月"),
	Oct(10,"十月"),
	Nov(11, "十一月"), 
	Dec(12, "十二月");

	private static Logger logger = LoggerFactory.getLogger(Month.class);

	private static final Object _LOCK = new Object();

	private static Map<Integer, Month> _MAP;
	private static List<Month> _LIST;
	private static List<Month> _ALL_LIST;

	static {
		synchronized (_LOCK) {
			Map<Integer, Month> map = new HashMap<>();
			List<Month> list = new ArrayList<>();
			List<Month> listAll = new ArrayList<>();
			for (Month yesNoStatus : Month.values()) {
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

	Month(Integer value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Integer getValue() {
		return value;
	}

	public static Month get(Integer value) {
		try {
			return _MAP.get(value);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public static List<Month> list() {
		return _LIST;
	}

	public static List<Month> listAll() {
		return _ALL_LIST;
	}
}
