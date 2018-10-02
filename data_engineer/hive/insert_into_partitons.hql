--################################################################################
--#--   Name:             insert_into_partitions.hql                             #
--#--   Description:      Insert data into partitioned table                     #
--#--                                                                            #
--#--   Usage:                                                                   #
--#--   beeline -f /path/to/insert_into_partitions.hql --hivevar in_table=<table_name>#
--#--   --hivevar out_table=<table_name> --hivevar partition_col=<partition_column>#
--#--                                                                            #
--#--   Modification log:                                                        #
--#--     Date         Name (SSO)                  History                       #
--#--   --------      ----------------------    ----------------------           #
--#--                                                                            #
--#--                                                                            #
--################################################################################

-- set dynamic partition support in hive
SET hive.exec.dynamic.partition = true;
SET hive.exec.dynamic.partition.mode = nonstrict;

-- set data compression as SNAPPY
SET parquet.compression=SNAPPY;

-- Copy data to a partitioned snappy-compressed parquet table
FROM ${in_table}
INSERT INTO TABLE ${out_table} PARTITION(partition_col_name)
SELECT *, to_date(${partition_col}) AS partition_col_name
DISTRIBUTE BY partition_col_name
;
