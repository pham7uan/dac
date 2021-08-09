/*
Navicat MySQL Data Transfer

Source Server         : 10.15.12.154
Source Server Version : 50727
Source Host           : 10.15.12.154:3306
Source Database       : vts_collector

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2019-11-13 11:10:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base_configs
-- ----------------------------
DROP TABLE IF EXISTS `base_configs`;
CREATE TABLE `base_configs` (
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_configs
-- ----------------------------
INSERT INTO `base_configs` VALUES ('ip', '10.15.12.154');
INSERT INTO `base_configs` VALUES ('auto_increment_increment', '2'); -- số này tương ứng với số server triển khai (bằng số node + 1 con center)
INSERT INTO `base_configs` VALUES ('auto_increment_offset', '1'); -- đánh số tăng dần: center là 1, node 1 là 2, node 2 là 3 ...
