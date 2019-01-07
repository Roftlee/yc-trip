package com.yc.trip.api.core.util;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

/**
 * Created by jinsheng on 15/11/26.
 */
public class RegionInfoComponent {

    /**
     * 拆分地区编码
     * 传入 101122，返回101122，101100，100000，0
     * 传入 101100，返回101100，100000，0
     * 传入 100000，返回100000，0
     * 传入 0，返回0
     * @param regionId 地区编码
     * @return
     */
    public static Set<Long> splitRegionId(Long regionId) {
        Set<Long> regionSet = Sets.newHashSet();
        if (regionId == 0) {
            regionSet.add(regionId);
            return regionSet;
        }

        regionSet.add(regionId);

        String regionIdStr = String.valueOf(regionId);
        while (true) {
            regionIdStr = StringUtils.substring(regionIdStr, 0, regionIdStr.length() - 2);
            if (StringUtils.isEmpty(regionIdStr)) {
                break;
            }
            regionSet.add(Long.valueOf(String.format("%-6s", regionIdStr).replace(" ", "0")));
        }

        regionSet.add(0L);
        return regionSet;
    }
}
