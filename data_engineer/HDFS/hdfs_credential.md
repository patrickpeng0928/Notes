# HDFS Credential API
## HDFS Command Interface

* take a backup of the existing jceks file //hdfs/path/to/password.jceks
	or the password jceks file that you are going to add to
```
hadoop fs -cp /hdfs/EDL/parameters/db_credential/edl.password.jceks //hdfs/EDL/parameters/db_credential/edl.password.jceks.11272018_bkp
```
* List all password alias: 
```
hadoop credential list -provider jceks://hdfs/EDL/parameters/db_credential/edl.password.jceks
```
* To add the new alias ( pwd.alias as example), it will ask for a password in command line input: 
```
hadoop credential create pwd.alias -provider jceks://hdfs/path/to/password.jceks
```

## Spark/Scala Call
