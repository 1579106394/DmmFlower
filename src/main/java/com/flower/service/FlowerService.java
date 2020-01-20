package com.flower.service;

import com.flower.pojo.Flower;
import com.flower.utils.Page;

import java.util.List;

public interface FlowerService {

    // 添加花卉
    void addFlower(Flower flower);

    // 分页查询花卉
    Page<Flower> getFlowerList(Page<Flower> p);

    // 根据id删除花卉
    void deleteFlowerById(String flowerId);

    // 根据id获取花卉
    Flower getFlowerById(String flowerId);

    // 编辑花卉
    void editFlower(Flower flower);

    // 从购物车中查询花卉是否存在
    Flower getFlowerFromCartByFlowerId(Flower flower);

    // 查询所有的花卉
    List<Flower> getAllFlowerById();

}
