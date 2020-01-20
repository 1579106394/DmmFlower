package com.flower.pojo;

import java.io.Serializable;
import java.util.*;

/**
 * CREATE TABLE `orders` (
 *   `order_id` varchar(36) NOT NULL,
 *   `order_created_time` varchar(36) NOT NULL,
 *   `order_staff` varchar(36) NOT NULL COMMENT '买家id',
 *   `order_price` double(10,0) NOT NULL DEFAULT '0' COMMENT '订单总价',
 *   `order_courier` varchar(36) DEFAULT NULL COMMENT '快递员id（员工）',
 *   `order_add` varchar(36) NOT NULL COMMENT '订单收货地址',
 *   `order_flag` int(2) NOT NULL DEFAULT '1' COMMENT '1正常2删除3已下单4已发货5已收货',
 *   PRIMARY KEY (`order_id`),
 *   KEY `order_staff` (`order_staff`),
 *   KEY `order_courier` (`order_courier`),
 *   KEY `order_add` (`order_add`),
 *   CONSTRAINT `order_add` FOREIGN KEY (`order_add`) REFERENCES `address` (`address_id`),
 *   CONSTRAINT `order_courier` FOREIGN KEY (`order_courier`) REFERENCES `staff` (`staff_id`),
 *   CONSTRAINT `order_staff` FOREIGN KEY (`order_staff`) REFERENCES `staff` (`staff_id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
public class Order implements Serializable {

    private String orderId;
    private String orderCreatedTime;
    private Staff staff;
    private Staff courier;
    private Double orderPrice;
    private Address address;
    private Integer orderFlag;
    private List<Flower> flowerList = new ArrayList<Flower>();

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderCreatedTime() {
        return orderCreatedTime;
    }

    public void setOrderCreatedTime(String orderCreatedTime) {
        this.orderCreatedTime = orderCreatedTime;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Staff getCourier() {
        return courier;
    }

    public void setCourier(Staff courier) {
        this.courier = courier;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getOrderFlag() {
        return orderFlag;
    }

    public void setOrderFlag(Integer orderFlag) {
        this.orderFlag = orderFlag;
    }

    public List<Flower> getFlowerList() {
        return flowerList;
    }

    public void setFlowerList(List<Flower> flowerList) {
        this.flowerList = flowerList;
    }
}
