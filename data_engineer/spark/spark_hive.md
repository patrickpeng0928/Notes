# Spark support for Hive table

## Hive settings
* Character case setting

`set spark.sql.caseSensitive=true`
```scala
sqlContext.sql("set spark.sql.caseSensitive=false")
sqlContext.sql("set spark.sql.caseSensitive=true")
```

* Dynamic partition support
```
set spark.hadoop.hive.exec.dynamic.partition=true
set spark.hadoop.hive.exec.dynamic.partition.mode=nonstrick
```

* Hive table subfolder support
```
set mapred.input.dir.recursive=true
set mapreduce.input.fileinputformat.input.dir.recursive=true
``
