CREATE TABLE `master_area`  (
   `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `name` varchar(255) NULL,
   `code` varchar(255) NULL,
   `created` bigint(20) NULL,
   `updated` bigint(20) NULL,
   `created_by` varchar(255) NULL,
   `updated_by` varchar(255) NULL,
   `active` tinyint(1) NULL,
   `tenant_id` bigint(20) NULL,
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `master_location`  (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NULL,
    `code` varchar(255) NULL,
    `area_id` bigint(20) NULL,
    `created` bigint(20) NULL,
    `updated` bigint(20) NULL,
    `created_by` varchar(255) NULL,
    `updated_by` varchar(255) NULL,
    `active` tinyint(1) NULL,
    `tenant_id` bigint(20) NULL,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `master_pricing`  (
   `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `cycle` varchar(255) NULL,
   `code` varchar(255) NULL,
   `created` bigint(20) NULL,
   `updated` bigint(20) NULL,
   `created_by` varchar(255) NULL,
   `updated_by` varchar(255) NULL,
   `active` tinyint(1) NULL,
   `tenant_id` bigint(20) NULL,
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `master_product`  (
   `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `internal_reference` varchar(255) NULL,
   `created` bigint(20) NULL,
   `updated` bigint(20) NULL,
   `created_by` varchar(255) NULL,
   `updated_by` varchar(255) NULL,
   `active` tinyint(1) NULL,
   `tenant_id` bigint(20) NULL,
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

---import data
-- ----------------------------
-- Records of master_area(id, code, name)
-- ----------------------------
INSERT INTO master_area(id, code, name) VALUES ('1', 'AGG', 'An Giang');
INSERT INTO master_area(id, code, name) VALUES ('2', 'VTU', 'Vũng Tàu');
INSERT INTO master_area(id, code, name) VALUES ('3', 'BGG', 'Bắc Giang');
INSERT INTO master_area(id, code, name) VALUES ('4', 'BKN', 'Bắc Cạn');
INSERT INTO master_area(id, code, name) VALUES ('5', 'BNH', 'Bắc Ninh');
INSERT INTO master_area(id, code, name) VALUES ('6', 'BDH', 'Bình Định');
INSERT INTO master_area(id, code, name) VALUES ('7', 'BTE', 'Bến Tre');
INSERT INTO master_area(id, code, name) VALUES ('8', 'BDG', 'Bình Dương');
INSERT INTO master_area(id, code, name) VALUES ('9', 'BPC', 'Bình Phước');
INSERT INTO master_area(id, code, name) VALUES ('10', 'BTN', 'Bình Thuận');
INSERT INTO master_area(id, code, name) VALUES ('11', 'BLU', 'Bạc Liêu');
INSERT INTO master_area(id, code, name) VALUES ('12', 'CBG', 'Cao Bằng');
INSERT INTO master_area(id, code, name) VALUES ('13', 'CTO', 'Cần Thơ');
INSERT INTO master_area(id, code, name) VALUES ('14', 'CMU', 'Cà Mau');
INSERT INTO master_area(id, code, name) VALUES ('15', 'DNG', 'TP. Đà Nẵng');
INSERT INTO master_area(id, code, name) VALUES ('16', 'DLC', 'Đắc Lắc');
INSERT INTO master_area(id, code, name) VALUES ('17', 'DNI', 'Đồng Nai');
INSERT INTO master_area(id, code, name) VALUES ('18', 'DTP', 'Đồng Tháp');
INSERT INTO master_area(id, code, name) VALUES ('19', 'GLI', 'Gia Lai');
INSERT INTO master_area(id, code, name) VALUES ('20', 'HGG', 'Hà Giang');
INSERT INTO master_area(id, code, name) VALUES ('21', 'HNI', 'Hà Nội');
INSERT INTO master_area(id, code, name) VALUES ('22', 'DBN', 'Điện Biên');
INSERT INTO master_area(id, code, name) VALUES ('23', 'HTH', 'Hà Tĩnh');
INSERT INTO master_area(id, code, name) VALUES ('24', 'HYN', 'Hưng Yên');
INSERT INTO master_area(id, code, name) VALUES ('25', 'HNM', 'Hà Nam');
INSERT INTO master_area(id, code, name) VALUES ('26', 'HPG', 'Hải Phòng');
INSERT INTO master_area(id, code, name) VALUES ('27', 'HDG', 'Hải Dương');
INSERT INTO master_area(id, code, name) VALUES ('28', 'HCM', 'TP Hồ Chí Minh');
INSERT INTO master_area(id, code, name) VALUES ('29', 'KHA', 'Khánh Hoà');
INSERT INTO master_area(id, code, name) VALUES ('30', 'KGG', 'Kiên Giang');
INSERT INTO master_area(id, code, name) VALUES ('31', 'KTM', 'Kon Tum');
INSERT INTO master_area(id, code, name) VALUES ('32', 'LCU', 'Lai Châu');
INSERT INTO master_area(id, code, name) VALUES ('33', 'LSN', 'Lạng Sơn');
INSERT INTO master_area(id, code, name) VALUES ('34', 'LCI', 'Lào Cai');
INSERT INTO master_area(id, code, name) VALUES ('35', 'LDG', 'Lâm Đồng');
INSERT INTO master_area(id, code, name) VALUES ('36', 'LAN', 'Long An');
INSERT INTO master_area(id, code, name) VALUES ('37', 'NDH', 'Nam Định');
INSERT INTO master_area(id, code, name) VALUES ('38', 'NAN', 'Nghệ An');
INSERT INTO master_area(id, code, name) VALUES ('39', 'NBH', 'Ninh Bình');
INSERT INTO master_area(id, code, name) VALUES ('40', 'NTN', 'Ninh Thuận');
INSERT INTO master_area(id, code, name) VALUES ('41', 'PYN', 'Phú Yên');
INSERT INTO master_area(id, code, name) VALUES ('42', 'QBH', 'Quảng Bình');
INSERT INTO master_area(id, code, name) VALUES ('43', 'QNM', 'Quảng Nam');
INSERT INTO master_area(id, code, name) VALUES ('44', 'QNI', 'Quảng Ngãi');
INSERT INTO master_area(id, code, name) VALUES ('45', 'QNH', 'Quảng Ninh');
INSERT INTO master_area(id, code, name) VALUES ('46', 'QTI', 'Quảng Trị');
INSERT INTO master_area(id, code, name) VALUES ('47', 'STG', 'Sóc Trăng');
INSERT INTO master_area(id, code, name) VALUES ('49', 'SLA', 'Sơn La');
INSERT INTO master_area(id, code, name) VALUES ('50', 'TNH', 'Tây Ninh');
INSERT INTO master_area(id, code, name) VALUES ('51', 'TBH', 'Thái Bình');
INSERT INTO master_area(id, code, name) VALUES ('52', 'THA', 'Thanh Hoá');
INSERT INTO master_area(id, code, name) VALUES ('53', 'HUE', 'Huế');
INSERT INTO master_area(id, code, name) VALUES ('54', 'TGG', 'Tiền Giang');
INSERT INTO master_area(id, code, name) VALUES ('55', 'TVH', 'Trà Vinh');
INSERT INTO master_area(id, code, name) VALUES ('56', 'TQG', 'Tuyên Quang');
INSERT INTO master_area(id, code, name) VALUES ('57', 'VLG', 'Vĩnh Long');
INSERT INTO master_area(id, code, name) VALUES ('58', 'VPC', 'Vĩnh Phúc');
INSERT INTO master_area(id, code, name) VALUES ('59', 'PTO', 'Phú Thọ');
INSERT INTO master_area(id, code, name) VALUES ('60', 'YBI', 'Yên Bái');
INSERT INTO master_area(id, code, name) VALUES ('61', 'TNN', 'Thái Nguyên');
INSERT INTO master_area(id, code, name) VALUES ('64', 'DNO', 'Đắc Nông');
INSERT INTO master_area(id, code, name) VALUES ('65', 'HBH', 'Hòa Bình');
INSERT INTO master_area(id, code, name) VALUES ('66', 'HGI', 'Hậu Giang');
