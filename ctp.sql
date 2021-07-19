/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50022
 Source Host           : localhost:3306
 Source Schema         : ctp

 Target Server Type    : MySQL
 Target Server Version : 50022
 File Encoding         : 65001

 Date: 28/05/2019 00:04:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '商品名称',
  `goods_detail` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '商品详情',
  `goods_image` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '商品图片',
  `fk_goodstype_id` int(10) NULL DEFAULT NULL COMMENT '商品类型',
  `goods_degree` decimal(3, 2) NULL DEFAULT NULL COMMENT '商品的新旧程度：0~1',
  `goods_original_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品原价',
  `goods_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品二手价',
  `goods_quantity` int(5) NULL DEFAULT NULL COMMENT '商品总量',
  `goods_status` int(1) NULL DEFAULT NULL COMMENT '商品状态：1-正在审核  2-已发布 3-已出售完 4-已下架 ',
  `register_time` datetime NULL DEFAULT NULL COMMENT '上架时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `delete_time` datetime NULL DEFAULT NULL COMMENT '删除时间',
  `delete_flag` int(1) NULL DEFAULT NULL COMMENT '删除标记：0-未删除 1-已删除',
  `fk_user_id` bigint(20) NULL DEFAULT NULL COMMENT '关联买家用户id',
  `goods_number` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '商品编号',
  `trading_place` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '交易地点',
  `goods_surplus` int(5) NULL DEFAULT NULL COMMENT '剩余商品数量',
  `sale_quantity` int(5) NOT NULL DEFAULT 0 COMMENT '出售数量',
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_goods
-- ----------------------------
INSERT INTO `t_goods` VALUES (1, 'asd', '1231231', 'GI1553219106190.jpg', 3, 0.90, 21.00, 12.00, 1, 2, '2019-03-22 17:17:28', '2019-03-22 10:04:41', '2019-03-22 17:17:28', '2019-05-14 16:51:31', 0, 3, 'G1553219106190', 'sdsadasd', 1, 0);
INSERT INTO `t_goods` VALUES (11, '风景区', '桂林山水', 'GI1553247662850.jpg', 1, 0.90, 400.00, 323.00, 1, 2, '2019-03-22 17:41:43', '2019-03-22 17:41:02', '2019-03-22 17:41:43', NULL, 0, 3, 'G1553247662850', '十分士大夫', 0, 0);
INSERT INTO `t_goods` VALUES (12, '普通图书', '本图书是去年买的，但是没有用过。', 'GI1555258058901.jpg', 4, 0.70, 54.00, 21.00, 1, 2, '2019-04-02 21:51:12', '2019-04-02 21:48:15', '2019-04-15 00:07:38', '2019-04-01 22:06:39', 0, 2, 'G1554212895709', '图书馆', 1, 0);
INSERT INTO `t_goods` VALUES (13, '除草机', '除草机', 'GI1554213687734.jpg', 5, 0.50, 123.00, 65.00, 1, 2, '2019-04-02 22:02:04', '2019-04-02 22:01:27', '2019-04-02 22:02:04', '2019-04-14 22:05:54', 0, 2, 'G1554213687734', '学校门口', 0, 1);
INSERT INTO `t_goods` VALUES (14, '音响', '音响', 'GI1557912073271.jpg', 5, 0.70, 121.00, 43.00, 1, 1, '2019-04-15 02:20:21', '2019-04-02 22:02:59', '2019-05-15 17:21:13', '2019-04-14 22:05:40', 0, 2, 'G1554213779715', '学校门口2222222', 1, 0);
INSERT INTO `t_goods` VALUES (15, '手机', '手机', 'GI1554213843427.jpg', 5, 0.80, 795.00, 432.00, 1, 2, '2019-04-02 22:13:29', '2019-04-02 22:04:03', '2019-04-02 22:13:29', NULL, 0, 2, 'G1554213843427', '超市门口', 1, 0);
INSERT INTO `t_goods` VALUES (16, '汽车', '汽车', 'GI1554213932261.jpg', 2, 0.70, 10000.00, 5999.00, 1, 2, '2019-04-02 22:13:31', '2019-04-02 22:05:32', '2019-04-02 22:13:31', NULL, 0, 3, 'G1554213932261', '校门口', 1, 0);
INSERT INTO `t_goods` VALUES (17, '二手音响', '音响', 'GI1554213987429.jpg', 5, 0.70, 145.00, 52.00, 1, 2, '2019-04-02 22:13:35', '2019-04-02 22:06:27', '2019-04-02 22:13:35', NULL, 0, 3, 'G1554213987429', '超市门口', 1, 0);
INSERT INTO `t_goods` VALUES (18, '电视机', '电视机', 'GI1554214269380.png', 5, 0.80, 400.00, 210.00, 1, 2, '2019-04-02 22:13:37', '2019-04-02 22:11:09', '2019-04-02 22:13:37', NULL, 0, 4, 'G1554214269380', '宿舍门口', 1, 0);
INSERT INTO `t_goods` VALUES (19, '锤子手机', '手机2', 'GI1554214352403.jpg', 5, 0.40, 2000.00, 210.00, 1, 2, '2019-04-02 22:13:39', '2019-04-02 22:12:32', '2019-04-02 22:13:39', NULL, 0, 4, 'G1554214352403', '宿舍门口', 1, 0);
INSERT INTO `t_goods` VALUES (20, '小型电子音响', '音响', 'GI1554214385120.jpg', 5, 0.40, 2000.00, 210.00, 1, 2, '2019-04-02 22:13:42', '2019-04-02 22:13:05', '2019-04-02 22:13:42', NULL, 0, 4, 'G1554214385120', '宿舍门口', 1, 0);
INSERT INTO `t_goods` VALUES (21, '拖拉机', '拖拉机士大夫', 'GI1554694608186.jpg', 2, 0.80, 3000.00, 400.00, 1, 2, '2019-04-21 00:59:49', '2019-04-08 11:36:48', '2019-04-21 00:59:49', NULL, 0, 2, 'G1554694608186', '校门口', 0, 0);
INSERT INTO `t_goods` VALUES (22, '二手手机', '手机22', 'GI1555258355690.jpg', 5, 0.80, 30000.00, 1231.00, 1, 2, '2019-05-14 16:51:53', '2019-04-15 00:12:35', '2019-05-14 16:51:53', NULL, 0, 2, 'G1555258355690', '寝室某某人', 1, 0);
INSERT INTO `t_goods` VALUES (23, '手提包（女）', '适合女士的包包', 'GI1558077458256.jpg', 3, 0.80, 322.00, 232.00, 2, 2, '2019-05-17 15:18:38', '2019-05-17 15:17:38', '2019-05-17 15:18:38', NULL, 0, 2, 'G1558077458256', '学校大门口', 2, 0);
INSERT INTO `t_goods` VALUES (24, 'suv汽车', 'suv汽车', 'GI1558089817223.jpg', 2, 0.90, 3674544.00, 57464.00, 1, 2, '2019-05-17 18:54:21', '2019-05-17 18:43:37', '2019-05-17 18:54:21', NULL, 0, 14, 'G1558089817223', '学校前门门口', 1, 0);
INSERT INTO `t_goods` VALUES (25, '女士包包（2）', '女士包包', 'GI1558091453580.jpg', 3, 0.80, 42423.00, 12312.00, 1, 2, '2019-05-17 19:11:00', '2019-05-17 19:10:53', '2019-05-17 19:11:00', NULL, 0, 14, 'G1558091453580', '校门口', 1, 0);
INSERT INTO `t_goods` VALUES (26, '电路板1', '电路板', 'GI1558101489808.jpg', 5, 0.80, 64.00, 23.00, 1, 2, '2019-05-17 21:58:28', '2019-05-17 21:58:09', '2019-05-17 21:58:28', NULL, 0, 2, 'G1558101489808', '校门口', 1, 0);
INSERT INTO `t_goods` VALUES (28, '地方', '胜多负少', 'GI1558103234201.jpg', 2, 0.80, 2342.00, 342.00, 1, 2, '2019-05-17 22:27:40', '2019-05-17 22:27:14', '2019-05-17 22:27:40', NULL, 0, 19, 'G1558103234201', '2342', 0, 1);
INSERT INTO `t_goods` VALUES (32, '背包2', '背包2', 'GI1558106341165.jpg', 3, 0.90, 435.00, 123.00, 1, 4, '2019-05-17 23:22:08', '2019-05-17 23:19:01', '2019-05-17 23:22:08', NULL, 0, 23, 'G1558106341165', '校门口', 0, 1);

