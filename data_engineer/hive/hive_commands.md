# Hive Operations

## Use schema
```hive
USE <db_name>;
```

## Drop table
```hive
DROP TABLE IF EXISTS <tbl_name>;
```

## Drop view
```hive
DROP VIEW IF EXISTS <view_name>;
```

## Repair table
```hive
MSCK REPAIR TABLE ${table};
```
