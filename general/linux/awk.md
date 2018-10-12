# AWK
`**awk**: a field-oriented pattern processing language with a **C**-style syntax`

https://likegeeks.com/awk-command/

## Examples
```bash
# get the modified date of the latest file
ls -lrt $path | tail -1 | awk '{print $6 " " $7}' | { read $dt ; date -d "$dt" -I ; }
hdfs dfs -ls $path | awk '{FILEDATE=$6;print FILEDATE}' | tail -1
```
