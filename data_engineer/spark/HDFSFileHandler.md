# HDFS IO on Spark Scala

## import
```scala
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FSDataOutputStream, FileSystem, Path}
```

## HDFS Configuration
```scala
/**
  * create a file system pointing to hdfs
  *
  * @return FileSystem        : org.apache.hadoop.fs.FileSystem
  */
def configureHDFS(): FileSystem = {
  val fs: FileSystem = try {
    val conf: Configuration = new Configuration()
    conf.setBoolean("dfs.support.append", true)
    FileSystem.get(conf)
  } catch  {
    case e: Exception =>
      println(s"Error occurred while configuring FileSystem: ${e.printStackTrace()}")
      sys.exit(1)
  }
  return fs
}

```

## Append HDFS files
```scala
/**
  * append content to an existing HDFS file
  * @param content
  * @param filePath
  */
def appendHDFSFile(
                    content: String
                    , filePath: String
                  ): Unit = {
  val outputPath: Path = new Path(filePath)
  val fs: FileSystem = configureHDFS()
  val outputStream: FSDataOutputStream = if (!fs.exists(outputPath)) {
    fs.create(outputPath)
  } else {
    val isAppendable = fs.getConf.get("dfs.support.append").toBoolean
    if (isAppendable) {
      fs.append(outputPath)
    } else {
      println("Please set the dfs.support.append property to true")
      sys.exit(1)
    }
  }
  outputStream.writeUTF(content)
  outputStream.close()
}
```
