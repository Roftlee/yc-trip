package com.yc.trip.web.controller.sales;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.sales.SpecialOffer;
import com.yc.trip.api.business.facade.sales.SpecialOfferProfFacade;
import com.yc.trip.api.business.item.sales.SpecialOfferItem;
import com.yc.trip.api.business.request.common.IdRequest;
import com.yc.trip.api.business.request.common.KeywordsPageRequest;
import com.yc.trip.api.business.request.sales.SpecialOfferAddRequest;
import com.yc.trip.api.business.request.sales.SpecialOfferUpdateRequest;
import com.yc.trip.web.controller.base.AbstractBaseController;
import org.go.api.core.dto.ResDto;
import org.go.framework.base.annotation.MvcValidate;
import org.go.framework.core.exception.PendingException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 优惠活动服务
 *
 * @author AsiQue
 * @since 2019/1/9 21:15
 */
@RestController
@RequestMapping("/product")
public class SpecialOfferController extends AbstractBaseController {

    @Reference(version = "1.0.0")
    private SpecialOfferProfFacade specialOfferProfFacade;// 优惠活动高级服务

    /**
     * 查询优惠活动列表分页
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/querySpecialOfferPage.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<PageInfo<SpecialOfferItem>> querySpecialOfferPage(@RequestBody KeywordsPageRequest request) throws PendingException {

        return new ResDto<>(specialOfferProfFacade.querySpecialOfferPage(request));
    }

    /**
     * 新增优惠活动
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/addSpecialOffer.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<SpecialOffer> addSpecialOffer(@RequestBody SpecialOfferAddRequest request) throws PendingException {

        return new ResDto<>(specialOfferProfFacade.addSpecialOffer(request));
    }

    /**
     * 获取优惠活动详情
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/getSpecialOffer.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<SpecialOfferItem> getSpecialOffer(@RequestBody IdRequest request) throws PendingException {

        return new ResDto<>(specialOfferProfFacade.getSpecialOffer(request));
    }

    /**
     * 更新优惠活动
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/updateSpecialOffer.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<?> updateSpecialOffer(@RequestBody SpecialOfferUpdateRequest request) throws PendingException {

        // 更新记录
        specialOfferProfFacade.updateSpecialOffer(request);

        return new ResDto<>();
    }

    /**
     * 删除优惠活动
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/deleteSpecialOffer.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<?> deleteSpecialOffer(@RequestBody IdRequest request) throws PendingException {

        // 删除记录
        specialOfferProfFacade.deleteSpecialOffer(request);

        return new ResDto<>();
    }
}
