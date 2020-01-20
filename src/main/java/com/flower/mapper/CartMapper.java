package com.flower.mapper;

import com.flower.pojo.Cart;
import com.flower.pojo.Staff;

import java.util.Map;

public interface CartMapper {

    // 根据用户id获取购物车
    Cart getCartByStaff(Staff staff);

    // 创建购物车
    void creatCart(Cart cart);

    // 将花卉添加进购物车
    void addCart(Map<String, Object> flowerMap);

    // 查询购物车列表
    Cart getCartList(Staff staff);

    // 从购物车中移出花卉
    void deleteFromCart(String flowerId, String cartId);
}
