# Command Line Options

## Functions
### shift
**shift** is a shell builtin that operates on the positional parameters. Each time you invoke **shift**, it "shifts" all the positional parameters down by one. $2 becomes $1, $3 becomes $2, $4 becomes $3, and so on.

## Example code
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
