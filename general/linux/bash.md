# Unix

http://tldp.org/LDP/abs/html/index.html

## Comments
### exec
```
exec [-cl] [-a name] [command [arguments]]
      If command is specified, it replaces the shell.  No new  process
      is  created.  The arguments become the arguments to command.  If
      the -l option is supplied,  the  shell  places  a  dash  at  the
      beginning  of  the  zeroth  argument passed to command.  This is
      what login(1) does.  The -c option causes command to be executed
      with  an empty environment.  If -a is supplied, the shell passes
      name as the zeroth argument to the executed command.  If command
      cannot  be  executed  for  some  reason, a non-interactive shell
      exits, unless the execfail shell option  is  enabled.   In  that
      case,  it returns failure.  An interactive shell returns failure
      if the file cannot be executed.  If command  is  not  specified,
      any  redirections  take  effect  in  the  current shell, and the
      return status is 0.  If there is a redirection error, the return
      status is 1.
```
The last two lines are what is important: If you run `exec` by itself, without a command, it will simply make the redirections apply to the current shell. You probably know that when you run `command > file`, the output of command is written to file instead of to your terminal (this is called a redirection). If you run `exec > file` instead, then the redirection applies to the entire shell: Any output produced by the shell is written to file instead of to your terminal. 

To understand `exec` you need to first understand `fork`. I am trying to keep it short.

* When you come to a `fork` in the road you generally have two options. Linux programs reach this fork in the road when they hit a fork() system call.

* Normal programs are system commands that exist in a compiled form on your system. When such a program is executed, a new process is created. This child process has the same environment as its parent, only the process ID number is different. This procedure is called forking.

* Forking provides a way for an existing process to start a new one. However, there may be situations where a child process is not the part of the same program as parent process. In this case `exec` is used. `exec` will replace the contents of the currently running process with the information from a program binary.

* After the forking process, the address space of the child process is overwritten with the new process data. This is done through an `exec` call to the system.

`exec` is a command with two very distinct behaviors, depending on whether at least one argument is used with it, or no argument is used at all.

* If at least one argument is passed, the first one is taken as a command name and `exec` try to execute it as a command passing the remaining arguments, if any, to that command and managing the redirections, if any.

* If the command passed as first argument doesn't exist, the current shell, not only the exec command, exits in error.

* If the command exists and is executable, it replaces the current shell. That means that if `exec` appears in a script, the instructions following the `exec` call **will never be executed** (unless `exec` is itself in a subshell). `exec` never returns.

* If no argument is passed, `exec` is only used to redefine the current shell file descriptors. The shell continue after the `exec`, unlike with the previous case, but the standard input, output, error or whatever file descriptor has been redirected take effect.

* If some of the redirections uses /dev/null, any input from it will return EOF and any output to it will be discarded.

* You can close file descriptors by using `-` as source or destination, e.g. `exec <&-`. Subsequent read or writes will then fail.

```
$ help exec
exec: exec [-cl] [-a name] [command [arguments ...]] [redirection ...]
    Replace the shell with the given command.

    Execute COMMAND, replacing this shell with the specified program.
    ARGUMENTS become the arguments to COMMAND.  If COMMAND is not specified,
    any redirections take effect in the current shell.

    Options:
      -a name   pass NAME as the zeroth argument to COMMAND
      -c        execute COMMAND with an empty environment
      -l        place a dash in the zeroth argument to COMMAND

    If the command cannot be executed, a non-interactive shell exits, unless
    the shell option `execfail' is set.

    Exit Status:
    Returns success unless COMMAND is not found or a redirection error occurs.
