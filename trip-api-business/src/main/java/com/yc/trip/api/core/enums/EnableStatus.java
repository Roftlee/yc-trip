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
    public enum EnableStatus implements BaseEnum<Enum<EnableStatus>, Integer> {
        ALL(-1, "全部"),
        Enabled(1, "是"),
        Unenabled(0, "否");

        private static Logger logger = LoggerFactory.getLogger(EnableStatus.class);

        private static final Object _LOCK = new Object();

        private static Map<Integer, EnableStatus> _MAP;
        private static List<EnableStatus> _LIST;
        private static List<EnableStatus> _ALL_LIST;

        static {
            synchronized (_LOCK) {
                Map<Integer, EnableStatus> map = new HashMap<>();
                List<EnableStatus> list = new ArrayList<>();
                List<EnableStatus> listAll = new ArrayList<>();
                for (EnableStatus yesNoStatus : EnableStatus.values()) {
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

        EnableStatus(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }

        public static EnableStatus get(Integer value) {
            try {
                return _MAP.get(value);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                return null;
            }
        }

        public static List<EnableStatus> list() {
            return _LIST;
        }

        public static List<EnableStatus> listAll() {
            return _ALL_LIST;
        }
    }
