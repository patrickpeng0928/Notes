# Hive Operations

## Use schema
```hive
USE <db_name>;
```

## Drop table
```hive
DROP TABLE IF EXISTS <tbl_name>;
```

## Drop view
```hive
DROP VIEW IF EXISTS <view_name>;
```

## Repair table
```hive
MSCK REPAIR TABLE ${table};
```

## Set variable
```hive
SET VARIABLE_NAME=vale;
```

## Dynamic partition
```hive
SET hive.exec.dynamic.partition = true;
SET hive.exec.dynamic.partition.mode = nonstrict;
```

## Compression
```hive
set parquet.compression=SNAPPY;
```
