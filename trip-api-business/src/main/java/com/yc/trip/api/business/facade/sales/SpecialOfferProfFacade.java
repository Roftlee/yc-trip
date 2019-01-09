package com.yc.trip.api.business.facade.sales;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.sales.SpecialOffer;
import com.yc.trip.api.business.item.sales.SpecialOfferItem;
import com.yc.trip.api.business.request.common.IdRequest;
import com.yc.trip.api.business.request.common.KeywordsPageRequest;
import com.yc.trip.api.business.request.sales.SpecialOfferAddRequest;
import com.yc.trip.api.business.request.sales.SpecialOfferUpdateRequest;
import org.go.framework.core.exception.PendingException;

/**
 * 优惠活动高级服务
 *
 * @author AsiQue
 * @since 2019/1/9 21:45
 */
public interface SpecialOfferProfFacade {

    /**
     * 查询优惠活动列表
     *
     * @param request
     * @return
     * @throws PendingException
     */
    PageInfo<SpecialOfferItem> querySpecialOfferPage(KeywordsPageRequest request) throws PendingException;

    /**
     * 新增优惠活动
     *
     * @param request
     * @return
     * @throws PendingException
     */
    SpecialOffer addSpecialOffer(SpecialOfferAddRequest request) throws PendingException;

    /**
     * 获取优惠活动详情
     *
     * @param request
     * @return
     * @throws PendingException
     */
    SpecialOfferItem getSpecialOffer(IdRequest request) throws PendingException;

    /**
     * 更新优惠活动
     *
     * @param request
     * @throws PendingException
     */
    void updateSpecialOffer(SpecialOfferUpdateRequest request) throws PendingException;

    /**
     * 删除优惠活动
     *
     * @param request
     * @throws PendingException
     */
    void deleteSpecialOffer(IdRequest request) throws PendingException;
}
