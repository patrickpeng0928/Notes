# AWK
`**awk**: a field-oriented pattern processing language with a **C**-style syntax`

https://likegeeks.com/awk-command/

## Examples
```bash
# get the modified date of the latest file
ls -lrt $SAS_GRID_INPUT" | tail -1 | awk '{print $6 " " $7}' | { read $dt ; date -d "$dt" -I ; }
```
