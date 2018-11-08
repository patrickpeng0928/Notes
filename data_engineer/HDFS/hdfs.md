# HDFS common commands

## Merge files from hdfs to hdfs
```
hdfs dfs -text /path/to/files | hdfs dfs -put - /path/to/output/filename.ext
```

## Merge files from hdfs to local
```
hdfs dfs -getmerge -nl <source file path> <local system destination path>
```

## Create an empty file
```
hdfs dfs -touchz /path/to/files
```

## Check node
```
hdfs fsck hdfs://path
```
