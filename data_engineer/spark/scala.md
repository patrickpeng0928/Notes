# Scala Functions
## Command line interface: spark-shell
```bash
spark-shell --conf spark.ui.port=PORT_NUMBER
# PORT_NUMBER < 65535

spark-shell --conf spark.port.maxRetries=RETRY_NUMBER
```

## Set conf
```scala
spark.conf.set(key, value)
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
