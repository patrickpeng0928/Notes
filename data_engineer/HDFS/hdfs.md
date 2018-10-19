# HDFS common commands

## Merge files
```
hdfs dfs -text /path/to/files | hdfs dfs -put - /path/to/output/filename.ext
```
