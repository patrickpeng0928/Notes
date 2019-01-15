package com.package

import java.text.SimpleDateFormat
import java.util.Date

import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._

/**
  * IO function with HDFS
  */
object HDFSFileSystemHandler {

  def readJsonFiles(
                     spark: SparkSession
                     , path: String
                   ): DataFrame = {
    println("Start to read json format: " + path)
    val df = spark.read.json(path)
    println("Complete to read json format: " + path)
    return df
  }

  def readParquetFiles(
                        spark: SparkSession
                        , path: String
                      ): DataFrame = {
    println("Start to read parquet format: " + path)
    val df = spark.read.parquet(path)
    println("Complete to read parquet format: " + path)
    return df
  }

  def readTextFiles(
                     spark: SparkSession
                     , path: String
                   ): DataFrame = {
    println("Start to read text format: " + path)
    val df = spark.read.text(path)
    println("Start to read text format: " + path)
    return df
  }

  def readCSVFiles(
                    spark: SparkSession
                    , path: String
                  ): DataFrame = {
    println("Start to read csv format: " + path)
    val df = spark.read.csv(path)
    println("Complete to read csv format: " + path)
    return df
  }

  def readCSVFilesWithHeader(
                              spark: SparkSession
                              , path: String
                            ): DataFrame = {
    println("Start to read csv format with header: " + path)
    val df = spark.read.option("header", "true").csv(path)
    println("Complete to read csv format with header: " + path)
    return df
  }

  def readFiles(
                 spark: SparkSession
                 , path: String
                 , format: String
               ): DataFrame = {
    println("Start to read from HDFS: " + path)
    val df = format.toLowerCase match {
      case "json"     => readJsonFiles(spark, path)
      case "parquet"  => readParquetFiles(spark, path)
      case "text"     => readTextFiles(spark, path)
      case "csv"      => readCSVFiles(spark, path)
      case "csv2"     => readCSVFilesWithHeader(spark, path)
      case _          => spark.read.format(format).load(path)
    }
    println("Complete to read from HDFS: " + path)
    return df
  }

  def input_parquet_file_list(
                       spark: SparkSession
                       , path: String
                       , start_date: String
                       , end_date: String
                     ): Array[String] = {
    val fs = FileSystem.get(spark.sparkContext.hadoopConfiguration)
    val sub_folder_status = fs.listStatus(new Path(path))
    val file_list = sub_folder_status
      .map(x => x.getPath.toString)
      .filter(
        x   =>  (start_date, end_date) match {
          case ("", "")   => true
          case (_, "")    => x.split("=").last >= start_date
          case ("", _)    => x.split("=").last < end_date
            case(_, _)    => x.split("=").last >= start_date && x.split("=").last < end_date
        }
      ).map(x => x + "/part*")
    return file_list
  }

  def readPartitionedParquetFiles(
                                   spark: SparkSession
                                   , path: String
                                   , start_date: String
                                   , end_date: String
                                 ): DataFrame = {
    val file_list = input_parquet_file_list(spark, path, start_date, end_date)
    file_list.foreach(println)
    val df = spark.read.parquet(file_list: _*)
    return df
  }

  def input_json_file_list(
                               spark: SparkSession
                               , path: String
                               , start_date: String
                               , end_date: String
                             ): Array[String] = {
    val fs = FileSystem.get(spark.sparkContext.hadoopConfiguration)
    val sub_folder_status = fs.listStatus(new Path(path))
    val file_list = sub_folder_status
      .map(x => x.getPath.toString)
      .filter(
        x   =>  (start_date, end_date) match {
          case ("", "")   => true
          case (_, "")    => x.split("/").last >= start_date
          case ("", _)    => x.split("/").last < end_date
          case(_, _)      => x.split("/").last >= start_date && x.split("=").last < end_date
        }
      ).map(x => x + "/part*")
    return file_list
  }

  def readMergedJSONFiles(
                           spark: SparkSession
                           , path: String
                           , start_date: String
                           , end_date: String
                         ): DataFrame = {
    val file_list = input_json_file_list(spark, path, start_date, end_date)
    val df = spark.read.json(file_list: _*)
    return df
  }

  def readPartitionedFiles(
                          spark: SparkSession
                          , path: String
                          , format: String
                          , start_date: String
                          , end_date: String
                          ): DataFrame = {
    println(s"Start to read data in $format from $path between $start_date and $end_date")
    val df = format.toLowerCase match {
      case "parquet"        => readPartitionedParquetFiles(spark, path, start_date, end_date)
      case "json_merged"    => readMergedJSONFiles(spark, path, start_date, end_date)
      case "json"           => spark.read.json(path)
      case _                => spark.read.format(format).load(path)
    }
    println(s"Completed to read data in $format from $path between $start_date and $end_date")
    return df
  }

  def writeToCSV(
                  df: DataFrame
                  , out_path: String
                ): Unit = {
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")
    val currentDatetime = dateFormat.format(new Date())
    df.coalesce(1).write.option("header", "true").csv(out_path + "/" + currentDatetime)
  }

  def writeToParquet(
                      df: DataFrame
                      , out_path: String
                    ): Unit = {
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")
    val currentDatetime = dateFormat.format(new Date())
    df.coalesce(1).write.option("header", "true").parquet(out_path + "/" + currentDatetime)
  }

  def writeToText(
                   df: DataFrame
                   , out_path: String
                 ): Unit = {
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")
    val currentDatetime = dateFormat.format(new Date())
    df.coalesce(1).write.text(out_path + "/" + currentDatetime + ".txt")
  }

  def writeToHDFS(
                   df: DataFrame
                   , path: String
                   , format: String
                 ): Unit = {
    println("Writing to HDFS: " + path)
    format.toLowerCase match {
      case "parquet"  => writeToParquet(df, path)
      case "text"     => writeToText(df, path)
      case "csv"      => writeToCSV(df, path)
      case _          => df.coalesce(1).write.format(format.toLowerCase).save(path)
    }
  }

  def writeToHDFSPartitioned(
                             df: DataFrame
                             , path: String
                             , format: String
                             , partition_col: String
                 ): Unit = {
    println("Writing to HDFS: " + path)
    format.toLowerCase match {
      case "parquet"  => writeToParquetPartitioned(df, path, partition_col)
      case _          => df.coalesce(1).write.format(format.toLowerCase).save(path)
    }
  }

  def writeToParquetPartitioned(
                                df: DataFrame
                                , out_path: String
                                , partition_col: String
                    ): Unit = {
//    df.withColumn("sending_date", from_unixtime(col(partition_col) / 1000, "yyyy-MM-dd")).coalesce(1).write.mode("append").partitionBy("sending_date").option("header", "true").parquet(out_path)
    df.withColumn("sending_date", from_unixtime(col(partition_col) / 1000, "yyyy-MM-dd")).repartition(100).write.mode("append").partitionBy("sending_date").option("header", "true").parquet(out_path)
  }

}
 
