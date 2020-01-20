package com.flower.mapper;

import com.flower.pojo.Flower;
import com.flower.utils.Page;

import java.util.List;

public interface FlowerMapper {

    // 添加花卉
    void addFlower(Flower flower);

    // 查询花卉列表
    List<Flower> getFlowerList(Page<Flower> p);

    // 查询花卉总数
    Integer getFlowerCount(Page<Flower> p);

    // 根据id删除花卉
    void deleteFlowerById(String flowerId);

    // 根据id获取花卉
    Flower getFlowerById(String flowerId);

    // 编辑花卉
    void editFlower(Flower flower);

    // 从购物车中查询花卉是否存在
    Flower getFlowerFromCartByFlowerId(Flower flower);

    // 获取所有的花卉
    List<Flower> getAllFlowerById();

    // 根据花卉id查询这个花卉的评论
    List<Flower> getCommentListByFlowerId(String flowerId);
}
