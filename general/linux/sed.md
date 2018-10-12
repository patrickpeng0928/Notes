# Sed

## Examples
```bash
# get the 2nd row from stdout
ls -l | sed -n 2p
ls -l | head -2 | tail -1
ls -l | tail -n +2 | head -n 1

# get a range of rows
ls -l | sed -n 2,4p

# For several ranges of lines:
ls -l | sed -n -e 2,4p -e 20,30p
ls -l | sed -n -e '2,4p;20,30p'
```
