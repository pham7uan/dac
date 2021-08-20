drop table if EXISTS master_project;
CREATE TABLE master_project(
   id bigint(20) NOT NULL AUTO_INCREMENT,
   created bigint(20) NULL,
   updated bigint(20) NULL,
   created_by varchar(255) NULL,
   updated_by varchar(255) NULL,
   active tinyint(1) NULL,
   tenant_id bigint(20) NULL,
   name varchar(255) NOT NULL,
   PRIMARY KEY (id)
);

drop table if EXISTS master_contract;
CREATE TABLE master_contract(
    id bigint(20) NOT NULL AUTO_INCREMENT,
    created bigint(20) NULL,
    updated bigint(20) NULL,
    created_by varchar(255) NULL,
    updated_by varchar(255) NULL,
    active tinyint(1) NULL,
    tenant_id bigint(20) NULL,
    name varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE master_devices ADD origin_contract_id bigint(20);
ALTER TABLE master_devices ADD contract_id bigint(20);
ALTER TABLE master_devices ADD project varchar(255);
ALTER TABLE master_devices ADD project_id bigint(20);