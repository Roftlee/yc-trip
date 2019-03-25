package com.yc.trip.web.controller.region;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yc.oss.constants.ResCode;
import com.yc.trip.api.business.dto.region.RegionSort;
import com.yc.trip.api.business.facade.region.RegionSortFacade;
import com.yc.trip.api.business.request.common.IdRequest;
import com.yc.trip.api.business.request.common.KeywordsRequest;
import com.yc.trip.api.business.request.region.RegionSortAddRequest;
import com.yc.trip.api.business.request.region.RegionSortUpdateRequest;
import com.yc.trip.api.core.enums.YesNoStatus;
import com.yc.trip.web.controller.base.AbstractBaseController;
import org.go.api.core.dto.ResDto;
import org.go.api.core.util.BeanMapping;
import org.go.framework.base.annotation.MvcValidate;
import org.go.framework.core.exception.PendingException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 地区分类服务
 *
 * @author AsiQue
 * @since 2019/1/9 20:21
 */
@RestController
@RequestMapping("/region")
public class RegionSortController extends AbstractBaseController {

    @Reference(version = "1.0.0")
    private RegionSortFacade regionSortFacade;// 地区分类服务

    /**
     * 查询地区分类列表
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/queryRegionSortList.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<List<RegionSort>> queryRegionSortList(@RequestBody KeywordsRequest request) throws PendingException {

        return new ResDto<>(regionSortFacade.queryRegionSortList(RegionSort.builder().keywords(request.getKeywords()).isDelete(YesNoStatus.NO).build()));
    }

    /**
     * 新增地区分类
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/addRegionSort.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<RegionSort> addRegionSort(@RequestBody RegionSortAddRequest request) throws PendingException {

        // 地区分类重名检测
        RegionSort regionSort = regionSortFacade.getRegionSort(RegionSort.builder().name(request.getName()).build());
        if (regionSort != null) {
            ResCode.paramError.throwException("地区分类已存在");
        }

        return new ResDto<>(regionSortFacade.addRegionSort(BeanMapping.map(request, RegionSort.class)));
    }

    /**
     * 获取地区分类详情
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/getRegionSort.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<RegionSort> getRegionSort(@RequestBody IdRequest request) throws PendingException {

        return new ResDto<>(regionSortFacade.mustGet(RegionSort.builder().id(request.getId()).build()));
    }

    /**
     * 更新地区分类
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/updateRegionSort.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<?> updateRegionSort(@RequestBody RegionSortUpdateRequest request) throws PendingException {

        // 地区分类重名检测
        RegionSort regionSort = regionSortFacade.getRegionSort(RegionSort.builder().name(request.getName()).build());
        if (regionSort != null && !request.getId().equals(regionSort.getId())) {
            ResCode.paramError.throwException("地区分类已存在");
        }

        // 更新记录
        regionSortFacade.updateRegionSort(BeanMapping.map(request, RegionSort.class));

        return new ResDto<>();
    }

    /**
     * 删除地区分类
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/deleteRegionSort.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<?> deleteRegionSort(@RequestBody IdRequest request) throws PendingException {

        // 删除记录
        regionSortFacade.updateRegionSort(RegionSort.builder().id(request.getId()).isDelete(YesNoStatus.YES).build());

        return new ResDto<>();
    }
}
