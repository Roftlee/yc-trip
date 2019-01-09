package com.yc.trip.web.controller.region;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.region.Region;
import com.yc.trip.api.business.dto.region.RegionSort;
import com.yc.trip.api.business.facade.region.RegionFacade;
import com.yc.trip.api.business.facade.region.RegionSortFacade;
import com.yc.trip.api.business.query.region.RegionQuery;
import com.yc.trip.api.business.query.region.RegionSortQuery;
import com.yc.trip.api.business.request.common.IdPageRequest;
import com.yc.trip.api.business.request.common.KeywordsPageRequest;
import com.yc.trip.api.business.request.common.KeywordsRequest;
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
 * 地区服务
 *
 * @author AsiQue
 * @since 2019/1/7 21:43
 */
@RestController
@RequestMapping("/region")
public class RegionController extends AbstractBaseController {

    @Reference(version = "1.0.0")
    private RegionFacade regionFacade;// 地区服务

    @Reference(version = "1.0.0")
    private RegionSortFacade regionSortFacade;// 地区分类服务

    /**
     * 首页-热门地区分页查询
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/queryHotRegionPage.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<PageInfo<Region>> queryHotRegionPage(@RequestBody KeywordsPageRequest request) throws PendingException {

        // 设置查询条件
        RegionQuery query = BeanMapping.map(request, RegionQuery.class);
        query.setIsDelete(YesNoStatus.NO);
        query.setIsHot(YesNoStatus.YES);

        return new ResDto<>(regionFacade.queryPage(query));
    }

    /**
     * 地区分类下地区分页查询
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/querySortedRegionPage.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<PageInfo<Region>> querySortedRegionPage(@RequestBody IdPageRequest request) throws PendingException {

        // 设置查询条件
        RegionQuery query = RegionQuery.builder()
                .regionSortId(request.getId())
                .isDelete(YesNoStatus.NO)
                .build();
        query.setPageNo(request.getPageNo());
        query.setPageSize(request.getPageSize());

        return new ResDto<>(regionFacade.queryPage(query));
    }

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

        return new ResDto<>(regionSortFacade.queryList(RegionSortQuery.builder().keywords(request.getKeywords()).isDelete(YesNoStatus.NO).build()));
    }
}
