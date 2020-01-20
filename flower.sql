/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50549
Source Host           : localhost:3306
Source Database       : flower

Target Server Type    : MYSQL
Target Server Version : 50549
File Encoding         : 65001

Date: 2018-11-30 13:02:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `flower`
-- ----------------------------
DROP TABLE IF EXISTS `flower`;
CREATE TABLE `flower` (
  `flw_id` varchar(36) NOT NULL,
  `flw_name` varchar(36) NOT NULL,
  `flw_price` double NOT NULL,
  `flw_type` varchar(36) NOT NULL COMMENT '花卉品种',
  `flw_num` int(11) NOT NULL DEFAULT '0' COMMENT '花卉数量',
  `flw_delete` int(2) NOT NULL DEFAULT '1' COMMENT '1正常2删除',
  PRIMARY KEY (`flw_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flower
-- ----------------------------
INSERT INTO `flower` VALUES ('45ed33b1-da6d-11e8-bfc0-e4f89cbef13a', '测试', '250', '9f2c07e6-d9ef-11e8-9b2d-e4f89cbef13a', '10', '1');
INSERT INTO `flower` VALUES ('463047f5-da6d-11e8-bfc0-e4f89cbef13a', '测试', '250', '9f2c07e6-d9ef-11e8-9b2d-e4f89cbef13a', '10', '1');
INSERT INTO `flower` VALUES ('4648d9f6-da6d-11e8-bfc0-e4f89cbef13a', '测试', '250', '9f2c07e6-d9ef-11e8-9b2d-e4f89cbef13a', '10', '1');
INSERT INTO `flower` VALUES ('4663c8ea-da6d-11e8-bfc0-e4f89cbef13a', '测试', '250', '9f2c07e6-d9ef-11e8-9b2d-e4f89cbef13a', '10', '1');
INSERT INTO `flower` VALUES ('46926099-da6d-11e8-bfc0-e4f89cbef13a', '测试', '250', '9f2c07e6-d9ef-11e8-9b2d-e4f89cbef13a', '10', '1');
INSERT INTO `flower` VALUES ('46bc3db9-da6d-11e8-bfc0-e4f89cbef13a', '测试', '250', '9f2c07e6-d9ef-11e8-9b2d-e4f89cbef13a', '10', '1');
INSERT INTO `flower` VALUES ('46ea0139-da6d-11e8-bfc0-e4f89cbef13a', '测试', '250', '9f2c07e6-d9ef-11e8-9b2d-e4f89cbef13a', '10', '1');
INSERT INTO `flower` VALUES ('4702776d-da6d-11e8-bfc0-e4f89cbef13a', '测试', '250', '9f2c07e6-d9ef-11e8-9b2d-e4f89cbef13a', '10', '1');
INSERT INTO `flower` VALUES ('471b937c-da6d-11e8-bfc0-e4f89cbef13a', '测试', '250', '9f2c07e6-d9ef-11e8-9b2d-e4f89cbef13a', '10', '1');
INSERT INTO `flower` VALUES ('47302f25-da6d-11e8-bfc0-e4f89cbef13a', '测试', '250', '9f2c07e6-d9ef-11e8-9b2d-e4f89cbef13a', '10', '1');
INSERT INTO `flower` VALUES ('4746b478-da6d-11e8-bfc0-e4f89cbef13a', '测试', '250', '9f2c07e6-d9ef-11e8-9b2d-e4f89cbef13a', '10', '1');
INSERT INTO `flower` VALUES ('4fe67d4b-da6d-11e8-bfc0-e4f89cbef13a', '1123131', '250', 'addcd165-d9ef-11e8-9b2d-e4f89cbef13a', '10', '2');
INSERT INTO `flower` VALUES ('5010443a-da6d-11e8-bfc0-e4f89cbef13a', '再测试', '250', 'a186d8ec-d9ef-11e8-9b2d-e4f89cbef13a', '10', '2');
INSERT INTO `flower` VALUES ('50237349-da6d-11e8-bfc0-e4f89cbef13a', '再测试', '250', 'a186d8ec-d9ef-11e8-9b2d-e4f89cbef13a', '10', '2');
INSERT INTO `flower` VALUES ('56c34484-da6d-11e8-bfc0-e4f89cbef13a', '曼珠沙华', '250', 'a186d8ec-d9ef-11e8-9b2d-e4f89cbef13a', '10', '1');
INSERT INTO `flower` VALUES ('57305fe5-da6d-11e8-bfc0-e4f89cbef13a', '曼珠沙华', '250', 'a186d8ec-d9ef-11e8-9b2d-e4f89cbef13a', '10', '2');
INSERT INTO `flower` VALUES ('8e072849-4cf4-4d43-b634-3d34dc1a3398', '123', '1231', 'a5c01240-d9ef-11e8-9b2d-e4f89cbef13a', '12321', '1');
INSERT INTO `flower` VALUES ('b759c5f1-bbe8-4e1a-904f-7081b1a6a514', '新增花卉', '1111', 'a32ffaa9-d9ef-11e8-9b2d-e4f89cbef13a', '50', '1');

-- ----------------------------
-- Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `order_id` varchar(36) NOT NULL,
  `order_user_id` varchar(36) NOT NULL,
  `order_price` double NOT NULL DEFAULT '0',
  `order_created_time` varchar(50) NOT NULL COMMENT '创建时间',
  `order_delete` int(2) NOT NULL DEFAULT '1' COMMENT '1正常2删除',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('14d6f307-da83-11e8-bfc0-e4f89cbef13a', '6fbb71f5-d9ee-11e8-9b2d-e4f89cbef13a', '0', '2018年10月28日 12:12:12', '1');
INSERT INTO `orders` VALUES ('68f30154-68e6-498d-9a8c-f2c2967a9671', '6fbb71f5-d9ee-11e8-9b2d-e4f89cbef13a', '0', '2018年10月28日 19:14:19', '1');
INSERT INTO `orders` VALUES ('b290befa-2390-4dc4-af53-93c4e902fd59', '6fbb71f5-d9ee-11e8-9b2d-e4f89cbef13a', '0', '2018年10月28日 19:20:51', '1');
INSERT INTO `orders` VALUES ('c8293daa-da82-11e8-bfc0-e4f89cbef13a', '6fbb71f5-d9ee-11e8-9b2d-e4f89cbef13a', '0', '2018年10月28日 12:12:12', '2');

-- ----------------------------
-- Table structure for `order_flower`
-- ----------------------------
DROP TABLE IF EXISTS `order_flower`;
CREATE TABLE `order_flower` (
  `order_id` varchar(36) NOT NULL,
  `flower_id` varchar(36) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_flower
-- ----------------------------
INSERT INTO `order_flower` VALUES ('14d6f307-da83-11e8-bfc0-e4f89cbef13a', '45ed33b1-da6d-11e8-bfc0-e4f89cbef13a');
INSERT INTO `order_flower` VALUES ('14d6f307-da83-11e8-bfc0-e4f89cbef13a', '56c34484-da6d-11e8-bfc0-e4f89cbef13a');
INSERT INTO `order_flower` VALUES ('c8293daa-da82-11e8-bfc0-e4f89cbef13a', '5010443a-da6d-11e8-bfc0-e4f89cbef13a');
INSERT INTO `order_flower` VALUES ('68f30154-68e6-498d-9a8c-f2c2967a9671', '4fe67d4b-da6d-11e8-bfc0-e4f89cbef13a');
INSERT INTO `order_flower` VALUES ('68f30154-68e6-498d-9a8c-f2c2967a9671', '56c34484-da6d-11e8-bfc0-e4f89cbef13a');
INSERT INTO `order_flower` VALUES ('68f30154-68e6-498d-9a8c-f2c2967a9671', 'b759c5f1-bbe8-4e1a-904f-7081b1a6a514');
INSERT INTO `order_flower` VALUES ('b290befa-2390-4dc4-af53-93c4e902fd59', '4fe67d4b-da6d-11e8-bfc0-e4f89cbef13a');

-- ----------------------------
-- Table structure for `type`
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `type_id` varchar(36) NOT NULL,
  `type_name` varchar(36) NOT NULL,
  `type_num` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES ('50b941c8-4b23-4e7c-8817-3af19513aefe', '修改后的品种', '0');
INSERT INTO `type` VALUES ('9f2c07e6-d9ef-11e8-9b2d-e4f89cbef13a', '郁金香', '110');
INSERT INTO `type` VALUES ('a186d8ec-d9ef-11e8-9b2d-e4f89cbef13a', '康乃馨', '40');
INSERT INTO `type` VALUES ('a32ffaa9-d9ef-11e8-9b2d-e4f89cbef13a', '百合', '50');
INSERT INTO `type` VALUES ('a5c01240-d9ef-11e8-9b2d-e4f89cbef13a', '黑蔷薇', '12321');
INSERT INTO `type` VALUES ('addcd165-d9ef-11e8-9b2d-e4f89cbef13a', '满江红', '10');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` varchar(36) NOT NULL,
  `username` varchar(36) NOT NULL,
  `password` varchar(36) NOT NULL,
  `user_flag` int(2) NOT NULL DEFAULT '1' COMMENT '1为用户。2为管理员',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('6fbb71f5-d9ee-11e8-9b2d-e4f89cbef13a', '123', '123', '1');
INSERT INTO `user` VALUES ('782e6081-d9ee-11e8-9b2d-e4f89cbef13a', 'admin', 'admin', '2');
