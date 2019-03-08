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

## Check corrupted file
### resources:
1. http://fibrevillage.com/storage/658-how-to-use-hdfs-fsck-command-to-identify-corrupted-files

```
hdfs fsck hdfs://path > ${file}
cat ${file} | egrep -v '^\.+$' | grep -i corrupt
cat ${file} | egrep -v '^\.+$' | grep -v replica | grep -v Replica
# best
cat ${file} | egrep -v '^\.+$' | grep -i "corrupt blockpool" | sed 's/\/part-.*$//g' | sort | uniq > ${log_file}
```
