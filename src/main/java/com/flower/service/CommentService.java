package com.flower.service;

import com.flower.pojo.Comment;
import com.flower.utils.Page;

import java.util.List;

public interface CommentService {

    // 获取评论列表
    Page<Comment> getCommentList(Page<Comment> p);

    // 添加评论
    void addComment(Comment comment);

    // 根据id删除评论
    void deleteCommentById(String commentId);

    // 根据id获取评论
    Comment getCommentById(String commentId);

    // 根据花卉id查询这个花卉的评论
    List<Comment> getCommentListByFlowerId(String flowerId);
}
