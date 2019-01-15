# Spark RDD Programming in Scala
```
https://spark.apache.org/docs/latest/rdd-programming-guide.html
```
## Create a RDD
```
val rdd = sc.parallelize(Seq(...))
```

### Broadcasting Variables
```scala
// create a broadcasting variable
val broadcastVar = sc.broadcast(Array(1, 2, 3))

// access the value of the broadcasted variable
broadcastVar.value
```
