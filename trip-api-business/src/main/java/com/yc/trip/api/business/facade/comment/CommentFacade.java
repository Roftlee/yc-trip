package com.yc.trip.api.business.facade.comment;

import com.yc.trip.api.business.dto.comment.Comment;
import org.go.framework.core.exception.PendingException;

import java.util.List;

public interface CommentFacade {

    public List<Comment>  queryCommentList(Comment comment) throws PendingException;

    public Comment  getCommentById(String  orderId) throws PendingException;

    public void updateComment(Comment comment) throws PendingException;
}
