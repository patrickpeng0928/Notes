# HDFS IO in pyspark
## Spark 2.4


## Spark 1.6.3
### Import and settings
```python
from pyspark import SparkContext

sc            = SparkContext()
URI           = sc._gateway.jvm.java.net.URI
Path          = sc._gateway.jvm.org.apache.hadoop.fs.Path
FileSystem    = sc._gateway.jvm.org.apache.hadoop.fs.FileSystem
Configuration = sc._gateway.jvm.org.apache.hadoop.conf.Configuration
```

### FileSystme
```python
fs = FileSystem.get(URI(""), Configuration())
status = fs.listStatus(Path(path))
fullFileListInPath = [ s.getPath().toString() for s in status ]
```
