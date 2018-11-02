# Using SQL in the Sqoop job and inherrit Data Type from DWH in Hive

## Get table schema from DWH
```sql
-- get column information from table and views in database
SELECT 
  owner, table_name, column_name, data_type, data_length, data_precision, data_scale
FROM all_tab_columns
WHERE 
  UPPER(table_name) LIKE '%<tbl_name_pattern>%'
OR UPPER(table_name) LIKE '%<tbl_name_pattern>%'
;
```

## Sql for sqoop job

## Sql for Sqoop output data (All String format)

## Sql for data conversion from String to corresponding data type inherrited from DWH table

## Sql for table or view after data conversion
