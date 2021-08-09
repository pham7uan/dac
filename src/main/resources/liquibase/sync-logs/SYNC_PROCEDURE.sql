DROP PROCEDURE IF EXISTS SYNC_MASTER_LOG;

DELIMITER ||
CREATE PROCEDURE SYNC_MASTER_LOG()
BEGIN
DECLARE cursor_List_isdone BOOLEAN DEFAULT FALSE;
DECLARE ID BIGINT;
DECLARE TABLE_NAME VARCHAR(255);
DECLARE Q MEDIUMTEXT CHARACTER SET utf8;
DECLARE TYPE VARCHAR(50);
DECLARE RECORD_ID BIGINT;
DECLARE IP VARCHAR(50);
DECLARE STATUS_RECORD INT;
DECLARE CREATED INT;

-- execute query_info
DECLARE cursor_List CURSOR FOR
SELECT
	T1.id, T1.table_name, T1.query_info, T1.type, T1.record_id, T1.ip, T1.`status`, T1.created
FROM (SELECT * FROM master_logs ORDER BY created ASC) T1
LEFT JOIN master_logs_local T2
ON T1.id = T2.id
WHERE T2.id IS NULL AND T1.query_info <> '' AND T1.query_info IS NOT NULL AND T1.ip <> (SELECT `value` FROM base_configs WHERE name = 'ip') LIMIT 10;

DECLARE CONTINUE HANDLER FOR NOT FOUND SET cursor_List_isdone = TRUE;

SET FOREIGN_KEY_CHECKS=0;
SET @DISABLE_TRIGGERS=1;

OPEN cursor_List;
  loop_List: LOOP
		FETCH cursor_List INTO ID, TABLE_NAME, Q, TYPE, RECORD_ID, IP, STATUS_RECORD, CREATED;
		IF cursor_List_isdone THEN
			LEAVE loop_List;
		END IF;

		IF 'insert' = TYPE AND RECORD_ID IS NOT NULL THEN
			SELECT REPLACE(Q, CONCAT('insert into ', TABLE_NAME, ' ('), CONCAT('insert into ', TABLE_NAME, ' (id,')) INTO Q;
			SELECT REPLACE(Q,' values (',CONCAT(' values (', RECORD_ID, ',')) INTO Q;
		END IF;

		SET @sql_query = Q;
		PREPARE stmt1 FROM @sql_query;
		EXECUTE stmt1;
		DEALLOCATE PREPARE stmt1;

		-- insert data to master_logs_local
		INSERT INTO master_logs_local(id, table_name, query_info,type,record_id,ip,`status`,created)
		VALUES (ID, TABLE_NAME, Q, TYPE, RECORD_ID, IP, STATUS_RECORD, CREATED);

	END LOOP loop_List;
CLOSE cursor_List;

DELETE FROM master_logs WHERE query_info = '' OR query_info IS NULL;

SET @DISABLE_TRIGGERS=NULL;
SET FOREIGN_KEY_CHECKS=1;

END; ||
DELIMITER ;

CALL SYNC_MASTER_LOG();