```

### Multiple-line comments in Bash
```
#!/bin/bash
: <<'END'
some comments
some comments
some comments
END
```

## cut

`Divide a file into several parts (columns)
Writes to standard output selected parts of each line of each input file, or standard input if no files are given or for a file name of '-'.`

```bash
cut [OPTION]... [FILE]...
```


| Options | Description | Examples |
| --- | --- | --- |
| -b BYTE-LIST</p>--bytes=BYTE-LIST | Print only the bytes in positions listed in BYTE-LIST. Tabs and </p>backspaces are treated like any other character; they take up 1 </p>byte. |
| -c CHARACTER-LIST</p>--characters=CHARACTER-LIST | Print only characters in positions listed in CHARACTER-LIST. The </p>same as `-b' for now, but internationalization will change that. </p>Tabs and backspaces are treated like any other character; they </p>take up 1 character. |
| -f FIELD-LIST</p>--fields=FIELD-LIST | Print only the fields listed in FIELD-LIST. Fields are separated </p>by a TAB character by default. |
| -d INPUT_DELIM_BYTE</p>--delimiter=INPUT_DELIM_BYTE | For `-f', fields are separated in the input by the first character </p>in INPUT_DELIM_BYTE (default is TAB). |
| -n | Do not split multi-byte characters (no-op for now). |
| -s</p>--only-delimited | For `-f', do not print lines that do not contain the field </p>separator character. |
| --output-delimiter=OUTPUT_DELIM_STRING | For `-f', output fields are separated by OUTPUT_DELIM_STRING The </p>default is to use the input delimiter. |

* Examples
```bash
# split the variable by "_" and get the first element
cut -d"_" -f1 <<< "$variable"
```

## [head](https://ss64.com/bash/head.html) and [tail](https://ss64.com/bash/tail.html)
* head

`Output the first part of files, prints the first part (10 lines by default) of each file.`

```bash
head [options]... [file]...
```


| Options | Description | Examples |
| --- | --- | --- |
| -*NUMBER* | Return the first *NUMBER* of lines from the file. (must be the first option specified) | `head -85 file.txt` |
| -*Count*Options | This option is only recognized if it is specified first. Count is a decimal number optionally followed by a size letter ('b', 'k', 'm' for bytes, Kilobytes or Megabytes) , or 'l' to mean count by lines, or other option letters ('cqv'). | |
| -c BYTES</p>--bytes=BYTES | Print the first BYTES bytes, instead of initial lines.  Appending 'b' multiplies BYTES by 512, 'k' by 1024, and 'm' by 1048576. | |
| -n N</p>--lines=N | Output the first N lines. | |
| -q</p>--quiet</p>--silent | Never print file name headers. | |
| -v</p>--verbose | Always print file name headers. | |

* tail

`Output the last part of files, print the last part (10 lines by default) of each FILE; 
tail reads from standard input if no files are given or when given a FILE of -
`

```bash
tail [options]... [file]...
```


| Options | Description | Examples |
| --- | --- | --- |
| -c</p>--bytes=K | Output the last K bytes; alternatively, use -c +K to output bytes starting with the Kth of each file. |  |
| -f</p>--follow[={name descriptor}] | Output appended data as the file grows; -f, --follow, and --follow=descriptor are equivalent | `tail -f /var/log/wifi.log` |
| -F | Same as --follow=name --retry | `tail -F /var/log/wifi.log` |
| -n, --lines=K | Output the last K lines, instead of the last 10; or use -n +K to output lines starting with the Kth | `tail -85 file.txt` |
| --max-unchanged-stats=N | With --follow=name, reopen a FILE which has not changed size after N (default 5) iterations </p>to see if it has been unlinked or renamed (this is the usual case of rotated log files). </p>With inotify, this option is rarely useful.|  |
| --pid=PID | With -f, terminate after process ID, PID dies |  |
| -q, --quiet, --silent | Never output headers giving file names |  |
| --retry | Keep trying to open a file even when it is or becomes inaccessible; </p>useful when following by name, i.e., with --follow=name|  |
| -s, --sleep-interval=N | With -f, sleep for approximately N seconds (default 1.0) between iterations. </p>With inotify and --pid=P, check process P at least once every N seconds. |  |
| -v, --verbose | Always output headers giving file names |  |
| --help | Display this help and exit |  |
| --version | Output version information and exit|  |

* Example
```bash
# Extract the first 85 lines from a file:
head -85 file.txt

# Extract lines 40-50 from a file, first using head to get the first 50 lines then tail to get the last 10
head -50 file.txt | tail -10

# Extract the last 85 lines from a file:
tail -85 file,txt

# Output the newly appended lines of a file instantly:
tail -f /var/log/wifi.log

# Output newly appended lines, and keep trying if the file is temporarily inaccessible:
tail -f /var/log/wifi.log --retry
# or
tail -F /var/log/wifi.log

# Extract lines 40-50 from a file, first using head to get the first 50 lines then tail to get the last 10:
head -50 file.txt | tail -10
```

## work around with temp file
```bash
#--------------------------------------------------------------------------
# Cleanup temporary file in case of keyboard interrupt or termination signal.
#--------------------------------------------------------------------------
function cleanup_temp {
    [ -e $tmpfile ] && rm --force $tmpfile
    exit 0
}
trap cleanup_temp SIGHUP SIGINT SIGPIPE SIGTERM

tmpfile=$(mktemp) || { echo "$0: creation of temporary file failed!"; exit 1; }

# ... use tmpfile ...

