package com.yc.trip.web.controller.comment;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yc.oss.dto.OssUploadDto;
import com.yc.oss.facade.OssUploadFacade;
import com.yc.trip.api.business.dto.comment.Comment;
import com.yc.trip.api.business.dto.order.OrderManagerInfo;
import com.yc.trip.api.business.dto.product.Product;
import com.yc.trip.api.business.dto.provider.Provider;
import com.yc.trip.api.business.dto.store.Store;
import com.yc.trip.api.business.dto.store.StoreSales;
import com.yc.trip.api.business.enums.user.UserType;
import com.yc.trip.api.business.facade.comment.CommentFacade;
import com.yc.trip.api.business.facade.order.OrderManageFacade;
import com.yc.trip.api.business.facade.store.StoreSalesFacade;
import com.yc.trip.api.business.request.comment.CommentQueryRequest;
import com.yc.trip.api.business.request.common.IdRequest;
import com.yc.trip.api.business.request.order.OrderQueryRequest;
import com.yc.trip.api.core.enums.YesNoStatus;
import com.yc.trip.web.controller.base.AbstractBaseController;
import org.apache.commons.io.IOUtils;
import org.go.api.core.dto.ResDto;
import org.go.framework.base.annotation.MvcValidate;
import org.go.framework.core.exception.PendingException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@RestController
@RequestMapping("/comment")
public class CommentController extends AbstractBaseController {


    @Reference(version = "1.0.0")
    private CommentFacade commentFacade;

    @Reference(version = "1.0.0")
    private OrderManageFacade orderFacade;

    @Reference(version = "1.0.0")
    private StoreSalesFacade storeSalesFacade;



    @RequestMapping(value = "/getProvider.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<List<Provider>>  getAllProvider()  throws PendingException {
        return new ResDto<>(orderFacade.provideList());
    }

    @RequestMapping(value = "/getStore.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<List<Store>>  getAllStore()  throws PendingException {
        return new ResDto<>(orderFacade.storeList());
    }


    @RequestMapping(value = "/queryComment.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<List<Comment>>  query(@RequestBody CommentQueryRequest request)  throws PendingException {
        UserType userTyp=getSessionUser().getUserType();
        if(userTyp.getName().equals("门店老板")){//如果登录用户为供应商，查询供应商对应的所有订单
            StoreSales ss=StoreSales.builder().userId(getSessionUser().getUserId()).build();
            request.setStoreId(storeSalesFacade.mustGet(ss).getStoreId()+"");
        }else if(userTyp.getName().equals("代理商")){

        }
        return new ResDto<>(commentFacade.queryCommentList(Comment.builder().customer(request.getCustomer()).storeId(request.getStoreId()).providerId(request.getProviderId()).grade(request.getGrade()).commentStatus(request.getCommentStatus()).build()));
    }


    @RequestMapping(value = "/getComment.do", method = RequestMethod.GET)
    @MvcValidate
    public ResDto<Comment>  getCommentByOrderId(String orderId)  throws PendingException {
        return new ResDto<>(commentFacade.getCommentById(orderId));
    }




    /**
     *评论-更新评论
     *
     * @param request
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/updateComment.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<?> updateComment(@RequestBody CommentQueryRequest request) throws PendingException {
        if(request.validateParam().isSuccess()) {
            commentFacade.updateComment(Comment.builder().orderid(request.getOrderId()).commentStatus(request.getCommentStatus()).build());
        }
        return new ResDto<>();
    }







}
