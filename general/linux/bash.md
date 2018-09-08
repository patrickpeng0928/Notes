# Unix

http://tldp.org/LDP/abs/html/index.html

## date

`date [option] ... [+Format]`

`date [option] [MMDDhhmm[[CC]YY][.ss]]`

1. Options:


   | option | long option | description |
   |:--- |:--- | :--- |
   | `-d` |`--date=String` | display time described by String. Any common format or literal. Now, yesterday,  -1 day, time zone, am, pm, ago, next, etc |
   | `-f` | `--file=DateFile` | `-d` on each line of DateFile |
   | `-I` | `--iso-8601[=Timespec]` | ISO 8601 compliant date/time string. %Y-%m-%d. Timespec = {date, hours, minutes, seconds} |
   | `-r` | `--reference=File` | display the last modification time of File |
   | | `--help` | |
   | | `--version` | |

2. Formats:

   1. Date

      `D` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; date in mm/dd/yy

      `x` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; date in local standard format

   2. Year

      `C` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Century in 2 digit

      `Y` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Year in 4 digit

      `y` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; year in last 2 digit

      `G` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; `Y`

      `g` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; `y`

   3. Month

      `b` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Month name - abbreviated

      `B` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Month name - full

      `h` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; `b`

      `m` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Month in 2 digit number

   4. Week

      `W` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Week in [00, 52]

      `V` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Week in [01, 53]

      `U` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; `W`

   5. Day

      `a` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Day of the week - abbreviated name (Mon)

      `A` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Day of the week - full name (Monday)

      `u` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Day of the week in 1 digit number (Mon = 1)

      `d` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Day of the month in 2 digits

      `e` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Day of the month in 1 or 2 digits, preceded by a space

      `j` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Day of the year in [1, 366]

      `w` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; `u`

   6. Time

      `p` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; AM or PM

      `r` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Time in 12-hour format

      `R` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Time in 24-hour format w/o seconds

      `T` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Time in 24-hour format w. seconds

      `X` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; `T`

      `Z` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Time offset from UTC in abbreviated name (CDT)

      `z` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Time offset from UTC in signed number (-0500)

   7. Hour

      `H` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Hour in 24-hour format

      `I` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Hour in 12-hour format

      `k` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; `H`

      `l` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; `I`

   8. Minutes & Seconds

      `M` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Minutes

      `S` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Seconds

      `s` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; epoch seconds (from January 1, 1970 00:00:00 GMT)

   9. pad

      `-` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; do not pad field

      `_` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; pad with spaces

      `defualt`					pad with 0

3. Examples:

```bash
date -I 							# 2018-09-06
date +%Y-%m-%d 							# 2018-09-06
date +%d-%b-%y 							# 06-Sep-18
date +%Y-%m-%d:%H:%M:%S 		      # 2018-09-06:22:07:13
date -d now 							# Thu Sep  6 22:07:58 CDT 2018
date -d '3 months 1 day' 			# Fri Dec  7 21:09:07 CST 2018
date -d "$DATE_VARIABLE - 1 day" 							# 1 day before DATE_VARIABLE
date -d "$DATE_VARIABLE 1 day ago" 	# 1 day before DATE_VARIABLE
date -d 1may '+%B %d' 			# May 01
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
