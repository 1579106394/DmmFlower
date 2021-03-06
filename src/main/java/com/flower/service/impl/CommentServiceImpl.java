package com.flower.service.impl;

import com.flower.mapper.CommentMapper;
import com.flower.mapper.LogMapper;
import com.flower.pojo.Comment;
import com.flower.pojo.Log;
import com.flower.pojo.Staff;
import com.flower.service.CommentService;
import com.flower.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private JedisClient jedisClient;

    @Override
    public Page<Comment> getCommentList(Page<Comment> p) {
        if (p.getCurrentPage() == null) {
            p.setCurrentPage(1);
        }

        if (p.getCurrentCount() == null) {
            p.setCurrentCount(10);
        }

        Integer currentPage = p.getCurrentPage();
        Integer currentCount = p.getCurrentCount();

        Integer index = (currentPage - 1) * currentCount;
        p.setIndex(index);

        List<Comment> commentList = commentMapper.getCommentList(p);
        p.setList(commentList);

        Integer totalCount = commentMapper.getCommentCount(p);
        p.setTotalCount(totalCount);

        Integer totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
        p.setTotalPage(totalPage);

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "查看了评论列表";
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);

        return p;
    }

    @Override
    public void addComment(Comment comment) {
        // 补全属性
        comment.setCommentId(UUID.randomUUID().toString());
        comment.setCommentCreatedTime(DateUtils.newDate());
        comment.setCommentFlag(1);
        commentMapper.addComment(comment);

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "发布了评论";
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);
    }

    @Override
    public void deleteCommentById(String commentId) {
        commentMapper.deleteCommentById(commentId);

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "将ID为" + commentId + "的评论删除";
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);
    }

    @Override
    public Comment getCommentById(String commentId) {
        return commentMapper.getCommentById(commentId);
    }

    @Override
    public List<Comment> getCommentListByFlowerId(String flowerId) {
        return commentMapper.getCommentListByFlowerId(flowerId);
    }

}
