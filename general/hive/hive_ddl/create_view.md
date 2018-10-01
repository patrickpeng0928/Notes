# Hive views
## View on existing table
```hive
CREATE VIEW IF NOT EXISTS <view_name> AS SELECT
CASE WHEN TRIM(col_name)='' THEN null WHEN ASCII(col_name)=0 THEN null ELSE CAST(col_name AS col_type) END AS col_name,
CASE WHEN TRIM(col_name)='' THEN null WHEN ASCII(col_name)=0 THEN null ELSE CAST(TO_DATE(col_name) AS DATE) END AS col_name,
old_col_name AS new_col_name
FROM <tbl_name>;
```
