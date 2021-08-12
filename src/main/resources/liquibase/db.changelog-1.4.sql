drop table if EXISTS master_customer;
CREATE TABLE `master_customer`  (
   `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `code` varchar(255) NULL,
   `created` bigint(20) NULL,
   `updated` bigint(20) NULL,
   `created_by` varchar(255) NULL,
   `updated_by` varchar(255) NULL,
   `active` tinyint(1) NULL,
   `tenant_id` bigint(20) NULL,
   PRIMARY KEY (`id`),
   UNIQUE(code)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;