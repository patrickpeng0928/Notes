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

## Filter DF
### isNull or isNotNull
```
df.filter(df.column.name.isNull())
df.filter(df.column.name.isNotNull())
```
