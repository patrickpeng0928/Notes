# Hive Operations

## Date Conversions
### Hive Functions
| Return Type | Name(Signature) | Description |
| string | from_unixtime(bigint unixtime[, string format]) | Converts the number of seconds from unix epoch (1970-01-01 00:00:00 UTC) to a string representing the timestamp of that moment in the current system time zone in the format of "1970-01-01 00:00:00". |
| bigint | unix_timestamp() | Gets current Unix timestamp in seconds. This function is not deterministic and its value is not fixed for the scope of a query execution, therefore prevents proper optimization of queries - this has been deprecated since 2.0 in favour of CURRENT_TIMESTAMP constant. |
| bigint | unix_timestamp(string date) | Converts time string in format <code>yyyy-MM-dd HH:mm:ss</code> to Unix timestamp (in seconds), using the default timezone and the default locale, return 0 if fail: unix_timestamp('2009-03-20 11:30:01') = 1237573801 |
| bigint | unix_timestamp(string date, string pattern) | Convert time string with given pattern (see http://docs.oracle.com/javase/tutorial/i18n/format/simpleDateFormat.html) to Unix time stamp (in seconds), return 0 if fail: unix_timestamp('2009-03-20', 'yyyy-MM-dd') = 1237532400. |
| *pre 2.1.0:* string</p>*2.1.0 on:* date | to_date(string timestamp) | Returns the date part of a timestamp string (pre-Hive 2.1.0): to_date("1970-01-01 00:00:00") = "1970-01-01". As of Hive 2.1.0, returns a date object. Prior to Hive 2.1.0 ([HIVE-13248](https://issues.apache.org/jira/browse/HIVE-13248)) the return type was a String because no Date type existed when the method was created. |
| int | year(string date) | Returns the year part of a date or a timestamp string: year("1970-01-01 00:00:00") = 1970, year("1970-01-01") = 1970. |
| int | quarter(date/timestamp/string) | Returns the quarter of the year for a date, timestamp, or string in the range 1 to 4 (as of Hive [1.3.0](https://issues.apache.org/jira/browse/HIVE-3404)). Example: quarter('2015-04-08') = 2. |
| int | month(string date) | Returns the month part of a date or a timestamp string: month("1970-11-01 00:00:00") = 11, month("1970-11-01") = 11. |
| int | day(string date) dayofmonth(date) | Returns the day part of a date or a timestamp string: day("1970-11-01 00:00:00") = 1, day("1970-11-01") = 1. |
| int | hour(string date) | Returns the hour of the timestamp: hour('2009-07-30 12:58:59') = 12, hour('12:58:59') = 12. |
| int | minute(string date) | Returns the minute of the timestamp. |
| int | second(string date) | Returns the second of the timestamp. |
| int | weekofyear(string date) | Returns the week number of a timestamp string: weekofyear("1970-11-01 00:00:00") = 44, weekofyear("1970-11-01") = 44. |
| int | extract(field FROM source) | Retrieve fields such as days or hours from source (as of Hive [2.2.0](https://issues.apache.org/jira/browse/HIVE-14579)). Source must be a date, timestamp, interval or a string that can be converted into either a date or timestamp. Supported fields include: day, dayofweek, hour, minute, month, quarter, second, week and year. Examples: select extract(month from "2016-10-20") results in 10. select extract(hour from "2016-10-20 05:06:07") results in 5. select extract(dayofweek from "2016-10-20 05:06:07") results in 5. select extract(month from interval '1-3' year to month) results in 3. select extract(minute from interval '3 12:20:30' day to second) results in 20. |
| int | datediff(string enddate, string startdate) | Returns the number of days from startdate to enddate: datediff('2009-03-01', '2009-02-27') = 2. |
| *pre 2.1.0:* string</p>*2.1.0 on:* date | date_add(date/timestamp/string startdate, tinyint/smallint/int days) | Adds a number of days to startdate: date_add('2008-12-31', 1) = '2009-01-01'. Prior to Hive 2.1.0 ([HIVE-13248](https://issues.apache.org/jira/browse/HIVE-13248)) the return type was a String because no Date type existed when the method was created. |
| *pre 2.1.0:* string</p>*2.1.0 on:* date | date_sub(date/timestamp/string startdate, tinyint/smallint/int days) | Subtracts a number of days to startdate: date_sub('2008-12-31', 1) = '2008-12-30'. Prior to Hive 2.1.0 ([HIVE-13248](https://issues.apache.org/jira/browse/HIVE-13248)) the return type was a String because no Date type existed when the method was created. |
| timestamp | from_utc_timestamp({*any primitive type*} ts, string timezone) | Converts a timestamp* in UTC to a given timezone (as of Hive [0.8.0](https://issues.apache.org/jira/browse/HIVE-2272)). *timestamp is a primitive type, including timestamp/date, tinyint/smallint/int/bigint, float/double and decimal. Fractional values are considered as seconds. Integer values are considered as milliseconds. For example, from_utc_timestamp (2592000.0,'PST'), from_utc_timestamp (2592000000,'PST') and from_utc_timestamp (timestamp '1970-01-30 16:00:00','PST') all return the timestamp 1970-01-30 08:00:00. |
| timestamp | to_utc_timestamp({*any * *primitive type*} ts , string timezone) | Converts a timestamp* in a given timezone to UTC (as of Hive [0.8.0](https://issues.apache.org/jira/browse/HIVE-2272)). *timestamp is a primitive type, including timestamp/date, tinyint/smallint/int/bigint, float/double and decimal. Fractional values are considered as seconds. Integer values are considered as milliseconds. For example, to_utc_timestamp(2592000.0,'PST'), to_utc_timestamp(2592000000,'PST') and to_utc_timestamp(timestamp '1970-01-30 16:00:00','PST') all return the timestamp 1970-01-31 00:00:00. |
| date | current_date | Returns the current date at the start of query evaluation (as of Hive [1.2.0](https://issues.apache.org/jira/browse/HIVE-5472)). All calls of current_date within the same query return the same value. |
| timestamp | current_timestamp | Returns the current timestamp at the start of query evaluation (as of Hive [1.2.0](https://issues.apache.org/jira/browse/HIVE-5472)). All calls of current_timestamp within the same query return the same value. |
| string | add_months(string start_date, int num_months, <span style="color: rgb(0,0,0);">output_date_format ) | Returns the date that is num_months after start_date (as of Hive [1.1.0](https://issues.apache.org/jira/browse/HIVE-9357)). start_date is a string, date or timestamp. num_months is an integer. If start_date is the last day of the month or if the resulting month has fewer days than the day component of start_date, then the result is the last day of the resulting month. Otherwise, the result has the same day component as start_date. The default output format is 'yyyy-MM-dd'. Before Hive 4.0.0, the time part of the date is ignored. As of Hive [4.0.0](https://issues.apache.org/jira/browse/HIVE-19370), add_months supports an optional argument output_date_format, which accepts a String that represents a valid date format for the output. This allows to retain the time format in the output. For example : add_months('2009-08-31', 1) returns '2009-09-30'. add_months('2017-12-31 14:15:16', 2, 'YYYY-MM-dd HH:mm:ss') returns '2018-02-28 14:15:16'. |
| string | last_day(string date) | Returns the last day of the month which the date belongs to (as of Hive [1.1.0](https://issues.apache.org/jira/browse/HIVE-9358)). date is a string in the format 'yyyy-MM-dd HH:mm:ss' or 'yyyy-MM-dd'. The time part of date is ignored. |
| string | next_day(string start_date, string day_of_week) | Returns the first date which is later than start_date and named as day_of_week (as of Hive [1.2.0](https://issues.apache.org/jira/browse/HIVE-9520)). start_date is a string/date/timestamp. day_of_week is 2 letters, 3 letters or full name of the day of the week (e.g. Mo, tue, FRIDAY). The time part of start_date is ignored. Example: next_day('2015-01-14', 'TU') = 2015-01-20. |
| string | trunc(string date, string format) | Returns date truncated to the unit specified by the format (as of Hive [1.2.0](https://issues.apache.org/jira/browse/HIVE-9480)). Supported formats: MONTH/MON/MM, YEAR/YYYY/YY. Example: trunc('2015-03-17', 'MM') = 2015-03-01. |
| double | months_between(date1, date2) | Returns number of months between dates date1 and date2 (as of Hive [1.2.0](https://issues.apache.org/jira/browse/HIVE-9518)). If date1 is later than date2, then the result is positive. If date1 is earlier than date2, then the result is negative. If date1 and date2 are either the same days of the month or both last days of months, then the result is always an integer. Otherwise the UDF calculates the fractional portion of the result based on a 31-day month and considers the difference in time components date1 and date2. date1 and date2 type can be date, timestamp or string in the format 'yyyy-MM-dd' or 'yyyy-MM-dd HH:mm:ss'. The result is rounded to 8 decimal places. Example: months_between('1997-02-28 10:30:00', '1996-10-30') = 3.94959677 |
| string | date_format(date/timestamp/string ts, string fmt) | Converts a date/timestamp/string to a value of string in the format specified by the date format fmt (as of Hive [1.2.0](https://issues.apache.org/jira/browse/HIVE-10276)). Supported formats are Java SimpleDateFormat formats - [](https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html). The second argument fmt should be constant. Example: date_format('2015-04-08', 'y') = '2015'. date_format can be used to implement other UDFs, e.g.: dayname(date) is date_format(date, 'EEEE') dayofyear(date) is date_format(date, 'D') |


### Common Conversions
#### to_date
```
to_date(timestamp string)
```

#### Date String with different input format
* use dt (String) as date column in String
* input_format (String) as input format
* ouput_format (String)  as output format
```hive
from_unixtime(unix_timestamp(dt, input_format), output_format)
```

| Input Format | Code | Output Format |
| --- | --- | --- |
| ddMMyyyy | to_date(from_unixtime(unix_timestamp(dt, ’ddMMyyyy’))) | yyyy-MM-dd |
| dd-MM-yyyy | to_date(from_unixtime(unix_timestamp(dt, ’dd-MM-yyyy’))) | yyyy-MM-dd |
| dd/MM/yyyy | to_date(from_unixtime(unix_timestamp(dt, ’dd/MM/yyyy’))) | yyyy-MM-dd |
| dd MM yyyy | to_date(from_unixtime(unix_timestamp(dt, ’dd MM yyyy’))) | yyyy-MM-dd |
| dd.MM.yyyy | to_date(from_unixtime(unix_timestamp(dt, ’dd.MM.yyyy’))) | yyyy-MM-dd |
| ddMMMyyyy | to_date(from_unixtime(unix_timestamp(dt, ’ddMMMyyyy’))) | yyyy-MM-dd |
| dd-MMM-yyyy | to_date(from_unixtime(unix_timestamp(dt, ’dd-MMM-yyyy’))) | yyyy-MM-dd |
| dd/MMM/yyyy | to_date(from_unixtime(unix_timestamp(dt, ’dd/MMM/yyyy’))) | yyyy-MM-dd |
| dd MMM yyyy | to_date(from_unixtime(unix_timestamp(dt, ’dd MMM yyyy’))) | yyyy-MM-dd |
| dd.MMM.yyyy | to_date(from_unixtime(unix_timestamp(dt, ’dd.MMM.yyyy’))) | yyyy-MM-dd |
| ddMMMMyyyy | to_date(from_unixtime(unix_timestamp(dt, ’ddMMMMyyyy’))) | yyyy-MM-dd |
| dd-MMMM-yyyy | to_date(from_unixtime(unix_timestamp(dt, ’dd-MMMM-yyyy’))) | yyyy-MM-dd |
| dd/MMMM/yyyy | to_date(from_unixtime(unix_timestamp(dt, ’dd/MMMM/yyyy’))) | yyyy-MM-dd |
| dd MMMM yyyy | to_date(from_unixtime(unix_timestamp(dt, ’dd MMMM yyyy’))) | yyyy-MM-dd |
| dd.MMMM.yyyy | to_date(from_unixtime(unix_timestamp(dt, ’dd.MMMM.yyyy’))) | yyyy-MM-dd |
| ddMMyy | to_date(from_unixtime(unix_timestamp(dt, ’ddMMyy’))) | yyyy-MM-dd |
| dd-MM-yy | to_date(from_unixtime(unix_timestamp(dt, ’dd-MM-yy’))) | yyyy-MM-dd |
| dd/MM/yy | to_date(from_unixtime(unix_timestamp(dt, ’dd/MM/yy’))) | yyyy-MM-dd |
| dd MM yy | to_date(from_unixtime(unix_timestamp(dt, ’dd MM yy’))) | yyyy-MM-dd |
| dd.MM.yy | to_date(from_unixtime(unix_timestamp(dt, ’dd.MM.yy’))) | yyyy-MM-dd |
| ddMMMyy | to_date(from_unixtime(unix_timestamp(dt, ’ddMMMyy’))) | yyyy-MM-dd |
| dd-MMM-yy | to_date(from_unixtime(unix_timestamp(dt, ’dd-MMM-yy’))) | yyyy-MM-dd |
| dd/MMM/yy | to_date(from_unixtime(unix_timestamp(dt, ’dd/MMM/yy’))) | yyyy-MM-dd |
| dd MMM yy | to_date(from_unixtime(unix_timestamp(dt, ’dd MMM yy’))) | yyyy-MM-dd |
| dd.MMM.yy | to_date(from_unixtime(unix_timestamp(dt, ’dd.MMM.yy’))) | yyyy-MM-dd |
| ddMMMMyy | to_date(from_unixtime(unix_timestamp(dt, ’ddMMMMyy’))) | yyyy-MM-dd |
| dd-MMMM-yy | to_date(from_unixtime(unix_timestamp(dt, ’dd-MMMM-yy’))) | yyyy-MM-dd |
| dd/MMMM/yy | to_date(from_unixtime(unix_timestamp(dt, ’dd/MMMM/yy’))) | yyyy-MM-dd |
| dd MMMM yy | to_date(from_unixtime(unix_timestamp(dt, ’dd MMMM yy’))) | yyyy-MM-dd |
| dd.MMMM.yy | to_date(from_unixtime(unix_timestamp(dt, ’dd.MMMM.yy’))) | yyyy-MM-dd |
| MMddyyyy | to_date(from_unixtime(unix_timestamp(dt, ’MMddyyyy’))) | yyyy-MM-dd |
| MM-dd-yyyy | to_date(from_unixtime(unix_timestamp(dt, ’MM-dd-yyyy’))) | yyyy-MM-dd |
| MM/dd/yyyy | to_date(from_unixtime(unix_timestamp(dt, ’MM/dd/yyyy’))) | yyyy-MM-dd |
| MM dd yyyy | to_date(from_unixtime(unix_timestamp(dt, ’MM dd yyyy’))) | yyyy-MM-dd |
| MM.dd.yyyy | to_date(from_unixtime(unix_timestamp(dt, ’MM.dd.yyyy’))) | yyyy-MM-dd |
| MMMddyyyy | to_date(from_unixtime(unix_timestamp(dt, ’MMMddyyyy’))) | yyyy-MM-dd |
| MMM-dd-yyyy | to_date(from_unixtime(unix_timestamp(dt, ’MMM-dd-yyyy’))) | yyyy-MM-dd |
| MMM/dd/yyyy | to_date(from_unixtime(unix_timestamp(dt, ’MMM/dd/yyyy’))) | yyyy-MM-dd |
| MMM dd yyyy | to_date(from_unixtime(unix_timestamp(dt, ’MMM dd yyyy’))) | yyyy-MM-dd |
| MMM.dd.yyyy | to_date(from_unixtime(unix_timestamp(dt, ’MMM.dd.yyyy’))) | yyyy-MM-dd |
| MMMMddyyyy | to_date(from_unixtime(unix_timestamp(dt, ’MMMMddyyyy’))) | yyyy-MM-dd |
| MMMM-dd-yyyy | to_date(from_unixtime(unix_timestamp(dt, ’MMMM-dd-yyyy’))) | yyyy-MM-dd |
| MMMM/dd/yyyy | to_date(from_unixtime(unix_timestamp(dt, ’MMMM/dd/yyyy’))) | yyyy-MM-dd |
| MMMM dd yyyy | to_date(from_unixtime(unix_timestamp(dt, ’MMMM dd yyyy’))) | yyyy-MM-dd |
| MMMM.dd.yyyy | to_date(from_unixtime(unix_timestamp(dt, ’MMMM.dd.yyyy’))) | yyyy-MM-dd |
| MMddyy | to_date(from_unixtime(unix_timestamp(dt, ’MMddyy’))) | yyyy-MM-dd |
| MM-dd-yy | to_date(from_unixtime(unix_timestamp(dt, ’MM-dd-yy’))) | yyyy-MM-dd |
| MM/dd/yy | to_date(from_unixtime(unix_timestamp(dt, ’MM/dd/yy’))) | yyyy-MM-dd |
| MM dd yy | to_date(from_unixtime(unix_timestamp(dt, ’MM dd yy’))) | yyyy-MM-dd |
| MM.dd.yy | to_date(from_unixtime(unix_timestamp(dt, ’MM.dd.yy’))) | yyyy-MM-dd |
| MMMddyy | to_date(from_unixtime(unix_timestamp(dt, ’MMMddyy’))) | yyyy-MM-dd |
| MMM-dd-yy | to_date(from_unixtime(unix_timestamp(dt, ’MMM-dd-yy’))) | yyyy-MM-dd |
| MMM/dd/yy | to_date(from_unixtime(unix_timestamp(dt, ’MMM/dd/yy’))) | yyyy-MM-dd |
| MMM dd yy | to_date(from_unixtime(unix_timestamp(dt, ’MMM dd yy’))) | yyyy-MM-dd |
| MMM.dd.yy | to_date(from_unixtime(unix_timestamp(dt, ’MMM.dd.yy’))) | yyyy-MM-dd |
| MMMMddyy | to_date(from_unixtime(unix_timestamp(dt, ’MMMMddyy’))) | yyyy-MM-dd |
| MMMM-dd-yy | to_date(from_unixtime(unix_timestamp(dt, ’MMMM-dd-yy’))) | yyyy-MM-dd |
| MMMM/dd/yy | to_date(from_unixtime(unix_timestamp(dt, ’MMMM/dd/yy’))) | yyyy-MM-dd |
| MMMM dd yy | to_date(from_unixtime(unix_timestamp(dt, ’MMMM dd yy’))) | yyyy-MM-dd |
| MMMM.dd.yy | to_date(from_unixtime(unix_timestamp(dt, ’MMMM.dd.yy’))) | yyyy-MM-dd |
| yyyyMMdd | to_date(from_unixtime(unix_timestamp(dt, ’yyyyMMdd’))) | yyyy-MM-dd |
| yyyy-MM-dd | to_date(from_unixtime(unix_timestamp(dt, ’yyyy-MM-dd’))) | yyyy-MM-dd |
| yyyy/MM/dd | to_date(from_unixtime(unix_timestamp(dt, ’yyyy/MM/dd’))) | yyyy-MM-dd |
| yyyy MM dd | to_date(from_unixtime(unix_timestamp(dt, ’yyyy MM dd ‘))) | yyyy-MM-dd |
| yyyy.MM.dd | to_date(from_unixtime(unix_timestamp(dt, ’yyyy.MM.dd’))) | yyyy-MM-dd |
| yyyyMMMdd | to_date(from_unixtime(unix_timestamp(dt, ’yyyyMMMdd’))) | yyyy-MM-dd |
| yyyy-MMM-dd | to_date(from_unixtime(unix_timestamp(dt, ’yyyy-MMM-dd’))) | yyyy-MM-dd |
| yyyy/MMM/dd | to_date(from_unixtime(unix_timestamp(dt, ’yyyy/MMM/dd’))) | yyyy-MM-dd |
| yyyy MMM dd | to_date(from_unixtime(unix_timestamp(dt, ’yyyy MMM dd ‘))) | yyyy-MM-dd |
| yyyy.MMM.dd | to_date(from_unixtime(unix_timestamp(dt, ’yyyy.MMM.dd’))) | yyyy-MM-dd |
| yyyyMMMMdd | to_date(from_unixtime(unix_timestamp(dt, ’yyyyMMMMdd’))) | yyyy-MM-dd |
| yyyy-MMMM-dd | to_date(from_unixtime(unix_timestamp(dt, ’yyyy-MMMM-dd’))) | yyyy-MM-dd |
| yyyy/MMMM/dd | to_date(from_unixtime(unix_timestamp(dt, ’yyyy/MMMM/dd’))) | yyyy-MM-dd |
| yyyy MMMM dd | to_date(from_unixtime(unix_timestamp(dt, ’yyyy MMMM dd ‘))) | yyyy-MM-dd |
| yyyy.MMMM.dd | to_date(from_unixtime(unix_timestamp(dt, ’yyyy.MMMM.dd’))) | yyyy-MM-dd |
| yyMMdd | to_date(from_unixtime(unix_timestamp(dt, ’yyMMdd’))) | yyyy-MM-dd |
| yy-MM-dd | to_date(from_unixtime(unix_timestamp(dt, ’yy-MM-dd’))) | yyyy-MM-dd |
| yy/MM/dd | to_date(from_unixtime(unix_timestamp(dt, ’yy/MM/dd’))) | yyyy-MM-dd |
| yy MM dd | to_date(from_unixtime(unix_timestamp(dt, ’yy MM dd ‘))) | yyyy-MM-dd |
| yy.MM.dd | to_date(from_unixtime(unix_timestamp(dt, ’yy.MM.dd’))) | yyyy-MM-dd |
| yyMMMdd | to_date(from_unixtime(unix_timestamp(dt, ’yyMMMdd’))) | yyyy-MM-dd |
| yy-MMM-dd | to_date(from_unixtime(unix_timestamp(dt, ’yy-MMM-dd’))) | yyyy-MM-dd |
| yy/MMM/dd | to_date(from_unixtime(unix_timestamp(dt, ’yy/MMM/dd’))) | yyyy-MM-dd |
| yy MMM dd | to_date(from_unixtime(unix_timestamp(dt, ’yy MMM dd ‘))) | yyyy-MM-dd |
| yy.MMM.dd | to_date(from_unixtime(unix_timestamp(dt, ’yy.MMM.dd’))) | yyyy-MM-dd |
| yyMMMMdd | to_date(from_unixtime(unix_timestamp(dt, ’yyMMMMdd’))) | yyyy-MM-dd |
| yy-MMMM-dd | to_date(from_unixtime(unix_timestamp(dt, ’yy-MMMM-dd’))) | yyyy-MM-dd |
| yy/MMMM/dd | to_date(from_unixtime(unix_timestamp(dt, ’yy/MMMM/dd’))) | yyyy-MM-dd |
| yy MMMM dd | to_date(from_unixtime(unix_timestamp(dt, ’yy MMMM dd ‘))) | yyyy-MM-dd |
| yy.MMMM.dd | to_date(from_unixtime(unix_timestamp(dt, ’yy.MMMM.dd’))) | yyyy-MM-dd |

## Use schema
```hive
USE <db_name>;
```

## Drop table
```hive
DROP TABLE IF EXISTS <tbl_name>;
```

## Drop view
```hive
DROP VIEW IF EXISTS <view_name>;
```

## Repair table
```hive
MSCK REPAIR TABLE ${table};
```

## Set variable
```hive
set VARIABLE_NAME=vale;
```

## Arguments
```hive
select ${in_varible}
```

```bash
beeline -f /path/to/hive/script.hql --hivevar in_varible=$IN_VARIABLE_IN_SHELL_SCRIPT
```
