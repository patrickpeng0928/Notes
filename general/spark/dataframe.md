# DataFrame Operations
## Filters

## Transformations
### date conversion
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

### String manipulation
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
