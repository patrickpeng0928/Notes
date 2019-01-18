# Spark SQL
## Date format

| symbol | meaning |
| --- | --- |
| yyyy | 4-digit year | 
| MM | 2-digit month | 
| dd | 2-digit date | 
| HH | 2-digit Hour in 24-format | 
| hh | 2-digit Hour in 12-format | 
| mm | 2-digit Minute | 
| ss | 2-digit second | 
| sss | 3-digit second | 
| SSS | 3-digit milisecond | 
| a | 2-character AM/PM | 
| z | 3-character timezone | 
| Z | +/- 4-digit timezone |

## Get partitions from partitioned hive table
```python
sc = SparkContext()
hive = HiveContext(sc)
showPartitions = "show partitions"
partitions = hive.sql("{0} {1}".format(showPartitions, tableName))
lastPartition = partitions.agg(max("result")).map(lambda row: row['max(result)'].split("=")[-1]).collect()[0]
```
