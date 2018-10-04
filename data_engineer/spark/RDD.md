# Spark RDD Programming
```
https://spark.apache.org/docs/latest/rdd-programming-guide.html
```

### Broadcasting Variables
```scala
// create a broadcasting variable
val broadcastVar = sc.broadcast(Array(1, 2, 3))

// access the value of the broadcasted variable
broadcastVar.value
```
