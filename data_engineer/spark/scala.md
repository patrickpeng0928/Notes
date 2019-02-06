# Scala Functions
## Command line interface: spark-shell
```bash
spark-shell --conf spark.ui.port=PORT_NUMBER
# PORT_NUMBER < 65535

spark-shell --conf spark.port.maxRetries=RETRY_NUMBER
```
## [Spark Conf](https://databricks.com/blog/2016/08/15/how-to-use-sparksession-in-apache-spark-2-0.html)
### Create conf
```scala
// create a empty SparkConf
// dynamically loading Spark Properties
// from spark-submit options
val sc = new SparkContext(new SparkConf)

//set up the spark configuration and create contexts
val sparkConf = new SparkConf().setAppName("SparkSessionZipsExample").setMaster("local")
val sc = new SparkContext(sparkConf).set("spark.some.config.option", "some-value")
val sqlContext = new org.apache.spark.sql.SQLContext(sc)
```
### Setter and getter
```scala
// set conf
spark.conf.set("spark.some.config", value)
// get conf
spark.conf.get("spark.some.config")
//get all settings
val configMap:Map[String, String] = spark.conf.getAll()
```
### Create SparkSession with Spark Conf
```scala
val spark = SparkSession.builder.config(sc.getConf).enableHiveSupport.getOrCreate()
```
### Set log level
```
sc.setLogLevel("ERROR")
```

## date
### import packages
```scala
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Calendar
```

### Date Format
```scala
import java.text.SimpleDateFormat

val dateFormat = new SimpleDateFormat("yyyy-MM-dd")
```

### date to epoch time
```scala
// get time in ms
val epoch_time: Long = dateFormat.parse(start_date).getTime
```

### +/- days
```scala
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Calendar

val delta_days: Int = 1
val start_date = new Date()
val start_date = "yyyy-MM-dd"
val calendar: Calendar = Calendar.getInstance()
calendar.setTime(dateFormat.parse(end_date))
calendar.add(Calendar.DATE, delta_days)
val end_date: String = dateFormat.format(calendar.getTime)
```

### Iterate through dates
```scala
import org.joda.time.LocalDate

# [start, end)
def dayIterator(start: LocalDate, end: LocalDate) = Iterator.iterate(start)(_ plusDays 1) takeWhile (_ isBefore end)

val startDate = "2018-12-01"
val endDate = "2018-12-08"
dayIterator(new LocalDate(startDate), new LocalDate(endDate)).foreach((sd: LocalDate) => {
  val start = sd.toString("yyyy-MM-dd")
  val end = sd.plusDays(1).toString("yyyy-MM-dd")
  ...
})
```
