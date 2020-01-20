package com.flower.service.impl;

import com.flower.mapper.CartMapper;
import com.flower.mapper.FlowerMapper;
import com.flower.mapper.LogMapper;
import com.flower.pojo.Cart;
import com.flower.pojo.Flower;
import com.flower.pojo.Log;
import com.flower.pojo.Staff;
import com.flower.service.CartService;
import com.flower.utils.DateUtils;
import com.flower.utils.JedisClient;
import com.flower.utils.JsonUtils;
import com.flower.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private FlowerMapper flowerMapper;

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private JedisClient jedisClient;

    @Override
    public void addCart(Flower flower) {
        // 先查询该用户有没有购物车，如果没有，就创建一个
        Cart cart = cartMapper.getCartByStaff(flower.getStaff());
        if (cart == null) {
            // 没有购物车，创建一个
            cart = new Cart();
            // 补全属性
            cart.setCartId(UUID.randomUUID().toString());
            cart.setCartCreatedTime(DateUtils.newDate());
            cart.setStaff(flower.getStaff());
            cart.setCartFlag(1);
            cartMapper.creatCart(cart);
        }

        cart = cartMapper.getCartByStaff(flower.getStaff());

        Map<String, Object> flowerMap = new HashMap<>();
        flowerMap.put("flowerCartId", UUID.randomUUID().toString());
        flowerMap.put("flowerId", flower.getFlowerId());
        flowerMap.put("cartId", cart.getCartId());
        flowerMap.put("flowerNum", 1);
        cartMapper.addCart(flowerMap);

        Flower f = flowerMapper.getFlowerById(flower.getFlowerId());

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "将" + f.getFlowerName() + "添加进了购物车";
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);
    }

    @Override
    public Cart getCartList(Staff staff) {
        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "查看了购物车";
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);

        return cartMapper.getCartList(staff);
    }

    @Override
    public void deleteFromCart(String flowerId, String cartId) {
        cartMapper.deleteFromCart(flowerId, cartId);

        Flower flower = flowerMapper.getFlowerById(flowerId);

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "将" + flower.getFlowerName() + "移出了购物车";
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);
    }
}
