# Sqoop commands

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
