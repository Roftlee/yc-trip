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
public enum Sex implements BaseEnum<Enum<Sex>, Integer> {
	ALL(-1, "全部"), Male(0, "男"), Female(1, "女");

	private static Logger logger = LoggerFactory.getLogger(Sex.class);

	private static final Object _LOCK = new Object();

	private static Map<Integer, Sex> _MAP;
	private static List<Sex> _LIST;
	private static List<Sex> _ALL_LIST;

	static {
		synchronized (_LOCK) {
			Map<Integer, Sex> map = new HashMap<>();
			List<Sex> list = new ArrayList<>();
			List<Sex> listAll = new ArrayList<>();
			for (Sex yesNoStatus : Sex.values()) {
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

	Sex(Integer value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Integer getValue() {
		return value;
	}

	public static Sex get(Integer value) {
		try {
			return _MAP.get(value);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public static List<Sex> list() {
		return _LIST;
	}

	public static List<Sex> listAll() {
		return _ALL_LIST;
	}
}
