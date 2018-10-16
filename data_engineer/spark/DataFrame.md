# DataFrame Operations
## imports
```
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._
```
## Create DF
```
val df = rdd.toDF(<schema>)
val df = spark.read.json(jsonRdd)
```
