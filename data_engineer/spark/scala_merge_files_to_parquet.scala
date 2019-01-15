import org.apache.spark.sql.Row;
import org.apache.spark.sql.types._
import org.apache.hadoop.fs._
import sqlContext.implicits._
import scala.collection.mutable._

//specifying the custom record delimiter for the files:
sc.hadoopConfiguration.set("textinputformat.record.delimiter","<delimiter>")

//assuming that field delimiter is stored in field_delim variable;

//a helper function get a list of files from the specified HDFS path;
def get_hdfs_files(given_path:String):ArrayBuffer[String]={
  val fs=FileSystem.get(sc.hadoopConfiguration)
  val flist=fs.listFiles(new Path(given_path),true)
  val arr_buff=new ArrayBuffer[String]
  while (flist.hasNext){
    arr_buff+=flist.next.getPath.toString
  }
  arr_buff
}

/*a helper function that generates a mutable ArrayBuffer which consists of DataFrames corresponding to each RDD of Files. 
*it accepts an array buffer of String and schema for constructing Dataframes out of those RDDs.
*/
def data_frames_buffer_generator(given_flist:ArrayBuffer[String],given_schema:org.apache.spark.sql.types.StructType):ArrayBuffer[org.apache.spark.sql.DataFrame]={
val arr_buff=new ArrayBuffer[org.apache.spark.sql.DataFrame]
for (efile<-given_flist){
  val rdd_x=sc.textFile(efile)
  val df=sqlContext.createDataFrame(rdd_x,given_schema)
    arr_buff+=df
  }
  arr_buff //returning the array buffer whose each element represents dataframe for corresponding RDD.
}

//specifying schema of the dataframe:
val my_schema=StructType(List(StructField("id",IntegerType,false),StructField("name",StringType,false),StructField("location",StringType,false)))

//getting list of files in the HDFS directory:
val hdfs_flist=get_hdfs_files("/user/Cloudera/ie_stuff/spark/exp1")

//generating mutable array buffer of spark sql dataframes from the list of files in the hdfs path:
val df_buffer=data_frames_buffer_generator(hdfs_flist,my_schema)

//generating empty dataframe for subsequent processing:
var temp_df=sqlContext.createDataFrame(sc.emptyRDD[Row],my_schema)

//Iterating through each dataframe in the returned mutable ArrayBuffer and generating union of all dataframes in the buffer:
for (each_df<-df_buffer){
temp_df=temp_df.unionAll(each_df)
}

//here temp_df represents a dataframe consisting of Union of RDDs
//writing as Parquet file:
temp_df.saveAsParquetFiles("/user/Cloudera/ie_stuff/impala/exp2")
