# # Unix

date

`date [option] ... [+Format]`

`date [option] [MMDDhhmm[[CC]YY][.ss]]`

1. Options:

   `-d, --date=String`				display time described by String. Any common format or literal. Now, yesterday,  -1 day, time zone, am, pm, ago, next, etc

   `-f, --file=DateFile`			-d on each line of DateFile

   `-I, --iso-8601[=Timespec]`		ISO 8601 compliant date/time string. %Y-%m-%d. Timespec = {date, hours, minutes, seconds}

   `-r, --reference=File`			display the last modification time of File

   `--help`

   `--version`

2. Formats:

   1. Date

      `D`							date in mm/dd/yy

      `x`							date in local standard format

   2. Year

      `C`							Century in 2 digit

      `Y`							Year in 4 digit

      `y`							year in last 2 digit

      `G`							`Y`

      `g`							`y`

   3. Month

      `b`							Month name - abbreviated

      `B`							Month name - full

      `h`							`b`

      `m`							Month in 2 digit number

   4. Week

      `W`							Week in [00, 52]

      `V`							Week in [01, 53]

      `U`							`W`

   5. Day

      `a`							Day of the week - abbreviated name (Mon)

      `A`							Day of the week - full name (Monday)

      `u`							Day of the week in 1 digit number (Mon = 1)

      `d`							Day of the month in 2 digits

      `e`							Day of the month in 1 or 2 digits, preceded by a space

      `j`							Day of the year in [1, 366]

      `w`							`u`

   6. Time

      `p`							AM or PM

      `r`							Time in 12-hour format

      `R`							Time in 24-hour format w/o seconds

      `T`							Time in 24-hour format w. seconds

      `X`							`T`

      `Z`							Time offset from UTC in abbreviated name (CDT)

      `z`							Time offset from UTC in signed number (-0500)

   7. Hour

      `H`							Hour in 24-hour format

      `I`							Hour in 12-hour format

      `k`							`H`

      `l`							`I`

   8. Minutes & Seconds

      `M`							Minutes

      `S`							Seconds

      `s`							epoch seconds (from January 1, 1970 00:00:00 GMT)

   9. pad

      `-`							do not pad field

      `_`							pad with spaces

      `defualt`					pad with 0

3. Examples:

```bash
date -I 							# 2018-09-06
date +%Y-%m-%d 						# 2018-09-06
date +%d-%b-%y 						# 06-Sep-18
date +%Y 							# 2018
date +%y							# 18
date +%m							#

```

