-- {{TABLE_NAME}}
-- CREATE
DELIMITER ||
CREATE TRIGGER TRIGGER_CREATE_{{TRIGGER_NAME}} AFTER INSERT ON {{TABLE_NAME}} FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'insert into {{TABLE_NAME}} %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('{{TABLE_NAME}}', Q, 'insert', NEW.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- UPDATE
DELIMITER ||
CREATE TRIGGER TRIGGER_UPDATE_{{TRIGGER_NAME}} AFTER UPDATE ON {{TABLE_NAME}} FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'update {{TABLE_NAME}} set %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('{{TABLE_NAME}}', Q, 'update', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;

-- DELETE
DELIMITER ||
CREATE TRIGGER TRIGGER_DELETE_{{TRIGGER_NAME}} AFTER DELETE ON {{TABLE_NAME}} FOR EACH ROW
BEGIN
  DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
	DECLARE IP_ADDRESS varchar(50);
  SELECT argument INTO Q
  FROM mysql.general_log
  where thread_id = connection_id()
	and command_type = 'Query' AND argument LIKE 'delete from {{TABLE_NAME}} %'
  order by event_time desc
  limit 1;

  SELECT `value` FROM base_configs WHERE name = 'ip' INTO IP_ADDRESS;

  INSERT INTO master_logs (`table_name`, `query_info`, `type`, `record_id`, `ip`, `created`)
  VALUES ('{{TABLE_NAME}}', Q, 'delete', OLD.id, IP_ADDRESS, UNIX_TIMESTAMP());

END ||
DELIMITER ;