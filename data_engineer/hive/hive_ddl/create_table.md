# Hive Table DDL
## Create an external table
```hive
CREATE EXTERNAL TABLE IF NOT EXISTS <tbl_name> (
col_name col_type,
)
ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde'
WITH SERDEPROPERTIES (
  "separatorChar" = ",",
  "quoteChar"     = "\"",
  "escapeChar"    = "\"",
  'serialization.null.format'=''
)
STORED AS TEXTFILE
LOCATION 'hdfs://...';
```

## Create an partitioned parquet table
```hive
CREATE EXTERNAL TABLE IF NOT EXISTS <tbl_name> (
col_name col_type,
)
PARTITIONED BY (
  partition_col col_type
)
ROW FORMAT SERDE
    'org.apache.hadoop.hive.ql.io.parquet.serde.ParquetHiveSerDe'
STORED AS INPUTFORMAT
    'org.apache.hadoop.hive.ql.io.parquet.MapredParquetInputFormat'
OUTPUTFORMAT
    'org.apache.hadoop.hive.ql.io.parquet.MapredParquetOutputFormat'
LOCATION
'hdfs://...'
TBLPROPERTIES (
    'numFiles'='2',
    'numRows'='-1',
    'parquet.compression'='SNAPPY',
    'rawDataSize'='-1'
)
;
```