rm --force $tmpfile
```

## filename and dirname althernatives
```
for pathname in $(find $search -type f -name "*" -print)
do
    basename=${pathname##*/}   # replaces basename "$pathname"
    dirname=${pathname%/*}     # replaces dirname "$pathname"
# .... 
done
```

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
date -d "$DATE_VARIABLE - 1 day" 				# 1 day before DATE_VARIABLE
date -d "$DATE_VARIABLE 1 day ago" 	       # 1 day before DATE_VARIABLE
date -d 1may '+%B %d' 			     # May 01

last_sunday=$(date -d "last sunday" -I)
sunday_before_last_sunday=$(date -d "$last_sunday - $(date -d $last_sunday +%u) days" -I)
```

4. Used in shell script:

```bash
`date +%Y-%m-%d:%H:%M:%S`
$(date +%Y-%m-%d:%H:%M:%S)
```

5. using pipe to pass date value to `date`:

```bash
echo  "Oct 10" | { read dt ; date -d "$dt" ; }
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


## String Manipulation
### lower case and UPPER CASE

```bash
var="aBc"
echo ${var^^} # ABC
echo ${var,,} # abc
```

### Pattern matching
```bash
metacharacter=’[~&|]’
if [[ "$variable_name" =~ $metacharacter ]]
then
    # treat metacharacter
fi
```

### Array
http://tldp.org/LDP/Bash-Beginners-Guide/html/sect_10_02.html

#### Create an array
```bash
AN_ARRAY=(
a
b
c
d
e
)
```

#### Print an array to screen
```bash
echo ${AN_ARRAY[0]}		# the 1st item in the array
echo ${AN_ARRAY[*]} 		# All of the items in the array
echo ${!AN_ARRAY[*]}            # All of the indexes in the array
echo ${#AN_ARRAY[*]}            # Number of items in the array
echo ${#AN_ARRAY[0]}            # Length of item zero
```

#### Loop through all elements in an array
```bash
array=(one two three four [5]=five)

echo "Array size: ${#array[*]}"
# Array size: 5

echo "Array items:"
for item in ${array[*]}
do
    printf "   %s\n" $item
done
# Array items:
#   one
#   two
#   three
#   four
#   five

echo "Array indexes:"
for index in ${!array[*]}
do
    printf "   %d\n" $index
done
# Array indexes:
#    0
#    1
#    2
#    3
#    5

echo "Array items and indexes:"
for index in ${!array[*]}
do
    printf "%4d: %s\n" $index ${array[$index]}
done
# Array items and indexes:
#    0: one
#    1: two
#    2: three
#    3: four
#    5: five
```
#### Convert array to string
```bash
ids=(1 2 3 4);id="${ids[@]}";echo ${id// /|}
1|2|3|4
```

#### @ vs *
Note that the "@" sign can be used instead of the "*" in constructs such as ${arr[*]}, the result is the same except when expanding to the items of the array within a quoted string. In this case the behavior is the same as when expanding "$*" and "$@" within quoted strings: "${arr[*]}" returns all the items as a single word, whereas "${arr[@]}" returns each item as a separate word.

The following example shows how unquoted, quoted "*", and quoted "@" affect the expansion (particularly important when the array items themselves contain spaces):
```bash
#!/bin/bash

array=("first item" "second item" "third" "item")

echo "Number of items in original array: ${#array[*]}"
for ix in ${!array[*]}
do
    printf "   %s\n" "${array[$ix]}"
done
echo
# Number of items in original array: 4
#    first item
#    second item
#    third
#    item

arr=(${array[*]})
echo "After unquoted expansion: ${#arr[*]}"
for ix in ${!arr[*]}
do
    printf "   %s\n" "${arr[$ix]}"
done
echo
# After unquoted expansion: 6
#    first
#    item
#    second
#    item
#    third
#    item

arr=("${array[*]}")
echo "After * quoted expansion: ${#arr[*]}"
for ix in ${!arr[*]}
do
    printf "   %s\n" "${arr[$ix]}"
done
echo
# After * quoted expansion: 1
#    first item second item third item

arr=("${array[@]}")
echo "After @ quoted expansion: ${#arr[*]}"
for ix in ${!arr[*]}
do
    printf "   %s\n" "${arr[$ix]}"
done
# After @ quoted expansion: 4
#    first item
#    second item
#    third
#    item
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
### Using case to set up different environments
```
HOST_NAME=$(hostname)
PROD_HOSTNAME='prod_host'
QA_HOSTNAME='qa_host'
DEV_HOSTNAME='dev_host'
EXP_HOSTNAME='exp_host'

case "$HOST_NAME" in
    *${PROD_HOSTNAME}* )
        env="PROD"
        echo "Running in $env environment :: $HOST_NAME"
        ;;
    *${DEV_HOSTNAME}* )
        env="DEV"
        echo "Running in $env environment :: $HOST_NAME"
        ;;
    *${QA_HOSTNAME}* )
        env="QA"
        echo "Running in $env environment :: $HOST_NAME"
        ;;
    *${EXP_HOSTNAME}* )
        env="EXP"
        echo "Running in $env environment :: $HOST_NAME"
        ;;
    *)
        echo "No environment selected ::::::::::::::::::: Process aborted :::::::::::::::::"
        exit 1
        ;;
esac

if [ "${HOST_NAME/$PROD_HOSTNAME}" = "$HOST_NAME" ]; then 
	echo NO
else 
	echo YES
fi
```


## IF
### [Compare Ops](http://tldp.org/LDP/abs/html/comparison-ops.html)
#### [Check string containing substring](https://askubuntu.com/questions/299710/how-to-determine-if-a-string-is-a-substring-of-another-in-bash)
```
my_string=abc
substring=ab
if [ "${my_string/$substring}" = "$my_string" ] ; then
  echo "${substring} is not in ${my_string}"
else
  echo "${substring} was found in ${my_string}"
fi

This works because ${VAR/subs} is equal to $VAR but with the first occurrence of the string subs removed, in particular if $VAR does not contains the word subs it won't be modified.
```

### && and ||
#### Within IF
* && AND

```bash
if [ $condition1 ] && [ $condition2 ]
#  Same as:  if [ $condition1 -a $condition2 ]
#  Returns true if both condition1 and condition2 hold true...

if [[ $condition1 && $condition2 ]]    # Also works.
# Note that && operator not permitted inside brackets
# of [ ... ] construct.
```

* || OR

```bash
if [ $condition1 ] || [ $condition2 ]
# Same as:  if [ $condition1 -o $condition2 ]
# Returns true if either condition1 or condition2 holds true...

if [[ $condition1 || $condition2 ]]    # Also works.
# Note that || operator not permitted inside brackets
# of a [ ... ] construct.
```

#### Without IF
* The right side of && will only be evaluated if the exit status of the left side is zero. || is the opposite: it will evaluate the right side only if the left side exit status is nonzero. You can consider [ ... ] to be a program with a return value. If the test inside evaluates to true, it returns zero; it returns nonzero otherwise.

```bash
$ false && echo howdy!

$ true && echo howdy!
howdy!
$ true || echo howdy!

$ false || echo howdy!
howdy!

$ [ -f <filename.extension> ] && echo "file found" || echo "file not found"
$ [[ -f <filename.extension> ]] && echo "file found" || echo "file not found"

```

#### Others
* "A ; B" Run A and then B, regardless of success of A
* "A && B" Run B if A succeeded
* "A || B" Run B if A failed
* "A &" Run A in background.

### Compare date
* You can compare [lexicographically](https://en.wikipedia.org/wiki/Lexicographical_order) with the [conditional construct](https://www.gnu.org/software/bash/manual/bashref.html#Conditional-Constructs) `[[ ]]` in this way:

```bash
[[ "2014-12-01T21:34:03+02:00" < "2014-12-01T21:35:03+02:00" ]] && echo "smaller"

date1=2013-07-18
date2=2013-07-15

if [[ "$date1" > "$date2" ]] ; then
    echo "break"
fi
```

```bash
date1=$(date -d 2013-07-18 +"%Y%m%d")    # = 20130718
date2=$(date -d 2013-07-15 +"%Y%m%d")    # = 20130715

date1=$(date -d 2013-07-18 +%s)
date2=$(date -d 2014-08-19 +%s)

if [ $date1 -ge $date2 ]; then
 echo 'yes';
fi
```

## Loop
### while
#### loop through dates
```
# slightly malformed input data
input_start=2018-09-23
input_end=2018-09-26

# After this, startdate and enddate will be valid ISO 8601 dates,
# or the script will have aborted when it encountered unparseable data
# such as input_end=abcd
START_DATE=$(date -I -d "$input_start") || exit -1
END_DATE=$(date -I -d "$input_end")     || exit -1

d=$START_DATE
while [ $(date -d "$d" +%Y%m%d) -lt $(date -d "$END_DATE" +%Y%m%d)  ]; do
    echo $d
    d=$(date -d "$d + 1 day" -I)
done
```




