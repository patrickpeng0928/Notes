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

## Set variable
```hive
set VARIABLE_NAME=vale;
```

## Arguments
```hive
select ${in_varible}
```

```bash
beeline -f /path/to/hive/script.hql --hivevar in_varible=$IN_VARIABLE_IN_SHELL_SCRIPT
```
