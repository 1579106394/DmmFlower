package com.flower.pojo;

import java.io.Serializable;

/**
 * CREATE TABLE `flower` (
 *   `flower_id` varchar(36) NOT NULL,
 *   `flower_name` varchar(36) NOT NULL,
 *   `flower_created_time` varchar(36) NOT NULL COMMENT '上架时间',
 *   `flower_created_staff` varchar(36) NOT NULL COMMENT '上架员工',
 *   `flower_price` double(10,0) NOT NULL DEFAULT '0' COMMENT '花卉单价',
 *   `flower_num` int(5) NOT NULL DEFAULT '0' COMMENT '库存',
 *   `flower_flag` int(2) NOT NULL DEFAULT '1' COMMENT '1正常2删除',
 *   `flower_type` varchar(36) NOT NULL COMMENT '分类id',
 *   `flower_img` varchar(120) DEFAULT NULL COMMENT '花卉图片',
 *   `flower_article` mediumtext COMMENT '花卉内容',
 *   PRIMARY KEY (`flower_id`),
 *   KEY `flower_type` (`flower_type`),
 *   KEY `staff` (`flower_created_staff`),
 *   CONSTRAINT `flower_type` FOREIGN KEY (`flower_type`) REFERENCES `type` (`type_id`),
 *   CONSTRAINT `staff` FOREIGN KEY (`flower_created_staff`) REFERENCES `staff` (`staff_id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
public class Flower implements Serializable {

    private String flowerId;
    private String flowerName;
    private String flowerCreatedTime;
    private String flowerImg;
    private String flowerArticle;
    private Staff staff;
    private Double flowerPrice;
    private Integer flowerNum;
    private Integer flowerFlag;

    private Type type;

    public String getFlowerId() {
        return flowerId;
    }

    public void setFlowerId(String flowerId) {
        this.flowerId = flowerId;
    }

    public String getFlowerName() {
        return flowerName;
    }

    public void setFlowerName(String flowerName) {
        this.flowerName = flowerName;
    }

    public String getFlowerCreatedTime() {
        return flowerCreatedTime;
    }

    public void setFlowerCreatedTime(String flowerCreatedTime) {
        this.flowerCreatedTime = flowerCreatedTime;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Double getFlowerPrice() {
        return flowerPrice;
    }

    public void setFlowerPrice(Double flowerPrice) {
        this.flowerPrice = flowerPrice;
    }

    public Integer getFlowerNum() {
        return flowerNum;
    }

    public void setFlowerNum(Integer flowerNum) {
        this.flowerNum = flowerNum;
    }

    public String getFlowerImg() {
        return flowerImg;
    }

    public void setFlowerImg(String flowerImg) {
        this.flowerImg = flowerImg;
    }

    public String getFlowerArticle() {
        return flowerArticle;
    }

    public void setFlowerArticle(String flowerArticle) {
        this.flowerArticle = flowerArticle;
    }

    public Integer getFlowerFlag() {
        return flowerFlag;
    }

    public void setFlowerFlag(Integer flowerFlag) {
        this.flowerFlag = flowerFlag;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
