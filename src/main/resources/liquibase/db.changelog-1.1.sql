/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50731
Source Host           : localhost:3306
Source Database       : dac

Target Server Type    : MYSQL
Target Server Version : 50731
File Encoding         : 65001

Date: 2021-08-06 15:45:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base_attachments
-- ----------------------------
DROP TABLE IF EXISTS `base_attachments`;
CREATE TABLE `base_attachments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` tinyint(4) DEFAULT NULL,
  `created` bigint(20) DEFAULT NULL,
  `updated` bigint(20) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `url` text,
  `parent_id` bigint(20) DEFAULT NULL,
  `type` int(2) DEFAULT NULL,
  `tenant_id` bigint(20) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of base_attachments
-- ----------------------------

-- ----------------------------
-- Table structure for base_privileges
-- ----------------------------
DROP TABLE IF EXISTS `base_privileges`;
CREATE TABLE `base_privileges` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created` bigint(20) DEFAULT NULL,
  `updated` bigint(20) DEFAULT NULL,
  `active` tinyint(4) DEFAULT '1',
  `display_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `category_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tenant_id` bigint(20) DEFAULT '0',
  `special` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=191 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of base_privileges
-- ----------------------------
INSERT INTO `base_privileges` VALUES ('183', 'user_view', null, null, null, null, null, '1', null, 'priv_Quản trị_Người dùng_Xem người dùng', '0', '0');
INSERT INTO `base_privileges` VALUES ('184', 'user_create', null, null, null, null, null, '1', null, 'priv_Quản trị_Người dùng_Tạo người dùng', '0', '0');
INSERT INTO `base_privileges` VALUES ('185', 'user_delete', null, null, null, null, null, '1', null, 'priv_Quản trị_Người dùng_Xóa người dùng', '0', '0');
INSERT INTO `base_privileges` VALUES ('186', 'user_edit', null, null, null, null, null, '1', null, 'priv_Quản trị_Người dùng_Sửa người dùng', '0', '0');
INSERT INTO `base_privileges` VALUES ('187', 'role_view', null, null, null, null, null, '1', null, 'priv_Quản trị_Vai trò_Xem vai trò', '0', '0');
INSERT INTO `base_privileges` VALUES ('188', 'role_create', null, null, null, null, null, '1', null, 'priv_Quản trị_Vai trò_Tạo vai trò', '0', '0');
INSERT INTO `base_privileges` VALUES ('189', 'role_delete', null, null, null, null, null, '1', null, 'priv_Quản trị_Vai trò_Xóa vai trò', '0', '0');
INSERT INTO `base_privileges` VALUES ('190', 'role_edit', null, null, null, null, null, '1', null, 'priv_Quản trị_Vai trò_Sửa vai trò', '0', '0');

