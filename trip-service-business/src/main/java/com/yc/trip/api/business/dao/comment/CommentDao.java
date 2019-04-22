package com.yc.trip.api.business.dao.comment;

import com.yc.trip.api.business.bo.comment.CommentDomain;
import com.yc.trip.api.business.bo.order.OrderInfoDomain;
import com.yc.trip.api.business.dto.comment.Comment;

import java.util.List;

public interface CommentDao {


    List<CommentDomain> queryCommentList(CommentDomain orderInfoDomain);

    CommentDomain  getCommentByOrderId(String commentId);

    void updateCommentStatus(CommentDomain comment);

}
