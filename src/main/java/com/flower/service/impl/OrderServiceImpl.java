package com.flower.service.impl;


import com.flower.mapper.*;
import com.flower.pojo.*;
import com.flower.service.OrderService;
import com.flower.utils.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private FlowerMapper flowerMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private HistoryMapper historyMapper;

    @Autowired
    private SaleMapper saleMapper;

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private JedisClient jedisClient;

    @Override
    public void addOrder(Order order) {
        // 补全属性
        order.setOrderId(UUID.randomUUID().toString());
        order.setOrderCreatedTime(DateUtils.newDate());
        order.setOrderFlag(1);
        Double price = 0.0;
        order.setOrderPrice(price);
        // 数据插入orders表中，这里插入是保证有这个订单，不然无法插入订单-花卉表
        orderMapper.addOrderNotCourier(order);
        //还剩price
        List<Flower> flowerList = order.getFlowerList();
        for (Flower flower : flowerList) {
            Flower f = flowerMapper.getFlowerById(flower.getFlowerId());
            Double flowerPrice = f.getFlowerPrice();
            price += flowerPrice;

            Map<String, Object> foMap = new HashMap<>();
            foMap.put("flowerOrderId", UUID.randomUUID().toString());
            foMap.put("flowerId", flower.getFlowerId());
            foMap.put("orderId", order.getOrderId());
            foMap.put("flowerNum", flower.getFlowerNum());
            // 在flower_order表中生成数据
            orderMapper.addFlowerInOrder(foMap);
        }
        order.setOrderPrice(price);
        // 下面更新订单价格
        orderMapper.updatePrice(order);

        // 订单生成完毕，删除购物车中的数据
        Staff staff = order.getStaff();
        Cart cart = cartMapper.getCartByStaff(staff);
        for (Flower flower : flowerList) {
            cartMapper.deleteFromCart(flower.getFlowerId(), cart.getCartId());
        }

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "添加了订单";
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);
    }

    @Override
    public Page<Order> getOrderList(Page<Order> p) {
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

        List<Order> orderList = orderMapper.getOrderList(p);
        // 获取到的列表，派送员有点问题，需要重新设置派送员
        for (Order order : orderList) {
            String courierId = orderMapper.getCourierByOrderId(order.getOrderId());
            if (StringUtils.isBlank(courierId)) {
                order.setCourier(null);
            } else {
                Staff courier = staffMapper.getStaffById(courierId);
                order.setCourier(courier);
            }
        }

        p.setList(orderList);

        Integer totalCount = orderMapper.getOrderCount(p);
        p.setTotalCount(totalCount);

        Integer totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
        p.setTotalPage(totalPage);

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "查看了订单列表";
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);

        return p;
    }

    @Override
    public void deleteOrderById(String orderId) {
        Order order = orderMapper.getOrderById(orderId);
        orderMapper.deleteOrderById(orderId);
        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "删除了ID为" + orderId + "的订单";
        Log log = LogUtils.newLog(time, article);
        // 判断订单是否已收货。收货前删除订单视为违约，积分-8
        Staff courier = order.getCourier();
        courier.setStaffPoint(-8);
        staffMapper.updatePoint(courier);
        logMapper.addLog(log);

    }

    @Override
    public void placeOrderById(String orderId) {
        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "将ID为" + orderId + "的订单下单";
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);
        // 查询订单的用户，积分+10
        Order order = orderMapper.getOrderById(orderId);
        Staff courier = order.getCourier();
        courier.setStaffPoint(10);
        staffMapper.updatePoint(courier);
        orderMapper.placeOrderById(orderId);
    }

    @Override
    public Order getOrderById(String orderId) {
        return orderMapper.getOrderById(orderId);
    }

    @Override
    public void addCourier(Order order) {
        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "给订单" + order.getOrderId() + "分配了配送员，配送员是" + order.getCourier().getStaffName();
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);

        orderMapper.addCourier(order);
    }

    @Override
    public void deliver(String orderId) {
        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "将ID 为" + orderId + "的订单发货";
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);

        orderMapper.deliver(orderId);
    }

    @Override
    public void collect(String orderId) {
        orderMapper.collect(orderId);
        // 确定收货后，需要生成财务记录，并且在公司财务上加上订单金额
        Order order = orderMapper.getOrderById(orderId);
        Double price = order.getOrderPrice();
        historyMapper.pay(0 - price); // 支出负金额就是收入
        Staff staff = order.getStaff();
        History history = HistoryUtils.newHistory(price, "用户" + staff.getStaffName() + "订单付款，收入" + price + "元", 2);
        historyMapper.addHistory(history);

        // 库存中减去对应的花卉的数量
        List<Flower> flowerList = order.getFlowerList();
        for (Flower flower : flowerList) {
            // 库存中减去对应的数量
            Integer num = orderMapper.getFlowerNumByFlowerIdAndOrderId(flower.getFlowerId(), order.getOrderId());
            flower.setFlowerNum(num);
            Flower f = flowerMapper.getFlowerById(flower.getFlowerId());
            f.setFlowerNum(f.getFlowerNum() - flower.getFlowerNum());
            flowerMapper.editFlower(f);
            // 对应的品种中减去订单中的数量
            Type type = typeMapper.getTypeByFlowerId(flower.getFlowerId());
            type.setTypeNum(type.getTypeNum() - flower.getFlowerNum());
            typeMapper.editType(type);
            // 销售记录表中记录卖出的数量
            Sale sale = new Sale();
            sale.setSaleId(UUID.randomUUID().toString());
            sale.setFlower(flower);
            sale.setSaleCreatedTime(DateUtils.newDate());
            sale.setSaleNum(flower.getFlowerNum());
            sale.setSaleFlag(1);

            saleMapper.addSale(sale);
        }

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "将ID 为" + orderId + "的订单收货";
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);

    }
}
