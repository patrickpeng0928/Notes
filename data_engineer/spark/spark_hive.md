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
1. support in hive-cli
```
set mapred.input.dir.recursive=true
set mapreduce.input.fileinputformat.input.dir.recursive=true
```
2. support in spark-shell
```
spark.conf.set("mapreduce.input.fileinputformat.input.dir.recursive","true")
spark.conf.set("hive.input.dir.recursive","true")
spark.conf.set("hive.mapred.supports.subdirectories","true")
spark.conf.set("hive.supports.subdirectories","true")
```
3. support in pyspark
```
sqlContext.setConf("mapreduce.input.fileinputformat.input.dir.recursive","true") 
sqlContext.setConf("hive.input.dir.recursive","true") 
sqlContext.setConf("hive.mapred.supports.subdirectories","true") 
sqlContext.setConf("hive.supports.subdirectories","true")
```
