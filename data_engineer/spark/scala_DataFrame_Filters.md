# DataFrame Filters in Scala
## Filter data by dates
```scala
/**
  * Filter data by start date and end date(exclusive)
  * @param df               source, DataFrame
  * @param ts_col           "time", timestamp column
  * @param start_date       start date, String in yyyy-MM-dd format
  * @param end_date         end date, String in yyyy-MM-dd format
  * @return                 DataFrame
  */
def filter_data_by_date_range(
                       df: DataFrame
                       , ts_col: String
                       , start_date: String
                       , end_date: String
                       ): DataFrame = {
  println(s"Start to filter data from $start_date to $end_date ...")
  val dateFormat = new SimpleDateFormat("yyyy-MM-dd")
  val start = dateFormat.parse(start_date).getTime
  val end = dateFormat.parse(end_date).getTime
  val dff = df.filter(col(ts_col) >= start && col(ts_col) < end)
  println("Complete to filter data")
  return dff
}

/**
  * Filter data from start date to current date
  * @param df               source, DataFrame
  * @param ts_col           "time", timestamp column
  * @param start_date       start date, String in yyyy-MM-dd format
  * @return                 DataFrame
  */
def filter_data_by_start_date(
                               df: DataFrame
                               , ts_col: String
                               , start_date: String
                             ): DataFrame = {
  println(s"Start to filter data from $start_date ...")
  val dateFormat = new SimpleDateFormat("yyyy-MM-dd")
  val start = dateFormat.parse(start_date).getTime
  val dff = df.filter(col(ts_col) >= start)
  println("Complete to filter data")
  return dff
}

/**
  * Filter data from teh earliest date to a certain date
  * @param df               source, DataFrame
  * @param ts_col           "time", timestamp column
  * @param end_date         end date, String in yyyy-MM-dd format
  * @return                 DataFrame
  */
def filter_data_by_end_date (
                               df: DataFrame
                               , ts_col: String
                               , end_date: String
                             ): DataFrame = {
  println(s"Start to filter data on '$ts_col' column before $end_date ...")
  val dateFormat = new SimpleDateFormat("yyyy-MM-dd")
  val end = dateFormat.parse(end_date).getTime
  val dff = df.filter(col(ts_col) < end)
  println("Complete to filter data")
  return dff
}

/**
  * Filter data by start date and end date(exclusive)
  * @param df               source, DataFrame
  * @param ts_col           "time", timestamp column
  * @param start_date       start date, String in yyyy-MM-dd format or ""
  * @param end_date         end date, String in yyyy-MM-dd format or ""
  * @return                 DataFrame
  */
def filter_data_by_date(
                       df: DataFrame
                       , ts_col: String
                       , start_date: String
                       , end_date: String
                       ): DataFrame = {
  val dff = (start_date, end_date) match {
    case ("", "")     => df
    case (_, "")      => DataHandler.filter_data_by_start_date(df, ts_col, start_date)
    case ("", _)      => DataHandler.filter_data_by_end_date(df, ts_col, end_date)
    case (_, _)       => DataHandler.filter_data_by_date_range(df, ts_col, start_date, end_date)
  }
  return dff
}

## Filter data by conditions
```scala
/**
  * Filter data by some conditions (and)
  * @param df               source, DataFrame
  * @param map              a list of conditions, column_name -> value
  * @return                 DataFrame
  */
def filter_data_by_and_conditions(
                            df : DataFrame
                          , map: Map[String, Any]
               ): DataFrame = {
  println("Start to filter data ...")
  var dff = df
  val map_list = map.toList
  dff = map_list.foldLeft(dff)((dff, condition) => dff.filter(col(condition._1) === condition._2))
  println("Complete to filter data")
  return dff
}

/**
  * Filter data by some conditions (or), using union to combine all conditions
  * @param df               source, DataFrame
  * @param map              a list of conditions, column_name -> value
  * @return                 DataFrame
  */
def filter_data_by_or_conditions(
                                   df : DataFrame
                                   , map: Map[String, Any]
                                 ): DataFrame = {
  println("Start to filter data ...")
  val spark = SparkSession.builder().getOrCreate()
  val dff = spark.createDataFrame(spark.sparkContext.emptyRDD[Row], df.schema)
  val map_list = map.toList
  val result_df = map_list.foldLeft(dff)((dff, condition) => dff.union(df.filter(col(condition._1) === condition._2)))
  println("Complete to filter data")
  return result_df
}

/**
  * Filter data by a condition
  * @param df               source, DataFrame
  * @param condition        a condition, column_name -> value
  * @return                 DataFrame
  */
def filter_data_by_condition(
                               df: DataFrame
                               , condition: String
                             ): DataFrame = {
  // TODO: figure out condition used here
  return df.filter(condition)
}
```

## select columns
```scala
/**
  * Select columns from a DataFrame
  * @param df               source, DataFrame
  * @param col_names        a list of column names, List[String]
  * @return                 DataFrame
  */
def selectColumns(
                 df: DataFrame
                 , col_names: List[String]
                 ): DataFrame = {
  return df.select(col_names.head, col_names.tail: _*)
}

/**
  * Check if a column exists in a DataFrame
  * @param df               source, DataFrame
  * @param col_name         column name, String
  * @return                 Boolean
  */
def hasColumn(
               df: DataFrame
               , col_name: String
             ): Boolean = {
  return Try(df(col_name)).isSuccess
}

/**
  * Select columns existing in a DataFrame
  * @param df               source, DataFrame
  * @param col_names        a list of column names, List[String]
  * @return                 DataFrame
  */
def selectExistingColumns(
                               df: DataFrame
                               , col_names: List[String]
                             ): DataFrame = {

  val valid_cols: immutable.Seq[Column] = col_names.flatMap(c => Try(df(c)).toOption)

  return df.select(valid_cols:_*)
}

/**
  * UDF: return null
  */
val getNull: UserDefinedFunction = udf(() => None: Option[String])

/**
  * Select columns from a DataFrame
  * if the column doesn't exist in the DataFrame, fill it will null
  * @param df               source, DataFrame
  * @param col_names        a list of column names, List[String]
  * @return                 DataFrame
  */
def selectColumnsFillMissingColNull(
                                     df: DataFrame
                                     , col_names: List[String]
                                   ): DataFrame = {
  val existing_cols_df = selectExistingColumns(df, col_names)
  val result_df = col_names.foldLeft(existing_cols_df)((dff: DataFrame, col: String) => hasColumn(df, col) match {
    case true     => dff
    case false    => dff.withColumn(col, getNull())
  })

  return result_df
}
```
