# Sqoop commands

## Test connection
```
sqoop job -Dhadoop.security.credential.provider.path="jceks://hdfs/path/to/db_credential/password.jceks" \
	-D target.output.dir="<hdfs parent location>" \
	--create test \
	-- import \
	--options-file '/path/to/connection.properties' \
	--fields-terminated-by , --escaped-by \" --optionally-enclosed-by '\"' --null-string '' --null-non-string '' \
	--query "SELECT* FROM <TABLE> WHERE <CONDITIONS> AND \$CONDITIONS" \
	--mapreduce-job-name test \
	--class-name test \
	--direct \
	--target-dir /hdfs/path/to/output \
	--incremental append \
	--append \
	--check-column "<CHECK_COLUMN_NAME>" \
	--split-by "<SPLIT_CONDITIONS>" \
	-m<#_MAPPER> \
	--verbose
```

## Running in a queue
```
--queue <queue_name>
```

## Table schema from RDBMS
```
select * from all_tab_columns where owner = '<OWENR_NAME>' and table_name = '<Table_name>';  --for Oracle
select * from DBC.columns where databasename ='<Database_Name>' and tablename ='<Table_name>';       --for Teradata
SELECT * from INFORMATION_SCHEMA.COLUMNS WHERE table_name = 'tbl_name' AND table_schema = 'db_name'; -- for Mysql .. etc
```
