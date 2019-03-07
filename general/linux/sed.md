# Sed
`**sed**: a non-interactive text file editor`

## Examples
### get the 2nd row from stdout
```bash
ls -l | sed -n 2p
ls -l | head -2 | tail -1
ls -l | tail -n +2 | head -n 1
```
### get a range of rows
```
ls -l | sed -n 2,4p
```

### For several ranges of lines:
```
ls -l | sed -n -e 2,4p -e 20,30p
ls -l | sed -n -e '2,4p;20,30p'
```
### remove the last part of each line, and get unique lines
### /a/b/c/part- => /a/b/c
```
cat ${file} | sed 's/\/part-.*$//' | sort | uniq
```
