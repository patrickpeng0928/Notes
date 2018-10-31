# HBase Phoenix Interface
## Enter into Phoenix
```bash
/usr/hdp/current/phoenix-client/bin/sqlline.py <hbase_url>:<port>:/hbase-unsecure
```

## Syntax
https://phoenix.apache.org/language/index.html

### CREATE a table


### SELECT rows from a table
```sql
SELECT * FROM <tbl_name>;
```

### Delete rows in a table
```sql
DELETE FROM <tbl_name>;
DELETE FROM <tbl_name> WHERE <column_name> = '<value>';
```
