# DataFrame Transformations in Scala
## add an index
```scala
/**
  * add an index column to df
  * @param target_col        String, name of target column
  * @param df                DataFrame
  * @return                  DataFrame
  */
def addIndex(
               target_col: String
             )(
               df: DataFrame
             ): DataFrame = {
  df.withColumn(
    target_col
    , monotonically_increasing_id
  )
}
```
## Add columns
```scala
  /**
    * add a new column of value to df
    * @param target_col        String, name of target column
    * @param value             String, value of target column
    * @param df                DataFrame
    * @return                  DataFrame
    */
  def addString(
    target_col: String,
    value: String
  )(
    df: DataFrame
  ): DataFrame = {
    df.withColumn(
      target_col
      , lit(value)
    )
  }
```

## Change values
```scala
  /**
    * change column value based on conditions
    * @param target_col        String, name of target column
    * @param condition_col     String, name of condition column
    * @param value             String, value of condition column
    * @param df                DataFrame
    * @return                  DataFrame
    */
  def addString(
    target_col: String,
    condition_col: String,
    value: String
  )(
    df: DataFrame
  ): DataFrame = {
    df.withColumn(
      target_col
      , when(col(condition_col) === value, *new_value*).otherwise(col(target_col))
    )
  }
```

## date conversion
```scala
df.withColumn("date", to_date(unix_timestamp(df.col("your_date_column"), "your_date_format").cast("timestamp")))
```

```scala
/**
  * convert timestamp to date time
  * @param dt_col_name        String, name of date time column
  * @param ts_col_name        String, name of timestamp column
  * @param df                 DataFrame
  * @return                   DataFrame
  */
def timestamp_to_datetime(
                           dt_col_name: String
                           , ts_col_name: String
                          )(
                            df: DataFrame
                          ): DataFrame = {
  df.withColumn(
    dt_col_name
    , from_unixtime(col(ts_col_name) / 1000, "yyyy-MM-dd HH:mm:ss")
  )
}
```

```scala
/**
  * Convert date time to date
  * @param date_col_name      String, name of date column
  * @param dt_col_name        String, name of date time column
  * @param df                 DataFrame
  * @return                   DataFrame
  */
def datetime_to_date(
                      date_col_name: String
                      , dt_col_name: String
                     )(
                       df: DataFrame
                     ): DataFrame = {
  df.withColumn(
    date_col_name
    , to_date(col(dt_col_name))
  )
}
```

```scala
/**
  * Get year from date
  * @param new_col_name       String, name of year column
  * @param date_col_name      String, name of date column
  * @param df                 DataFrame
  * @return                   DataFrame
  */
def get_year (
               new_col_name: String
               , date_col_name: String
              )(
                df: DataFrame
              ): DataFrame = {
  df.withColumn(
    new_col_name
    , year(col(date_col_name))
  )
}
```

## String manipulation
```scala
/**
  * Substring
  * @param df                 DataFrame
  * @return                   DataFrame
  */
def generate_bin_number(start_pos: int, len: int)(
                            df: DataFrame
                          ): DataFrame = {
  df.withColumn(
    "bin_number"
    , substring(col("account_number"), start_pos, len)
  )
}
```

## UDF
```scala
val udf: UserDefinedFunction = udf(((col1: String, col2: String) => {
    some_functions: Some
  }):((String, String) => Some))
  
def use_udf() (
                df: DataFrame
              ): DataFrame = {
 df.withColumn(
   new_col_nmae
 , udf(col("col_name1"), col("col_name2"))
 )
```
