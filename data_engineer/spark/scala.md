# Scala Functions
## Command line interface: spark-shell
```bash
spark-shell --conf spark.ui.port=PORT_NUMBER
# PORT_NUMBER < 65535

spark-shell --conf spark.port.maxRetries=RETRY_NUMBER
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

val startDate = "2018-12-01"
val endDate = "2018-12-08"

def dayIterator(start: LocalDate, end: LocalDate) = Iterator.iterate(start)(_ plusDays 1) takeWhile (_ isBefore end)

dayIterator(new LocalDate(startDate), new LocalDate(endDate)).foreach((sd: LocalDate) => {
  val start = sd.toString("yyyy-MM-dd")
  val end = sd.plusDays(1).toString("yyyy-MM-dd")
  ...
})
```

## HDFS File Operations
### Set up HDFS configurations
#### Enable hdfs append
```scala
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.FileSystem
import org.apache.spark.sql.SparkSession

def configureHDFS(): FileSystem = {
  val fs: FileSystem = try {
    val spark: SparkSession = SparkSession.builder().getOrCreate()
    val conf: Configuration = spark.sparkContext.hadoopConfiguration
    conf.setBoolean("dfs.support.append", true)
    FileSystem.get(conf)
  } catch  {
    case e: Exception =>
      println(s"Error occurred while configuring FileSystem: ${e.printStackTrace()}")
      sys.exit(1)
  }
  
  return fs
}
```

### Create a new file or append an existing file
```scala
import org.apache.hadoop.fs.{FSDataOutputStream, FileSystem, Path}
import org.apache.spark.sql.SparkSession

def appendHDFSFile(
                    content: String
                    , filePath: String
                  ): Unit = {
  val outputPath: Path = new Path(filePath)
  val fs: FileSystem = configureHDFS()
  val outputStream: FSDataOutputStream = if (!fs.exists(outputPath)) {
    fs.create(outputPath)
  } else {
    val isAppendable = fs.getConf.get("dfs.support.append").toBoolean
    if (isAppendable) {
      fs.append(outputPath)
    } else {
      println("Please set the dfs.support.append property to true")
      sys.exit(1)
    }
  }
  outputStream.writeUTF(content)
  outputStream.close()
}
```
