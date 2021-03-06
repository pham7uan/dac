drop table if EXISTS master_area;
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

drop table if EXISTS master_location;
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

drop table if EXISTS master_pricing;
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

drop table if EXISTS master_product;
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

-- import data
-- ----------------------------
-- Records of master_area(id, code, name)
-- ----------------------------
INSERT INTO master_area(id, code, name) VALUES ('1', 'AGG', 'An Giang');
INSERT INTO master_area(id, code, name) VALUES ('2', 'VTU', 'V??ng T??u');
INSERT INTO master_area(id, code, name) VALUES ('3', 'BGG', 'B???c Giang');
INSERT INTO master_area(id, code, name) VALUES ('4', 'BKN', 'B???c C???n');
INSERT INTO master_area(id, code, name) VALUES ('5', 'BNH', 'B???c Ninh');
INSERT INTO master_area(id, code, name) VALUES ('6', 'BDH', 'B??nh ?????nh');
INSERT INTO master_area(id, code, name) VALUES ('7', 'BTE', 'B???n Tre');
INSERT INTO master_area(id, code, name) VALUES ('8', 'BDG', 'B??nh D????ng');
INSERT INTO master_area(id, code, name) VALUES ('9', 'BPC', 'B??nh Ph?????c');
INSERT INTO master_area(id, code, name) VALUES ('10', 'BTN', 'B??nh Thu???n');
INSERT INTO master_area(id, code, name) VALUES ('11', 'BLU', 'B???c Li??u');
INSERT INTO master_area(id, code, name) VALUES ('12', 'CBG', 'Cao B???ng');
INSERT INTO master_area(id, code, name) VALUES ('13', 'CTO', 'C???n Th??');
INSERT INTO master_area(id, code, name) VALUES ('14', 'CMU', 'C?? Mau');
INSERT INTO master_area(id, code, name) VALUES ('15', 'DNG', 'TP. ???? N???ng');
INSERT INTO master_area(id, code, name) VALUES ('16', 'DLC', '?????c L???c');
INSERT INTO master_area(id, code, name) VALUES ('17', 'DNI', '?????ng Nai');
INSERT INTO master_area(id, code, name) VALUES ('18', 'DTP', '?????ng Th??p');
INSERT INTO master_area(id, code, name) VALUES ('19', 'GLI', 'Gia Lai');
INSERT INTO master_area(id, code, name) VALUES ('20', 'HGG', 'H?? Giang');
INSERT INTO master_area(id, code, name) VALUES ('21', 'HNI', 'H?? N???i');
INSERT INTO master_area(id, code, name) VALUES ('22', 'DBN', '??i???n Bi??n');
INSERT INTO master_area(id, code, name) VALUES ('23', 'HTH', 'H?? T??nh');
INSERT INTO master_area(id, code, name) VALUES ('24', 'HYN', 'H??ng Y??n');
INSERT INTO master_area(id, code, name) VALUES ('25', 'HNM', 'H?? Nam');
INSERT INTO master_area(id, code, name) VALUES ('26', 'HPG', 'H???i Ph??ng');
INSERT INTO master_area(id, code, name) VALUES ('27', 'HDG', 'H???i D????ng');
INSERT INTO master_area(id, code, name) VALUES ('28', 'HCM', 'TP H??? Ch?? Minh');
INSERT INTO master_area(id, code, name) VALUES ('29', 'KHA', 'Kh??nh Ho??');
INSERT INTO master_area(id, code, name) VALUES ('30', 'KGG', 'Ki??n Giang');
INSERT INTO master_area(id, code, name) VALUES ('31', 'KTM', 'Kon Tum');
INSERT INTO master_area(id, code, name) VALUES ('32', 'LCU', 'Lai Ch??u');
INSERT INTO master_area(id, code, name) VALUES ('33', 'LSN', 'L???ng S??n');
INSERT INTO master_area(id, code, name) VALUES ('34', 'LCI', 'L??o Cai');
INSERT INTO master_area(id, code, name) VALUES ('35', 'LDG', 'L??m ?????ng');
INSERT INTO master_area(id, code, name) VALUES ('36', 'LAN', 'Long An');
INSERT INTO master_area(id, code, name) VALUES ('37', 'NDH', 'Nam ?????nh');
INSERT INTO master_area(id, code, name) VALUES ('38', 'NAN', 'Ngh??? An');
INSERT INTO master_area(id, code, name) VALUES ('39', 'NBH', 'Ninh B??nh');
INSERT INTO master_area(id, code, name) VALUES ('40', 'NTN', 'Ninh Thu???n');
INSERT INTO master_area(id, code, name) VALUES ('41', 'PYN', 'Ph?? Y??n');
INSERT INTO master_area(id, code, name) VALUES ('42', 'QBH', 'Qu???ng B??nh');
INSERT INTO master_area(id, code, name) VALUES ('43', 'QNM', 'Qu???ng Nam');
INSERT INTO master_area(id, code, name) VALUES ('44', 'QNI', 'Qu???ng Ng??i');
INSERT INTO master_area(id, code, name) VALUES ('45', 'QNH', 'Qu???ng Ninh');
INSERT INTO master_area(id, code, name) VALUES ('46', 'QTI', 'Qu???ng Tr???');
INSERT INTO master_area(id, code, name) VALUES ('47', 'STG', 'S??c Tr??ng');
INSERT INTO master_area(id, code, name) VALUES ('49', 'SLA', 'S??n La');
INSERT INTO master_area(id, code, name) VALUES ('50', 'TNH', 'T??y Ninh');
INSERT INTO master_area(id, code, name) VALUES ('51', 'TBH', 'Th??i B??nh');
INSERT INTO master_area(id, code, name) VALUES ('52', 'THA', 'Thanh Ho??');
INSERT INTO master_area(id, code, name) VALUES ('53', 'HUE', 'Hu???');
INSERT INTO master_area(id, code, name) VALUES ('54', 'TGG', 'Ti???n Giang');
INSERT INTO master_area(id, code, name) VALUES ('55', 'TVH', 'Tr?? Vinh');
INSERT INTO master_area(id, code, name) VALUES ('56', 'TQG', 'Tuy??n Quang');
INSERT INTO master_area(id, code, name) VALUES ('57', 'VLG', 'V??nh Long');
INSERT INTO master_area(id, code, name) VALUES ('58', 'VPC', 'V??nh Ph??c');
INSERT INTO master_area(id, code, name) VALUES ('59', 'PTO', 'Ph?? Th???');
INSERT INTO master_area(id, code, name) VALUES ('60', 'YBI', 'Y??n B??i');
INSERT INTO master_area(id, code, name) VALUES ('61', 'TNN', 'Th??i Nguy??n');
INSERT INTO master_area(id, code, name) VALUES ('64', 'DNO', '?????c N??ng');
INSERT INTO master_area(id, code, name) VALUES ('65', 'HBH', 'H??a B??nh');
INSERT INTO master_area(id, code, name) VALUES ('66', 'HGI', 'H???u Giang');