-- ----------------------------
-- Table structure for t_goodstype
-- ----------------------------
DROP TABLE IF EXISTS `t_goodstype`;
CREATE TABLE `t_goodstype`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '商品类型名称',
  `type_detail` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '商品类型描述',
  `type_number` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '类型编号',
  `type_parent_id` int(20) NULL DEFAULT NULL COMMENT '商品类型父id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `delete_flag` int(1) NULL DEFAULT NULL COMMENT '删除标记：0-未删除  1-已删除',
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_goodstype
-- ----------------------------
INSERT INTO `t_goodstype` VALUES (1, '食品', '小吃零食', 't1213', NULL, '2019-01-13 22:00:54', '2019-01-13 22:10:48', 0);
INSERT INTO `t_goodstype` VALUES (2, '汽车', '汽车', 't123123', NULL, '2019-03-19 09:57:40', NULL, 0);
INSERT INTO `t_goodstype` VALUES (3, '服装', '衣服\n', 'T123123', NULL, '2019-03-19 09:58:04', '2019-05-14 12:56:16', 0);
INSERT INTO `t_goodstype` VALUES (4, '书籍', NULL, 't12313', NULL, '2019-04-02 21:44:11', NULL, 0);
INSERT INTO `t_goodstype` VALUES (5, '电子产品', NULL, 't23424', NULL, '2019-04-02 21:59:38', '2019-04-02 21:59:49', 0);
INSERT INTO `t_goodstype` VALUES (6, '化妆品', '眼线笔、', 'T1557811049165', NULL, '2019-05-14 13:17:29', NULL, 0);

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_number` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '订单编号',
  `goods_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品单价',
  `goods_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '商品名称',
  `order_status` int(2) NULL DEFAULT NULL COMMENT '订单状态：1-已下单，待交易，2-正在交易，3-已取消，4，交易成功',
  `buy_goods_quantity` int(8) NULL DEFAULT NULL COMMENT '商品购买数量',
  `commodity_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品总价',
  `goods_number` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '商品编号',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `delete_time` datetime NULL DEFAULT NULL COMMENT '删除时间',
  `delete_flag` int(2) NULL DEFAULT NULL COMMENT '删除标记',
  `buyers_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '买家名称',
  `vendor_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '卖家名称',
  `telephone_number` varchar(12) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '联系电话',
  `consignee_address` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '交易地点',
  `fk_buyer_id` bigint(20) NULL DEFAULT NULL COMMENT '购买人id',
  `fk_vendor_id` bigint(20) NULL DEFAULT NULL COMMENT '发布人id',
  `goods_image` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '商品图片',
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES (12, 'O1557934884535-G1553247662850', 323.00, '风景区', 3, 1, 323.00, 'G1553247662850', '2019-05-15 23:41:24', NULL, 0, '123456', '小刘', '15111808141', '十分士大夫', 2, 3, 'GI1553247662850.jpg');
INSERT INTO `t_order` VALUES (13, 'O1557939985661-G1553247662850', 323.00, '风景区', 4, 1, 323.00, 'G1553247662850', '2019-05-16 01:06:25', NULL, 0, '123456', '小刘', '15111808141', '十分士大夫', 2, 3, 'GI1553247662850.jpg');
INSERT INTO `t_order` VALUES (14, 'O1558076969787-G1554214385120', 210.00, '小型电子音响', 4, 1, 210.00, 'G1554214385120', '2019-05-17 15:09:29', NULL, 0, '123456', '小张', '15111808141', '宿舍门口', 2, 4, 'GI1554214385120.jpg');
INSERT INTO `t_order` VALUES (15, 'O1558090673533-G1558089817223', 57464.00, 'suv汽车', 1, 1, 57464.00, 'G1558089817223', '2019-05-17 18:57:53', NULL, 0, '123456', '1234567890', '15111808141', '学校前门门口', 2, 14, 'GI1558089817223.jpg');
INSERT INTO `t_order` VALUES (16, 'O1558090693770-G1558089817223', 57464.00, 'suv汽车', 4, 1, 57464.00, 'G1558089817223', '2019-05-17 18:58:13', NULL, 0, '123456', '1234567890', '15111808141', '学校前门门口', 2, 14, 'GI1558089817223.jpg');
INSERT INTO `t_order` VALUES (17, 'O1558090737797-G1554213987429', 52.00, '二手音响', 1, 1, 52.00, 'G1554213987429', '2019-05-17 18:58:57', NULL, 0, '123456', '小刘', '15111808141', '超市门口', 2, 3, 'GI1554213987429.jpg');
INSERT INTO `t_order` VALUES (18, 'O1558091577917-G1558077458256', 232.00, '手提包（女）', 4, 1, 232.00, 'G1558077458256', '2019-05-17 19:12:57', NULL, 0, '1234567890', '123456', '15111808141', '学校大门口', 14, 2, 'GI1558077458256.jpg');
INSERT INTO `t_order` VALUES (19, 'O1558096161406-G1558091453580', 12312.00, '女士包包（2）', 4, 1, 12312.00, 'G1558091453580', '2019-05-17 20:29:21', NULL, 0, '123456', '1234567890', '15111808141', '校门口', 2, 14, 'GI1558091453580.jpg');
INSERT INTO `t_order` VALUES (20, 'O1558100881456-G1554694608186', 400.00, '拖拉机', 1, 1, 400.00, 'G1554694608186', '2019-05-17 21:48:01', NULL, 0, '159001221', '123456', '15111808141', '校门口', 17, 2, 'GI1554694608186.jpg');
INSERT INTO `t_order` VALUES (21, 'O1558101366193-G1554213687734', 65.00, '除草机', 4, 1, 65.00, 'G1554213687734', '2019-05-17 21:56:06', NULL, 0, '12345678901', '123456', '15111808141', '学校门口', 18, 2, 'GI1554213687734.jpg');
INSERT INTO `t_order` VALUES (23, 'O1558103269601-G1558103234201', 342.00, '地方', 4, 1, 342.00, 'G1558103234201', '2019-05-17 22:27:49', NULL, 0, '123456', '12345678902', '15111808141', '2342', 2, 19, 'GI1558103234201.jpg');
INSERT INTO `t_order` VALUES (25, 'O1558106454780-G1558106341165', 123.00, '背包2', 4, 1, 123.00, 'G1558106341165', '2019-05-17 23:20:54', NULL, 0, '123456', '12345678906', '15111808141', '校门口', 2, 23, 'GI1558106341165.jpg');

