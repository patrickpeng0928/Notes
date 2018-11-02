# Using SQL in the Sqoop job and inherrit Data Type from DWH in Hive

## Get table schema from DWH
* Data Type conversion from Oracle to Hive



| Data Type in Oracle | Data Type in Hive |
 --- :|: --- 
| VARCHAR2 | string|


```sql
-- get column information from table and views in database
SELECT 
    owner
  , table_name
  , column_name
  , data_type
  , data_length
  , data_precision
  , data_scale
  , CASE data_type
      WHEN 'VARCHAR2' THEN 'string'
      WHEN 'DATE'     THEN 'date'
      WHEN 'NUMBER'   THEN 
        CASE
          WHEN data_scale <> 0 THEN 'decimal(' || data_precision || ',' || data_scale || ')'
          ELSE 
            CASE
              WHEN data_precision > 32 THEN 'decimal(' || data_precision || ',' || data_scale || ')'
              WHEN data_precision <= 8 THEN 'int'
              ELSE 'bigint'
            END
        END       
      ELSE data_type 
    END "HIVE_DATA_TYPE"
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
