# Using SQL in the Sqoop job and inherrit Data Type from DWH in Hive
## Everything
```sql
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
  UPPER(table_name) LIKE '%<TBL_NAME_PATTERN>%'
OR UPPER(table_name) LIKE '%<TBL_NAME_PATTERN>%'
;
```

## Get table schema from DWH
* Data Type conversion from Oracle to Hive

| Data Type in Oracle | Data Type in Hive |
| -------------------:|:----------------- |
| VARCHAR2            | string            |
| DATE                | date              |
| NUMBER(\_, <>0      | decimal(\_, <>0)  |
| NUMBER(>32, 0)      | decimal(>32, 0)   |
| NUMBER(<=8, 0)      | int               |
| NUMBER(8<&<=32, 0)  | bigint            |

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
  ,  ', ' 
  || column_name "SQOOP_SQL"  
  ,  ', '
  || LOWER(column_name)
  || ' string' "INCRE_TABLE"
  ,  ', case when trim('
  || column_name
  || ')="" then null when ascii('
  || column_name
  || ')=0 then null else '
  || (CASE data_type
        WHEN 'DATE' THEN 'to_date('||column_name||')'
        WHEN 'VARCHAR2' THEN column_name
        ELSE CASE 
                WHEN data_precision > 32 OR data_scale <> 0 THEN 'cast '||column_name||' as decimal('||data_precision||', '||data_scale||')'
                WHEN data_precision <= 8 AND data_scale = 0 THEN 'cast '||column_name||'as int)'
                WHEN data_precision > 8 AND data_scale =0 THEN 'cast '||column_name||' as bigint)'
             END
      END)
  || ' end as '
  || LOWER(column_name) "INCRE_VIEW"
  ,  ', '
  || LOWER(column_name)
  || ' '
  || CASE data_type
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
    END "HISTORY_PARTITIONED_TBL"
FROM all_tab_columns
WHERE 
  UPPER(table_name) LIKE '%<TBL_NAME_PATTERN>%'
OR UPPER(table_name) LIKE '%<TBL_NAME_PATTERN>%'
;
```

## Sql for sqoop job
* format: , col_name
```sql
SELECT
     ', ' 
  || column_name "SQOOP_SQL"
FROM all_tab_columns
WHERE
  UPPER(table_name) = '<TBL_NAME>'
;
    
```

## Sql for Sqoop output data (All String format)
* format: , col_name *string*
```sql
SELECT
     ', '
  || LOWER(column_name)
  || ' string' "INCRE_TABLE"
FROM all_tab_columns
WHERE
  UPPER(table_name) = '<TBL_NAME>'
;
```

## Sql for data conversion from String to corresponding data type inherrited from DWH table
* format: , case when trim(col_name) = 0 then null when ascii(col_name) = 0 then null else date_type_convert(col_name) end as col_name
* data clean

| Content in string | condition expr | value |
| ----------------- | -------------- | ----- | 
| ""                | col = ""       | null  |
| "\s+"             | trim(col) = "" | null  |
| "\0"              | ascii(col) = 0 | null  |

```sql
SELECT 
     ', case when trim('
  || column_name
  || ')="" then null when ascii('
  || column_name
  || ')=0 then null else '
  || (CASE data_type
        WHEN 'DATE' THEN 'to_date('||column_name||')'
        WHEN 'VARCHAR2' THEN column_name
        ELSE CASE 
                WHEN data_precision > 32 OR data_scale <> 0 THEN 'cast '||column_name||' as decimal('||data_precision||', '||data_scale||')'
                WHEN data_precision <= 8 AND data_scale = 0 THEN 'cast '||column_name||'as int)'
                WHEN data_precision > 8 AND data_scale =0 THEN 'cast '||column_name||' as bigint)'
             END
      END)
  || ' end as '
  || LOWER(column_name) "INCRE_VIEW"
FROM all_tab_columns
WHERE
  UPPER(table_name) = '<TBL_NAME>'
;
```

## Sql for table or view after data conversion
* format: , col_name date_type
```sql
SELECT 
     ', '
  || LOWER(column_name)
  || ' '
  || CASE data_type
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
    END "HISTORY_PARTITIONED_TBL"
FROM all_tab_columns
WHERE 
  UPPER(table_name) = '<TBL_NAME>'
;
```
