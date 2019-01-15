# Handling Hive table in Spark Scala
## Hive Commands
```scala
val showPartitions = "show partitions"
```

## Get Latest Partition data from a partitioned table
```scala
def getLatestTableDate(
                        tableName: String
                      , partitionCol: String = "partition"
                      ): String = {
  val partitions: DataFrame = spark.sql(s"$showPartitions $tableName")
  val lastDate: String = partitions.agg(max(col(partitionCol))).map(row => row.mkString.split("=").last).collect().head
  return lastDate
}
```
