# DataFrame Transformation
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
