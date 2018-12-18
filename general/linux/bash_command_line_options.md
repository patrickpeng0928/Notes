# Command Line Options

## Resources
1. http://linuxcommand.org/lc3_wss0120.php
2. https://stackoverflow.com/questions/192249/how-do-i-parse-command-line-arguments-in-bash

## Bash Space-Separated (e.g., --option argument) (without getopt[s])
### Usage   
```
./myscript.sh -e conf -s /etc -l /usr/lib /etc/hosts 
```

### shift
**shift** is a shell builtin that operates on the positional parameters. Each time you invoke **shift**, it "shifts" all the positional parameters down by one. $2 becomes $1, $3 becomes $2, $4 becomes $3, and so on.

### Example code
```
#!/bin/bash
interactive=
filename=~/sysinfo_page.html

while [ "$1" != "" ]; do
    case $1 in
        -f | --file )           shift
                                filename=$1
                                ;;
        -i | --interactive )    interactive=1
                                ;;
        -h | --help )           usage
                                exit
                                ;;
        * )                     usage
                                exit 1
    esac
    shift
done
```

## Bash Equals-Separated (e.g., --option=argument) (without getopt[s])
### Usage 
```
./myscript.sh -e=conf -s=/etc -l=/usr/lib /etc/hosts
```

### Substring
To better understand `${i#*=}` search for "Substring Removal" in [this guide](http://tldp.org/LDP/abs/html/string-manipulation.html). It is functionally equivalent to `sed 's/[^=]*=//' <<< "$i"` which calls a needless subprocess or `echo "$i" | sed 's/[^=]*=//'` which calls two needless subprocesses.

### Example code
```
#!/bin/bash

for i in "$@"
do
case $i in
    -e=*|--extension=*)
    EXTENSION="${i#*=}"
    shift # past argument=value
    ;;
    -s=*|--searchpath=*)
    SEARCHPATH="${i#*=}"
    shift # past argument=value
    ;;
    -l=*|--lib=*)
    LIBPATH="${i#*=}"
    shift # past argument=value
    ;;
    --default)
    DEFAULT=YES
    shift # past argument with no value
    ;;
    *)
          # unknown option
    ;;
esac
done
echo "FILE EXTENSION  = ${EXTENSION}"
echo "SEARCH PATH     = ${SEARCHPATH}"
echo "LIBRARY PATH    = ${LIBPATH}"
echo "Number files in SEARCH PATH with EXTENSION:" $(ls -1 "${SEARCHPATH}"/*."${EXTENSION}" | wc -l)
if [[ -n $1 ]]; then
    echo "Last line of file specified as non-opt/last argument:"
    tail -1 $1
fi
```

## Using bash with getopt[s]
### Tutorial
http://mywiki.wooledge.org/BashFAQ/035#getopts

[getopts tutorial](http://wiki.bash-hackers.org/howto/getopts_tutorial)

`help getopts`

### Explanation
#### Advantages

* It's more portable, and will work in other shells like dash.
* It can handle multiple single options like -vf filename in the typical Unix way, automatically.

#### Disadvantage
The disadvantage of getopts is that it can only handle short options (`-h`, not `--help`) without additional code.

getopt(1) limitations (older, relatively-recent getopt versions):

* can't handle arguments that are empty strings
* can't handle arguments with embedded whitespace
    
More recent getopt versions don't have these limitations.

Additionally, the POSIX shell (and others) offer getopts which doesn't have these limitations.

### Example code
```
#!/bin/sh
# A POSIX variable
OPTIND=1         # Reset in case getopts has been used previously in the shell.

# Initialize our own variables:
output_file=""
verbose=0

while getopts "h?vf:" opt; do
    case "$opt" in
    h|\?)
        show_help
        exit 0
        ;;
    v)  verbose=1
        ;;
    f)  output_file=$OPTARG
        ;;
    esac
done

shift $((OPTIND-1))

[ "${1:-}" = "--" ] && shift

echo "verbose=$verbose, output_file='$output_file', Leftovers: $@"

# End of file
```

```
#!/bin/sh
for arg in "$@"
do
   key=$(echo $arg | cut -f1 -d=)`
   value=$(echo $arg | cut -f2 -d=)`
   case "$key" in
        name|-name)      read_name=$value;;
        id|-id)          read_id=$value;;
        *)               echo "I dont know what to do with this"
   ease
done
```
