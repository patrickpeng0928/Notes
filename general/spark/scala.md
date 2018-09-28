# Scala Functions
## date
### import packages
```scala
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Calendar
```

### Date Format
```scala
val dateFormat = new SimpleDateFormat("yyyy-MM-dd")
```

### date to epoch time
```scala
// get time in ms
val epoch_time: Long = dateFormat.parse(start_date).getTime
```

### +/- days
```scala
val delta_days: Int = 1
val start_date = new Date()
val start_date = "yyyy-MM-dd"
val calendar: Calendar = Calendar.getInstance()
calendar.setTime(dateFormat.parse(end_date))
calendar.add(Calendar.DATE, delta_days)
val end_date: String = dateFormat.format(calendar.getTime)
```
