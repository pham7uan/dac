
-- base_advanced_filters
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_BASE_ADVANCED_FILTERS AFTER INSERT ON base_advanced_filters FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into base_advanced_filters %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_advanced_filters', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_BASE_ADVANCED_FILTERS AFTER UPDATE ON base_advanced_filters FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update base_advanced_filters set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_advanced_filters', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_BASE_ADVANCED_FILTERS AFTER DELETE ON base_advanced_filters FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from base_advanced_filters %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_advanced_filters', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- base_attachments
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_BASE_ATTACHMENTS AFTER INSERT ON base_attachments FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into base_attachments %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_attachments', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_BASE_ATTACHMENTS AFTER UPDATE ON base_attachments FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update base_attachments set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_attachments', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_BASE_ATTACHMENTS AFTER DELETE ON base_attachments FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from base_attachments %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_attachments', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- base_audit_log
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_BASE_AUDIT_LOG AFTER INSERT ON base_audit_log FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into base_audit_log %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_audit_log', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_BASE_AUDIT_LOG AFTER UPDATE ON base_audit_log FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update base_audit_log set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_audit_log', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_BASE_AUDIT_LOG AFTER DELETE ON base_audit_log FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from base_audit_log %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_audit_log', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- base_contacts
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_BASE_CONTACTS AFTER INSERT ON base_contacts FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into base_contacts %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_contacts', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_BASE_CONTACTS AFTER UPDATE ON base_contacts FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update base_contacts set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_contacts', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_BASE_CONTACTS AFTER DELETE ON base_contacts FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from base_contacts %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_contacts', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- base_default_configs
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_BASE_DEFAULT_CONFIGS AFTER INSERT ON base_default_configs FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into base_default_configs %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_default_configs', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_BASE_DEFAULT_CONFIGS AFTER UPDATE ON base_default_configs FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update base_default_configs set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_default_configs', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_BASE_DEFAULT_CONFIGS AFTER DELETE ON base_default_configs FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from base_default_configs %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_default_configs', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- base_filters
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_BASE_FILTERS AFTER INSERT ON base_filters FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into base_filters %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_filters', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_BASE_FILTERS AFTER UPDATE ON base_filters FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update base_filters set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_filters', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_BASE_FILTERS AFTER DELETE ON base_filters FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from base_filters %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_filters', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- base_notes
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_BASE_NOTES AFTER INSERT ON base_notes FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into base_notes %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_notes', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_BASE_NOTES AFTER UPDATE ON base_notes FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update base_notes set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_notes', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_BASE_NOTES AFTER DELETE ON base_notes FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from base_notes %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_notes', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- base_notifications
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_BASE_NOTIFICATIONS AFTER INSERT ON base_notifications FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into base_notifications %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_notifications', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_BASE_NOTIFICATIONS AFTER UPDATE ON base_notifications FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update base_notifications set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_notifications', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_BASE_NOTIFICATIONS AFTER DELETE ON base_notifications FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from base_notifications %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_notifications', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- base_operations
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_BASE_OPERATIONS AFTER INSERT ON base_operations FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into base_operations %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_operations', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_BASE_OPERATIONS AFTER UPDATE ON base_operations FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update base_operations set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_operations', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_BASE_OPERATIONS AFTER DELETE ON base_operations FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from base_operations %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_operations', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- base_organizations
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_BASE_ORGANIZATIONS AFTER INSERT ON base_organizations FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into base_organizations %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_organizations', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_BASE_ORGANIZATIONS AFTER UPDATE ON base_organizations FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update base_organizations set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_organizations', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_BASE_ORGANIZATIONS AFTER DELETE ON base_organizations FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from base_organizations %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_organizations', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- base_personal_configs
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_BASE_PERSONAL_CONFIGS AFTER INSERT ON base_personal_configs FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into base_personal_configs %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_personal_configs', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_BASE_PERSONAL_CONFIGS AFTER UPDATE ON base_personal_configs FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update base_personal_configs set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_personal_configs', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_BASE_PERSONAL_CONFIGS AFTER DELETE ON base_personal_configs FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from base_personal_configs %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_personal_configs', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- base_privileges
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_BASE_PRIVILEGES AFTER INSERT ON base_privileges FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into base_privileges %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_privileges', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_BASE_PRIVILEGES AFTER UPDATE ON base_privileges FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update base_privileges set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_privileges', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_BASE_PRIVILEGES AFTER DELETE ON base_privileges FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from base_privileges %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_privileges', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- base_processing
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_BASE_PROCESSING AFTER INSERT ON base_processing FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into base_processing %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_processing', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_BASE_PROCESSING AFTER UPDATE ON base_processing FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update base_processing set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_processing', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_BASE_PROCESSING AFTER DELETE ON base_processing FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from base_processing %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_processing', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- base_representations
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_BASE_REPRESENTATIONS AFTER INSERT ON base_representations FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into base_representations %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_representations', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_BASE_REPRESENTATIONS AFTER UPDATE ON base_representations FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update base_representations set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_representations', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_BASE_REPRESENTATIONS AFTER DELETE ON base_representations FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from base_representations %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_representations', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- base_roles
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_BASE_ROLES AFTER INSERT ON base_roles FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into base_roles %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_roles', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_BASE_ROLES AFTER UPDATE ON base_roles FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update base_roles set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_roles', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_BASE_ROLES AFTER DELETE ON base_roles FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from base_roles %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_roles', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- base_sequences
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_BASE_SEQUENCES AFTER INSERT ON base_sequences FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into base_sequences %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_sequences', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_BASE_SEQUENCES AFTER UPDATE ON base_sequences FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update base_sequences set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_sequences', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_BASE_SEQUENCES AFTER DELETE ON base_sequences FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from base_sequences %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_sequences', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- base_users
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_BASE_USERS AFTER INSERT ON base_users FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into base_users %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_users', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_BASE_USERS AFTER UPDATE ON base_users FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update base_users set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_users', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_BASE_USERS AFTER DELETE ON base_users FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from base_users %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_users', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- master_area
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_MASTER_AREA AFTER INSERT ON master_area FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into master_area %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_area', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_MASTER_AREA AFTER UPDATE ON master_area FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update master_area set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_area', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_MASTER_AREA AFTER DELETE ON master_area FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from master_area %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_area', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- master_attributes
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_MASTER_ATTRIBUTES AFTER INSERT ON master_attributes FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into master_attributes %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_attributes', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_MASTER_ATTRIBUTES AFTER UPDATE ON master_attributes FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update master_attributes set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_attributes', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_MASTER_ATTRIBUTES AFTER DELETE ON master_attributes FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from master_attributes %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_attributes', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- master_bricks
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_MASTER_BRICKS AFTER INSERT ON master_bricks FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into master_bricks %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_bricks', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_MASTER_BRICKS AFTER UPDATE ON master_bricks FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update master_bricks set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_bricks', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_MASTER_BRICKS AFTER DELETE ON master_bricks FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from master_bricks %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_bricks', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- master_classes
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_MASTER_CLASSES AFTER INSERT ON master_classes FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into master_classes %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_classes', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_MASTER_CLASSES AFTER UPDATE ON master_classes FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update master_classes set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_classes', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_MASTER_CLASSES AFTER DELETE ON master_classes FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from master_classes %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_classes', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- master_currencies
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_MASTER_CURRENCIES AFTER INSERT ON master_currencies FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into master_currencies %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_currencies', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_MASTER_CURRENCIES AFTER UPDATE ON master_currencies FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update master_currencies set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_currencies', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_MASTER_CURRENCIES AFTER DELETE ON master_currencies FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from master_currencies %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_currencies', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- master_families
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_MASTER_FAMILIES AFTER INSERT ON master_families FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into master_families %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_families', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_MASTER_FAMILIES AFTER UPDATE ON master_families FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update master_families set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_families', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_MASTER_FAMILIES AFTER DELETE ON master_families FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from master_families %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_families', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- master_material_categories
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_MASTER_MATERIAL_CATEGORIES AFTER INSERT ON master_material_categories FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into master_material_categories %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_material_categories', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_MASTER_MATERIAL_CATEGORIES AFTER UPDATE ON master_material_categories FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update master_material_categories set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_material_categories', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_MASTER_MATERIAL_CATEGORIES AFTER DELETE ON master_material_categories FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from master_material_categories %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_material_categories', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- master_materials
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_MASTER_MATERIALS AFTER INSERT ON master_materials FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into master_materials %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_materials', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_MASTER_MATERIALS AFTER UPDATE ON master_materials FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update master_materials set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_materials', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_MASTER_MATERIALS AFTER DELETE ON master_materials FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from master_materials %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_materials', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- master_phases
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_MASTER_PHASES AFTER INSERT ON master_phases FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into master_phases %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_phases', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_MASTER_PHASES AFTER UPDATE ON master_phases FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update master_phases set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_phases', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_MASTER_PHASES AFTER DELETE ON master_phases FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from master_phases %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_phases', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- master_procedure_info
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_MASTER_PROCEDURE_INFO AFTER INSERT ON master_procedure_info FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into master_procedure_info %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_procedure_info', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_MASTER_PROCEDURE_INFO AFTER UPDATE ON master_procedure_info FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update master_procedure_info set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_procedure_info', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_MASTER_PROCEDURE_INFO AFTER DELETE ON master_procedure_info FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from master_procedure_info %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_procedure_info', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- master_procedure_logs
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_MASTER_PROCEDURE_LOGS AFTER INSERT ON master_procedure_logs FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into master_procedure_logs %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_procedure_logs', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_MASTER_PROCEDURE_LOGS AFTER UPDATE ON master_procedure_logs FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update master_procedure_logs set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_procedure_logs', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_MASTER_PROCEDURE_LOGS AFTER DELETE ON master_procedure_logs FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from master_procedure_logs %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_procedure_logs', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- master_procedures
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_MASTER_PROCEDURES AFTER INSERT ON master_procedures FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into master_procedures %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_procedures', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_MASTER_PROCEDURES AFTER UPDATE ON master_procedures FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update master_procedures set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_procedures', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_MASTER_PROCEDURES AFTER DELETE ON master_procedures FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from master_procedures %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_procedures', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- master_produce_method
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_MASTER_PRODUCE_METHOD AFTER INSERT ON master_produce_method FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into master_produce_method %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_produce_method', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_MASTER_PRODUCE_METHOD AFTER UPDATE ON master_produce_method FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update master_produce_method set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_produce_method', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_MASTER_PRODUCE_METHOD AFTER DELETE ON master_produce_method FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from master_produce_method %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_produce_method', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- master_product_attributes
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_MASTER_PRODUCT_ATTRIBUTES AFTER INSERT ON master_product_attributes FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into master_product_attributes %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_product_attributes', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_MASTER_PRODUCT_ATTRIBUTES AFTER UPDATE ON master_product_attributes FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update master_product_attributes set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_product_attributes', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_MASTER_PRODUCT_ATTRIBUTES AFTER DELETE ON master_product_attributes FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from master_product_attributes %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_product_attributes', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- master_products
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_MASTER_PRODUCTS AFTER INSERT ON master_products FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into master_products %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_products', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_MASTER_PRODUCTS AFTER UPDATE ON master_products FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update master_products set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_products', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_MASTER_PRODUCTS AFTER DELETE ON master_products FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from master_products %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_products', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- master_seasons
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_MASTER_SEASONS AFTER INSERT ON master_seasons FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into master_seasons %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_seasons', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_MASTER_SEASONS AFTER UPDATE ON master_seasons FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update master_seasons set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_seasons', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_MASTER_SEASONS AFTER DELETE ON master_seasons FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from master_seasons %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_seasons', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- master_segments
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_MASTER_SEGMENTS AFTER INSERT ON master_segments FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into master_segments %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_segments', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_MASTER_SEGMENTS AFTER UPDATE ON master_segments FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update master_segments set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_segments', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_MASTER_SEGMENTS AFTER DELETE ON master_segments FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from master_segments %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_segments', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- master_uom_types
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_MASTER_UOM_TYPES AFTER INSERT ON master_uom_types FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into master_uom_types %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_uom_types', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_MASTER_UOM_TYPES AFTER UPDATE ON master_uom_types FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update master_uom_types set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_uom_types', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_MASTER_UOM_TYPES AFTER DELETE ON master_uom_types FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from master_uom_types %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_uom_types', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- master_uoms
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_MASTER_UOMS AFTER INSERT ON master_uoms FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into master_uoms %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_uoms', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_MASTER_UOMS AFTER UPDATE ON master_uoms FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update master_uoms set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_uoms', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_MASTER_UOMS AFTER DELETE ON master_uoms FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from master_uoms %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_uoms', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- master_values
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_MASTER_VALUES AFTER INSERT ON master_values FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into master_values %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_values', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_MASTER_VALUES AFTER UPDATE ON master_values FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update master_values set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_values', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_MASTER_VALUES AFTER DELETE ON master_values FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from master_values %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_values', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- vts_ssccs
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_VTS_SSCCS AFTER INSERT ON vts_ssccs FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into vts_ssccs %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('vts_ssccs', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_VTS_SSCCS AFTER UPDATE ON vts_ssccs FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update vts_ssccs set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('vts_ssccs', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_VTS_SSCCS AFTER DELETE ON vts_ssccs FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from vts_ssccs %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('vts_ssccs', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- vts_lots
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_VTS_LOTS AFTER INSERT ON vts_lots FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into vts_lots %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('vts_lots', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_VTS_LOTS AFTER UPDATE ON vts_lots FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update vts_lots set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('vts_lots', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_VTS_LOTS AFTER DELETE ON vts_lots FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from vts_lots %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('vts_lots', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;


-- base_organization_user
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_BASE_ORGANIZATION_USER AFTER INSERT ON base_organization_user FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into base_organization_user %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_organization_user', Q, 'insert', NULL, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_BASE_ORGANIZATION_USER AFTER UPDATE ON base_organization_user FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update base_organization_user set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_organization_user', Q, 'update', NULL, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_BASE_ORGANIZATION_USER AFTER DELETE ON base_organization_user FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from base_organization_user %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_organization_user', Q, 'delete', NULL, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- base_role_privilege
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_BASE_ROLE_PRIVILEGE AFTER INSERT ON base_role_privilege FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into base_role_privilege %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_role_privilege', Q, 'insert', NULL, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_BASE_ROLE_PRIVILEGE AFTER UPDATE ON base_role_privilege FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update base_role_privilege set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_role_privilege', Q, 'update', NULL, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_BASE_ROLE_PRIVILEGE AFTER DELETE ON base_role_privilege FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from base_role_privilege %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_role_privilege', Q, 'delete', NULL, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- base_user_role
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_BASE_USER_ROLE AFTER INSERT ON base_user_role FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into base_user_role %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_user_role', Q, 'insert', NULL, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_BASE_USER_ROLE AFTER UPDATE ON base_user_role FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update base_user_role set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_user_role', Q, 'update', NULL, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_BASE_USER_ROLE AFTER DELETE ON base_user_role FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from base_user_role %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('base_user_role', Q, 'delete', NULL, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- master_area_user
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_MASTER_AREA_USER AFTER INSERT ON master_area_user FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into master_area_user %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_area_user', Q, 'insert', NULL, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_MASTER_AREA_USER AFTER UPDATE ON master_area_user FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update master_area_user set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_area_user', Q, 'update', NULL, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_MASTER_AREA_USER AFTER DELETE ON master_area_user FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from master_area_user %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_area_user', Q, 'delete', NULL, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- master_procedure_transfers
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_MASTER_PROCEDURE_TRANSFERS AFTER INSERT ON master_procedure_transfers FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into master_procedure_transfers %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_procedure_transfers', Q, 'insert', NULL, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_MASTER_PROCEDURE_TRANSFERS AFTER UPDATE ON master_procedure_transfers FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update master_procedure_transfers set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_procedure_transfers', Q, 'update', NULL, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_MASTER_PROCEDURE_TRANSFERS AFTER DELETE ON master_procedure_transfers FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from master_procedure_transfers %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_procedure_transfers', Q, 'delete', NULL, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- master_season_area
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_MASTER_SEASON_AREA AFTER INSERT ON master_season_area FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into master_season_area %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_season_area', Q, 'insert', NULL, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_MASTER_SEASON_AREA AFTER UPDATE ON master_season_area FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update master_season_area set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_season_area', Q, 'update', NULL, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_MASTER_SEASON_AREA AFTER DELETE ON master_season_area FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from master_season_area %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('master_season_area', Q, 'delete', NULL, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;