-- ----------------------------
-- Table structure for base_roles
-- ----------------------------
DROP TABLE IF EXISTS `base_roles`;
CREATE TABLE `base_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created` bigint(20) DEFAULT NULL,
  `updated` bigint(20) DEFAULT NULL,
  `active` tinyint(4) DEFAULT '1',
  `type` tinyint(1) DEFAULT NULL,
  `organization_id` bigint(20) DEFAULT '1',
  `tenant_id` bigint(20) DEFAULT NULL,
  `display_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of base_roles
-- ----------------------------
INSERT INTO `base_roles` VALUES ('1', 'ROLE_SYSTEM_ADMIN', 'Quyền quản trị hệ thống', null, null, null, null, '1', '1', '1', '1', 'Quyền quản trị hệ thống');
INSERT INTO `base_roles` VALUES ('2', 'ROLE_ORGANIZATION', 'Quyền chủ doanh nghiệp', null, null, null, null, '1', '1', '1', '1', 'Quyền chủ doanh nghiệp');

-- ----------------------------
-- Table structure for base_role_privilege
-- ----------------------------
DROP TABLE IF EXISTS `base_role_privilege`;
CREATE TABLE `base_role_privilege` (
  `role_id` bigint(20) DEFAULT NULL,
  `privilege_id` bigint(20) DEFAULT NULL,
  KEY `fk_rp_privilege_id` (`privilege_id`) USING BTREE,
  KEY `fk_rp_role_id` (`role_id`) USING BTREE,
  CONSTRAINT `base_role_privilege_ibfk_1` FOREIGN KEY (`privilege_id`) REFERENCES `base_privileges` (`id`),
  CONSTRAINT `base_role_privilege_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `base_roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of base_role_privilege
-- ----------------------------

-- ----------------------------
-- Table structure for base_users
-- ----------------------------
DROP TABLE IF EXISTS `base_users`;
CREATE TABLE `base_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created` bigint(20) DEFAULT NULL,
  `updated` bigint(20) DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL,
  `activated` bigint(20) DEFAULT NULL,
  `activation_token` mediumtext COLLATE utf8_unicode_ci,
  `activation_token_created` bigint(20) DEFAULT NULL,
  `forgot_password_token` mediumtext COLLATE utf8_unicode_ci,
  `forgot_password_token_created` bigint(20) DEFAULT NULL,
  `first_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lang_key` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `department` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_avatar` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `full_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `jwt_token` text COLLATE utf8_unicode_ci,
  `tenant_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `email_UNIQUE` (`email`) USING BTREE,
  KEY `tenant_index` (`tenant_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of base_users
-- ----------------------------
INSERT INTO `base_users` VALUES ('1', 'admin', '$2a$10$p/A/BPCIXciQQv3nD76X..XfBrqVeXCB.49r.CZvK1vXw2d2TvMJe', null, null, null, null, '1', null, null, null, null, null, 'admin', '', null, null, null, null, null, 'admin', null, '1');

-- ----------------------------
-- Table structure for base_user_role
-- ----------------------------
DROP TABLE IF EXISTS `base_user_role`;
CREATE TABLE `base_user_role` (
  `user_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  KEY `fk_ur_user_id` (`user_id`) USING BTREE,
  KEY `fk_ur_role_id` (`role_id`) USING BTREE,
  CONSTRAINT `base_user_role_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `base_roles` (`id`),
  CONSTRAINT `base_user_role_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `base_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of base_user_role
-- ----------------------------
INSERT INTO `base_user_role` VALUES ('1', '1');

-- ----------------------------
-- Table structure for databasechangelog
-- ----------------------------
DROP TABLE IF EXISTS `databasechangelog`;
CREATE TABLE `databasechangelog` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of databasechangelog
-- ----------------------------

-- ----------------------------
-- Table structure for databasechangeloglock
-- ----------------------------
DROP TABLE IF EXISTS `databasechangeloglock`;
CREATE TABLE `databasechangeloglock` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of databasechangeloglock
-- ----------------------------

-- ----------------------------
-- Table structure for jv_commit
-- ----------------------------
DROP TABLE IF EXISTS `jv_commit`;
CREATE TABLE `jv_commit` (
  `commit_pk` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(200) DEFAULT NULL,
  `commit_date` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `commit_id` decimal(22,2) DEFAULT NULL,
  PRIMARY KEY (`commit_pk`),
  KEY `jv_commit_commit_id_idx` (`commit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jv_commit
-- ----------------------------

-- ----------------------------
-- Table structure for jv_commit_property
-- ----------------------------
DROP TABLE IF EXISTS `jv_commit_property`;
CREATE TABLE `jv_commit_property` (
  `property_name` varchar(191) NOT NULL,
  `property_value` varchar(600) DEFAULT NULL,
  `commit_fk` bigint(20) NOT NULL,
  PRIMARY KEY (`commit_fk`,`property_name`),
  KEY `jv_commit_property_commit_fk_idx` (`commit_fk`),
  KEY `jv_commit_property_property_name_property_value_idx` (`property_name`,`property_value`(191)),
  CONSTRAINT `jv_commit_property_commit_fk` FOREIGN KEY (`commit_fk`) REFERENCES `jv_commit` (`commit_pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jv_commit_property
-- ----------------------------

-- ----------------------------
-- Table structure for jv_global_id
-- ----------------------------
DROP TABLE IF EXISTS `jv_global_id`;
CREATE TABLE `jv_global_id` (
  `global_id_pk` bigint(20) NOT NULL AUTO_INCREMENT,
  `local_id` varchar(191) DEFAULT NULL,
  `fragment` varchar(200) DEFAULT NULL,
  `type_name` varchar(200) DEFAULT NULL,
  `owner_id_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`global_id_pk`),
  KEY `jv_global_id_owner_id_fk` (`owner_id_fk`),
  KEY `jv_global_id_local_id_idx` (`local_id`),
  CONSTRAINT `jv_global_id_owner_id_fk` FOREIGN KEY (`owner_id_fk`) REFERENCES `jv_global_id` (`global_id_pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jv_global_id
-- ----------------------------

-- ----------------------------
-- Table structure for jv_snapshot
-- ----------------------------
DROP TABLE IF EXISTS `jv_snapshot`;
CREATE TABLE `jv_snapshot` (
  `snapshot_pk` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(200) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `state` text,
  `changed_properties` text,
  `managed_type` varchar(200) DEFAULT NULL,
  `global_id_fk` bigint(20) DEFAULT NULL,
  `commit_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`snapshot_pk`),
  KEY `jv_snapshot_global_id_fk_idx` (`global_id_fk`),
  KEY `jv_snapshot_commit_fk_idx` (`commit_fk`),
  CONSTRAINT `jv_snapshot_commit_fk` FOREIGN KEY (`commit_fk`) REFERENCES `jv_commit` (`commit_pk`),
  CONSTRAINT `jv_snapshot_global_id_fk` FOREIGN KEY (`global_id_fk`) REFERENCES `jv_global_id` (`global_id_pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jv_snapshot
-- ----------------------------

CREATE TABLE `master_devices` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `serial` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `imei` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
	`mac` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
	`product_name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
	`fw` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
`state` tinyint(4) DEFAULT '0',
	 area_id int(5) DEFAULT NULL,
	`area_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,

`export_date` bigint(20) DEFAULT NULL,
`delivery_date` bigint(20) DEFAULT NULL,
`active_date` bigint(20) DEFAULT NULL,
`expired_date` bigint(20) DEFAULT NULL,
`guarantee_export_date` bigint(20) DEFAULT NULL,
`guarantee_import_date` bigint(20) DEFAULT NULL,
`recall_date` bigint(20) DEFAULT NULL,
`export_code` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
`customer_code` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
`pricing_code` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
`pricing_cycle` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
`pricing_begin_date` bigint(20) DEFAULT NULL,
`pricing_end_date` bigint(20) DEFAULT NULL,
`pricing_pause_date` bigint(20) DEFAULT NULL,
`pricing_change_date` bigint(20) DEFAULT NULL,
`subscription_status` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
`origin_contract` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
`origin_po` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
`contract` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
`po` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
`origin_agency` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
`agency` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
`location_code` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
`location_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
`description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
`accounting_code` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
`inventory_transfer_number` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
`customer_type` tinyint(4) COLLATE utf8_unicode_ci DEFAULT 1,

  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created` bigint(20) DEFAULT NULL,
  `updated` bigint(20) DEFAULT NULL,
  `active` tinyint(4) DEFAULT '1',
  `tenant_id` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;