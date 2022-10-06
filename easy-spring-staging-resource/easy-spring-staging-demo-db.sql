/*
 Navicat Premium Data Transfer

 Source Server         : 42.192.142.114
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : 42.192.142.114:8089
 Source Schema         : easy-spring-staging-demo-db

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 25/09/2022 15:03:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for demo_account_info
-- ----------------------------
DROP TABLE IF EXISTS `demo_account_info`;
CREATE TABLE `demo_account_info`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `account_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账号名称',
  `gender` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别(1:男;0:女)',
  `create_time` date NULL DEFAULT NULL COMMENT '创建信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '账号' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of demo_account_info
-- ----------------------------
INSERT INTO `demo_account_info` VALUES ('1', 'zhangsan', '1', '2020-01-01');
INSERT INTO `demo_account_info` VALUES ('2', 'lili', '0', '2020-04-01');
INSERT INTO `demo_account_info` VALUES ('3', 'lisi', '1', '2022-09-25');

-- ----------------------------
-- Table structure for demo_employee_info
-- ----------------------------
DROP TABLE IF EXISTS `demo_employee_info`;
CREATE TABLE `demo_employee_info`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '员工ID',
  `account_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账号ID',
  `employee_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '员工姓名',
  `entry_time` date NULL DEFAULT NULL COMMENT '入职日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '员工' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of demo_employee_info
-- ----------------------------
INSERT INTO `demo_employee_info` VALUES ('1', '1', '张三', '2020-01-01');
INSERT INTO `demo_employee_info` VALUES ('2', '2', '李丽', '2020-04-01');
INSERT INTO `demo_employee_info` VALUES ('3', '3', '李四', '2022-09-25');

-- ----------------------------
-- Table structure for demo_salary_info
-- ----------------------------
DROP TABLE IF EXISTS `demo_salary_info`;
CREATE TABLE `demo_salary_info`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '薪水ID',
  `account_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账号ID',
  `year_number` int(11) NULL DEFAULT NULL COMMENT '年份',
  `month_number` int(11) NULL DEFAULT NULL COMMENT '月份',
  `salary_amount` float(255, 0) NULL DEFAULT NULL COMMENT '薪水（单位：元）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '薪水' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of demo_salary_info
-- ----------------------------
INSERT INTO `demo_salary_info` VALUES ('1', '1', 2022, 1, 10000);
INSERT INTO `demo_salary_info` VALUES ('2', '1', 2022, 2, 10000);
INSERT INTO `demo_salary_info` VALUES ('3', '1', 2022, 3, 10000);

SET FOREIGN_KEY_CHECKS = 1;
