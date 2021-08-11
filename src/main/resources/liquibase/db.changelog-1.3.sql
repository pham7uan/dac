CREATE TABLE `report_device_summary`  (
          `id` bigint(20) NOT NULL AUTO_INCREMENT,
          `area_id` bigint(20) NOT NULL,
          `area_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
          `contract` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
          `total_device` int(11) NULL DEFAULT NULL,
          `total_pricing` int(11) NULL DEFAULT NULL,
          `total_none_pricing` int(11) NULL DEFAULT NULL,
          PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;