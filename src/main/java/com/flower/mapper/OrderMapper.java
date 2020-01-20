package com.flower.mapper;

import com.flower.pojo.Order;
import com.flower.utils.Page;

import java.util.List;
import java.util.Map;

public interface OrderMapper {

    // 提交订单（没有送货员）
    void addOrderNotCourier(Order order);

    // 在flower_order表中生成对应的数据
    void addFlowerInOrder(Map<String, Object> foMap);

    // 获取订单列表
    List<Order> getOrderList(Page<Order> p);

    // 获取订单数量
    Integer getOrderCount(Page<Order> p);

    // 根据id删除订单
    void deleteOrderById(String orderId);

    // 根据id下单
    void placeOrderById(String orderId);

    // 根据id获取订单
    Order getOrderById(String orderId);

    // 根据订单id获取派送员
    String getCourierByOrderId(String orderId);

    // 添加派送员
    void addCourier(Order order);

    // 收货
    void collect(String orderId);

    // 发货
    void deliver(String orderId);

    // 根据flowerId和orderId查花卉数量
    Integer getFlowerNumByFlowerIdAndOrderId(String flowerId, String orderId);

    // 更新订单价格
    void updatePrice(Order order);
}