-- ----------------------------
-- Table structure for t_order_info
-- ----------------------------
DROP TABLE IF EXISTS `t_order_info`;
CREATE TABLE `t_order_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_number` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '商品编号',
  `goods_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '商品名称',
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_receiver_address
-- ----------------------------
DROP TABLE IF EXISTS `t_receiver_address`;
CREATE TABLE `t_receiver_address`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address_info` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '收件详细地址',
  `telephone` varchar(12) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '收件人电话',
  `fk_user_id` bigint(20) NULL DEFAULT NULL COMMENT '外键（关联用户）',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `delete_time` datetime NULL DEFAULT NULL COMMENT '删除时间',
  `delete_flag` int(2) NULL DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_detail` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, '后台管理员', '主要管理后台操作（商品审核、信息发布等）');
INSERT INTO `t_role` VALUES (2, '普通用户', '主要进行商品交易');
INSERT INTO `t_role` VALUES (3, '普通管理员', '各个模块');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `login_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '登陆名',
  `login_pwd` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '登陆密码',
  `real_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '暂无' COMMENT '真实姓名',
  `gender` int(1) NULL DEFAULT NULL COMMENT '性别',
  `head_portrait` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `telephone` varchar(12) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '电话号码',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `delete_flag` int(1) NOT NULL DEFAULT 0 COMMENT '删除标记：0-未删除，1-已删除',
  `fk_role_id` int(11) NULL DEFAULT NULL COMMENT '关联角色id',
  `my_introduction` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '我的介绍',
  `login_time` datetime NULL DEFAULT NULL COMMENT '最近登陆时间',
  `user_status` int(2) NULL DEFAULT 0 COMMENT '用户状态：1-可用，0-不可用',
  `bay_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '支付宝账号',
  `identity` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '身份证号',
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, '12312', '1213811', '123456', '小黑', 1, 'male_default.jpg', '124087930@qq.com', NULL, '123456789099', '2019-01-13 23:03:41', '2019-02-09 16:50:03', 0, 1, '123456', '2019-05-17 23:19:19', 1, NULL, NULL);
INSERT INTO `t_user` VALUES (2, '123456', '123456', '123456', '李四', 1, 'UI1554213423346.jpg', '124087930@qq.com', '重庆工程学院', '15111808141', '2019-02-08 13:40:44', '2019-05-14 17:27:09', 0, 2, '123456', '2019-05-17 23:20:20', 0, 'L15111808130', '510521199706184359');
INSERT INTO `t_user` VALUES (3, '小刘', '123456789', '123456789', '刘公康', 2, 'UI1553363300153.jpg', '124087930@qq.com', '重庆工程学院', '15111808140', '2019-02-08 15:55:45', '2019-03-24 01:57:37', 0, 2, '你的健康就是我的保障', '2019-04-10 11:19:30', 0, 'L15111808130', '510521199706184359');
INSERT INTO `t_user` VALUES (4, '小张', '111111', '123456', '张某某', 2, 'UI1554214171643.jpg', '124087930@qq.com', '重庆工程学院', '15111808141', '2019-02-08 16:16:30', '2019-04-02 22:09:31', 0, 2, '123456', '2019-04-02 22:07:36', 0, 'L15111808130', '510521199706184359');
INSERT INTO `t_user` VALUES (5, '小明', '123133', '123456', '王老五', 2, 'male_default.jpg', '124087930@qq.com', NULL, NULL, '2019-02-09 14:16:03', '2019-02-09 21:06:00', 0, 2, '十分士大夫但是', NULL, 0, NULL, NULL);
INSERT INTO `t_user` VALUES (6, '小蔡', '113121', '123456', '蔡某某', 2, 'male_default.jpg', '1240@qq.com', NULL, NULL, '2019-02-10 00:30:56', '2019-05-14 16:34:06', 0, 2, '故事发生', NULL, 0, NULL, NULL);
INSERT INTO `t_user` VALUES (7, '张三', '12131415', '123456', '张某某', 0, 'male_default.jpg', '1240@qq.com', NULL, NULL, '2019-03-15 14:25:29', '2019-05-14 16:34:06', 0, 2, NULL, NULL, 0, NULL, NULL);
INSERT INTO `t_user` VALUES (8, '李四', '12345678', '123456', '李某某', 0, 'male_default.jpg', '124087930@qq.com', NULL, NULL, '2019-03-23 17:06:40', NULL, 0, 2, NULL, '2019-03-23 19:34:13', 0, NULL, NULL);
INSERT INTO `t_user` VALUES (9, '159001212', '159001212', '123456', '小罗', 1, 'UI1554865971039.jpg', '124087930@qq.com', '重庆工程学院', '15111808141', '2019-04-10 09:48:38', '2019-04-10 11:12:50', 0, 2, '你好', '2019-04-10 10:45:13', 0, 'L15111808130', '510521199706184359');
INSERT INTO `t_user` VALUES (10, '159001211', '159001211', '123456', '暂无', 0, 'female_default.jpg', '124087930@qq.com', NULL, NULL, '2019-04-10 10:19:17', NULL, 0, 2, NULL, '2019-04-10 10:24:18', 0, NULL, NULL);
INSERT INTO `t_user` VALUES (11, '159001213', '159001213', '123456', '暂无', 0, 'female_default.jpg', '124087930@qq.com', NULL, NULL, '2019-04-10 10:20:56', '2019-05-14 16:35:22', 0, 2, NULL, NULL, 0, NULL, NULL);
INSERT INTO `t_user` VALUES (12, '159001214', '159001214', '123456', '暂无', 1, 'male_default.jpg', '124087930@qq.com', NULL, NULL, '2019-04-10 10:29:25', '2019-05-14 16:35:22', 0, 2, NULL, '2019-04-10 10:29:26', 0, NULL, NULL);
INSERT INTO `t_user` VALUES (13, '159001201', '159001201', '1234567', '小钟', 1, 'UI1557940884297.jpg', '124087930@qq.com', '重庆工程学院', '15111808141', '2019-05-16 01:14:45', '2019-05-16 01:21:24', 0, 2, '你好', '2019-05-17 21:32:33', 0, 'L15111808130', '510521199706184359');
INSERT INTO `t_user` VALUES (14, '1234567890', '1234567890', '123456', '李四', 0, 'UI1558089183366.jpg', '124087930@qq.com', '重庆工程学院', '15111808141', '2019-05-17 18:15:14', '2019-05-17 18:33:40', 0, 2, '你好', '2019-05-17 19:30:06', 0, 'L15111808130', '510521199706184359');
INSERT INTO `t_user` VALUES (15, '159001219', '159001219', '123456', NULL, 1, 'male_default.jpg', '124087930@qq.com', NULL, NULL, '2019-05-17 20:47:57', NULL, 0, 2, NULL, '2019-05-17 21:35:17', 0, NULL, NULL);
INSERT INTO `t_user` VALUES (16, '159001220', '159001220', '123456', '李四', 1, 'UI1558099632335.jpg', '124087930@qq.com', '重庆工程学院', '15111808141', '2019-05-17 21:26:26', '2019-05-17 21:27:12', 0, 2, '你好', '2019-05-17 21:26:29', 0, 'L15111808130', '510521199706184359');
INSERT INTO `t_user` VALUES (17, '159001221', '159001221', '1234567', '李四', 1, 'UI1558100824104.jpg', '124087930@qq.com', '重庆工程学院', '15111808141', '2019-05-17 21:45:57', '2019-05-17 21:47:04', 0, 2, 'nihao', '2019-05-17 21:45:59', 0, 'L15111808130', '510521199706184359');
INSERT INTO `t_user` VALUES (18, '12345678901', '12345678901', '1234567', '李四', 0, 'female_default.jpg', '124087930@qq.com', '重庆工程学院', '15111808141', '2019-05-17 21:54:31', '2019-05-17 21:55:36', 0, 2, 'nihao', '2019-05-17 21:54:34', 0, 'L15111808130', '510521199706184359');
INSERT INTO `t_user` VALUES (19, '12345678902', '12345678902', '123456789', '李四', 0, 'UI1558102817819.jpg', '124087930@qq.com', '重庆工程学院', '15111808141', '2019-05-17 22:19:07', '2019-05-17 22:20:17', 0, 2, 'nihao', '2019-05-17 22:19:09', 0, 'L15111808130', '510521199706184359');
INSERT INTO `t_user` VALUES (20, '12345678903', '12345678903', '123456789', '李四', 0, 'female_default.jpg', '124087930@qq.com', '重庆工程学院', '15111808141', '2019-05-17 22:40:40', '2019-05-17 22:41:51', 0, 2, 'nihao', '2019-05-17 22:40:42', 0, 'L15111808130', '510521199706184359');
INSERT INTO `t_user` VALUES (21, '12345678904', '12345678904', '123456789', '李四', 0, 'female_default.jpg', '124087930@qq.com', '重庆工程学院', '15111808141', '2019-05-17 23:01:41', '2019-05-17 23:02:44', 0, 2, '你好', '2019-05-17 23:01:45', 0, 'L15111808130', '510521199706184359');
INSERT INTO `t_user` VALUES (22, '12345678905', '12345678905', '123456789', '李四', 0, 'female_default.jpg', '124087930@qq.com', '重庆工程学院', '15111808141', '2019-05-17 23:09:39', '2019-05-17 23:10:42', 0, 2, '你好', '2019-05-17 23:09:43', 0, 'L15111808130', '510521199706184359');
INSERT INTO `t_user` VALUES (23, '12345678906', '12345678906', '123456789', '李四', 0, 'female_default.jpg', '124087930@qq.com', '重庆工程学院', '15111808141', '2019-05-17 23:17:18', '2019-05-17 23:18:23', 0, 2, '你好', '2019-05-17 23:17:21', 0, 'L15111808130', '510521199706184359');

-- ----------------------------
-- Table structure for t_user_cart
-- ----------------------------
DROP TABLE IF EXISTS `t_user_cart`;
CREATE TABLE `t_user_cart`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `fk_goods_id` bigint(11) NULL DEFAULT NULL COMMENT '外键（关联商品id）',
  `fk_user_id` bigint(11) NULL DEFAULT NULL COMMENT '外键(关联用户id)',
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
