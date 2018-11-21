# Spark support for Hive table

## Hive settings
* Character case setting
`set spark.sql.caseSensitive=true`
```scala
sqlContext.sql("set spark.sql.caseSensitive=false")
sqlContext.sql("set spark.sql.caseSensitive=true")
```
