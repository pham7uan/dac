CREATE TABLE `master_logs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `table_name` varchar(120) COLLATE utf8_unicode_ci DEFAULT NULL,
  `query_info` mediumtext COLLATE utf8_unicode_ci,
  `type` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `record_id` bigint(20) DEFAULT NULL,
  `ip` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` tinyint(1) DEFAULT '0',
  `created` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=FEDERATED DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci CONNECTION='mysql://root:root@10.15.12.151:3306/vts_collector_center/master_logs';

CREATE TABLE `master_logs_local` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `table_name` varchar(120) COLLATE utf8_unicode_ci DEFAULT NULL,
  `query_info` mediumtext COLLATE utf8_unicode_ci,
  `type` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `record_id` bigint(20) DEFAULT NULL,
  `ip` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` tinyint(1) DEFAULT '0',
  `created` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;