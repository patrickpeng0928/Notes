# Unix

http://tldp.org/LDP/abs/html/index.html

## date

`date [option] ... [+Format]`

`date [option] [MMDDhhmm[[CC]YY][.ss]]`

1. Options:

   `-d, --date=String` &npsp;&npsp;&npsp;&npsp;display time described by String. Any common format or literal. Now, yesterday,  -1 day, time zone, am, pm, ago, next, etc

   `-f, --file=DateFile`			`-d` on each line of DateFile

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
date +%Y-%m-%d 							# 2018-09-06
date +%d-%b-%y 							# 06-Sep-18
date +%Y-%m-%d:%H:%M:%S 							# 2018-09-06:22:07:13
date -d now 							# Thu Sep  6 22:07:58 CDT 2018
date -d '3 months 1 day' 							# Fri Dec  7 21:09:07 CST 2018
date -d "$DATE_VARIABLE - 1 day" 							# 1 day before $DATE_VARIABLE
date -d "$DATE_VARIABLE 1 day ago" 							# 1 day before $DATE_VARIABLE
date -d 1may '+%B %d' 							# May 01
```

4. Used in shell script:

```bash
`date +%Y-%m-%d:%H:%M:%S`
$(date +%Y-%m-%d:%H:%M:%S)
```

## Redirection

`echo “” 2>&1 | tee -a /path/to/log/file.log` Redirect `stderr` to file descriptor 1 (`stdout`), `&` indicates that what follows is a file descriptor, not a filename.

File descriptor 0 is standard input (`stdin`).

File descriptor 1 is standard output (`stdout`).

File descriptor 2 is standard error (`stderr`).

`echo “” > afile.txt`	redirects `stdout` to afile.txt

`echo “” 1> afile.txt`	redirects `stdout` to afile.txt

`echo “” 2> afile.txt`	redirects `stderr` to afile.txt

`>&`	redirects to a file descriptor

`echo “” 1>&2`			redirects `stdout` to `stderr`

`>`	*send to as a whole completed file*, **overwriting** target if exist

`>>` *send in addition to* would **append** to target if exist

`echo “” >> /path/to/log/file.log  `

https://stackoverflow.com/questions/818255/in-the-shell-what-does-21-mean

## Dynamic Programming

```bash
var1="abc"
pre_abc="xyz"
var2=pre_$var1
echo $var2 # pre_abc
echo ${!var2} # xyz
```



## lower case and upper case

```
var="aBc"
echo ${var^^} # ABC
echo ${var,,} # abc
```



## Case

```bash
case "$1" in:
	a )
		echo a
		;;
	*b* )
		echo c
		;;
	*)
		echo *
		;;
esac
```



## IF





## grep



## sed

`**sed**: a non-interactive text file editor`

## awk

`**awk**: a field-oriented pattern processing language with a **C**-style syntax`

https://likegeeks.com/awk-command/
