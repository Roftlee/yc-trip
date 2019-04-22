package com.yc.trip.api.business.service.comment;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.yc.trip.api.business.bo.comment.CommentDomain;
import com.yc.trip.api.business.bo.order.OrderConstractDomain;
import com.yc.trip.api.business.bo.order.OrderInfoDomain;
import com.yc.trip.api.business.bo.order.OrderNoticeDomain;
import com.yc.trip.api.business.bo.provider.ProviderDomain;
import com.yc.trip.api.business.bo.store.StoreDomain;
import com.yc.trip.api.business.dao.comment.CommentDao;
import com.yc.trip.api.business.dao.order.OrderConstractDao;
import com.yc.trip.api.business.dao.order.OrderInfoDao;
import com.yc.trip.api.business.dao.order.OrderNoticeDao;
import com.yc.trip.api.business.dao.provider.ProviderDao;
import com.yc.trip.api.business.dao.store.StoreDao;
import com.yc.trip.api.business.dto.comment.Comment;
import com.yc.trip.api.business.dto.order.OrderManagerInfo;
import com.yc.trip.api.business.dto.provider.Provider;
import com.yc.trip.api.business.dto.store.Store;
import com.yc.trip.api.business.facade.comment.CommentFacade;
import com.yc.trip.api.business.facade.order.OrderManageFacade;
import com.yc.trip.api.core.constants.ResCode;
import org.go.api.core.integration.AbstractDubboNativeService;
import org.go.api.core.util.BeanMapping;
import org.go.framework.core.exception.PendingException;
import org.go.framework.util.common.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0")
public class CommentFacadeImpl extends AbstractDubboNativeService implements CommentFacade {


    @Autowired
    private CommentDao dao;


    @Override
    public List<Comment> queryCommentList(Comment comment)  throws PendingException {
        try {
            // 转换成Domain对象
            CommentDomain cond = BeanMapping.map(comment, CommentDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(comment.getOrderby())) {
                PageHelper.orderBy(comment.getOrderby());
            }
            // 调数据库接口查询列表
            List<CommentDomain> resultList = dao.queryCommentList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, Comment.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.productCommentDBError, "订单信息列表查询失败");
        }
    }

    @Override
    public Comment getCommentById(String orderId) throws PendingException {
        try {

            CommentDomain comment = dao.getCommentByOrderId(orderId);
            // 转换返回结果
            return BeanMapping.map(comment, Comment.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.productCommentDBError, "订单信息列表查询失败");
        }
    }

    @Override
    public void updateComment(Comment comment) throws PendingException{
        try {
            // 转换成Domain对象
            CommentDomain cond = BeanMapping.map(comment, CommentDomain.class);
             dao.updateCommentStatus(cond);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.productCommentDBError, "订单信息列表查询失败");
        }


    }
}
