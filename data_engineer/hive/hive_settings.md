# Hive Execution Settings

## Dynamic partition
```hive
set hive.exec.dynamic.partition = true;
set hive.exec.dynamic.partition.mode = nonstrict;
set hive.exec.max.dynamic.partitions.pernode = 400;
```

## Compression
```hive
set parquet.compression=SNAPPY;
```
