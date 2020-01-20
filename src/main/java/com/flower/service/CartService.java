package com.flower.service;

import com.flower.pojo.Cart;
import com.flower.pojo.Flower;
import com.flower.pojo.Staff;

public interface CartService {

    // 把花卉加入购物车
    void addCart(Flower flower);

    // 获取购物车
    Cart getCartList(Staff staff);

    // 从购物车中移出花卉
    void deleteFromCart(String flowerId, String cartId);
}
