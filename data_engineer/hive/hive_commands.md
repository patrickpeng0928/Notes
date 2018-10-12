# Hive Operations

## Date conversions
* use dt (String) as date column in String
* input_format (String) as input format
* ouput_format (String)  as output format
```hive
from_unixtime(unix_timestamp(dt, input_format), output_format)
```

| Input Format | Code | Output Format |
| --- | --- | --- |
| ddMMyyyy | to_date(from_unixtime(unix_timestamp(dt,’ddMMyyyy’))) | yyyy-MM-dd |
| dd-MM-yyyy | to_date(from_unixtime(unix_timestamp(dt,’dd-MM-yyyy’))) | yyyy-MM-dd |
| dd/MM/yyyy | to_date(from_unixtime(unix_timestamp(dt,’dd/MM/yyyy’))) | yyyy-MM-dd |
| dd MM yyyy | to_date(from_unixtime(unix_timestamp(dt,’dd MM yyyy’))) | yyyy-MM-dd |
| dd.MM.yyyy | to_date(from_unixtime(unix_timestamp(dt,’dd.MM.yyyy’))) | yyyy-MM-dd |
| ddMMMyyyy | to_date(from_unixtime(unix_timestamp(dt,’ddMMMyyyy’))) | yyyy-MM-dd |
| dd-MMM-yyyy | to_date(from_unixtime(unix_timestamp(dt,’dd-MMM-yyyy’))) | yyyy-MM-dd |
| dd/MMM/yyyy | to_date(from_unixtime(unix_timestamp(dt,’dd/MMM/yyyy’))) | yyyy-MM-dd |
| dd MMM yyyy | to_date(from_unixtime(unix_timestamp(dt,’dd MMM yyyy’))) | yyyy-MM-dd |
| dd.MMM.yyyy | to_date(from_unixtime(unix_timestamp(dt,’dd.MMM.yyyy’))) | yyyy-MM-dd |
| ddMMMMyyyy | to_date(from_unixtime(unix_timestamp(dt,’ddMMMMyyyy’))) | yyyy-MM-dd |
| dd-MMMM-yyyy | to_date(from_unixtime(unix_timestamp(dt,’dd-MMMM-yyyy’))) | yyyy-MM-dd |
| dd/MMMM/yyyy | to_date(from_unixtime(unix_timestamp(dt,’dd/MMMM/yyyy’))) | yyyy-MM-dd |
| dd MMMM yyyy | to_date(from_unixtime(unix_timestamp(dt,’dd MMMM yyyy’))) | yyyy-MM-dd |
| dd.MMMM.yyyy | to_date(from_unixtime(unix_timestamp(dt,’dd.MMMM.yyyy’))) | yyyy-MM-dd |
| ddMMyy | to_date(from_unixtime(unix_timestamp(dt,’ddMMyy’))) | yyyy-MM-dd |
| dd-MM-yy | to_date(from_unixtime(unix_timestamp(dt,’dd-MM-yy’))) | yyyy-MM-dd |
| dd/MM/yy | to_date(from_unixtime(unix_timestamp(dt,’dd/MM/yy’))) | yyyy-MM-dd |
| dd MM yy | to_date(from_unixtime(unix_timestamp(dt,’dd MM yy’))) | yyyy-MM-dd |
| dd.MM.yy | to_date(from_unixtime(unix_timestamp(dt,’dd.MM.yy’))) | yyyy-MM-dd |
| ddMMMyy | to_date(from_unixtime(unix_timestamp(dt,’ddMMMyy’))) | yyyy-MM-dd |
| dd-MMM-yy | to_date(from_unixtime(unix_timestamp(dt,’dd-MMM-yy’))) | yyyy-MM-dd |
| dd/MMM/yy | to_date(from_unixtime(unix_timestamp(dt,’dd/MMM/yy’))) | yyyy-MM-dd |
| dd MMM yy | to_date(from_unixtime(unix_timestamp(dt,’dd MMM yy’))) | yyyy-MM-dd |
| dd.MMM.yy | to_date(from_unixtime(unix_timestamp(dt,’dd.MMM.yy’))) | yyyy-MM-dd |
| ddMMMMyy | to_date(from_unixtime(unix_timestamp(dt,’ddMMMMyy’))) | yyyy-MM-dd |
| dd-MMMM-yy | to_date(from_unixtime(unix_timestamp(dt,’dd-MMMM-yy’))) | yyyy-MM-dd |
| dd/MMMM/yy | to_date(from_unixtime(unix_timestamp(dt,’dd/MMMM/yy’))) | yyyy-MM-dd |
| dd MMMM yy | to_date(from_unixtime(unix_timestamp(dt,’dd MMMM yy’))) | yyyy-MM-dd |
| dd.MMMM.yy | to_date(from_unixtime(unix_timestamp(dt,’dd.MMMM.yy’))) | yyyy-MM-dd |
| MMddyyyy | to_date(from_unixtime(unix_timestamp(dt,’MMddyyyy’))) | yyyy-MM-dd |
| MM-dd-yyyy | to_date(from_unixtime(unix_timestamp(dt,’MM-dd-yyyy’))) | yyyy-MM-dd |
| MM/dd/yyyy | to_date(from_unixtime(unix_timestamp(dt,’MM/dd/yyyy’))) | yyyy-MM-dd |
| MM dd yyyy | to_date(from_unixtime(unix_timestamp(dt,’MM dd yyyy’))) | yyyy-MM-dd |
| MM.dd.yyyy | to_date(from_unixtime(unix_timestamp(dt,’MM.dd.yyyy’))) | yyyy-MM-dd |
| MMMddyyyy | to_date(from_unixtime(unix_timestamp(dt,’MMMddyyyy’))) | yyyy-MM-dd |
| MMM-dd-yyyy | to_date(from_unixtime(unix_timestamp(dt,’MMM-dd-yyyy’))) | yyyy-MM-dd |
| MMM/dd/yyyy | to_date(from_unixtime(unix_timestamp(dt,’MMM/dd/yyyy’))) | yyyy-MM-dd |
| MMM dd yyyy | to_date(from_unixtime(unix_timestamp(dt,’MMM dd yyyy’))) | yyyy-MM-dd |
| MMM.dd.yyyy | to_date(from_unixtime(unix_timestamp(dt,’MMM.dd.yyyy’))) | yyyy-MM-dd |
| MMMMddyyyy | to_date(from_unixtime(unix_timestamp(dt,’MMMMddyyyy’))) | yyyy-MM-dd |
| MMMM-dd-yyyy | to_date(from_unixtime(unix_timestamp(dt,’MMMM-dd-yyyy’))) | yyyy-MM-dd |
| MMMM/dd/yyyy | to_date(from_unixtime(unix_timestamp(dt,’MMMM/dd/yyyy’))) | yyyy-MM-dd |
| MMMM dd yyyy | to_date(from_unixtime(unix_timestamp(dt,’MMMM dd yyyy’))) | yyyy-MM-dd |
| MMMM.dd.yyyy | to_date(from_unixtime(unix_timestamp(dt,’MMMM.dd.yyyy’))) | yyyy-MM-dd |
| MMddyy | to_date(from_unixtime(unix_timestamp(dt,’MMddyy’))) | yyyy-MM-dd |
| MM-dd-yy | to_date(from_unixtime(unix_timestamp(dt,’MM-dd-yy’))) | yyyy-MM-dd |
| MM/dd/yy | to_date(from_unixtime(unix_timestamp(dt,’MM/dd/yy’))) | yyyy-MM-dd |
| MM dd yy | to_date(from_unixtime(unix_timestamp(dt,’MM dd yy’))) | yyyy-MM-dd |
| MM.dd.yy | to_date(from_unixtime(unix_timestamp(dt,’MM.dd.yy’))) | yyyy-MM-dd |
| MMMddyy | to_date(from_unixtime(unix_timestamp(dt,’MMMddyy’))) | yyyy-MM-dd |
| MMM-dd-yy | to_date(from_unixtime(unix_timestamp(dt,’MMM-dd-yy’))) | yyyy-MM-dd |
| MMM/dd/yy | to_date(from_unixtime(unix_timestamp(dt,’MMM/dd/yy’))) | yyyy-MM-dd |
| MMM dd yy | to_date(from_unixtime(unix_timestamp(dt,’MMM dd yy’))) | yyyy-MM-dd |
| MMM.dd.yy | to_date(from_unixtime(unix_timestamp(dt,’MMM.dd.yy’))) | yyyy-MM-dd |
| MMMMddyy | to_date(from_unixtime(unix_timestamp(dt,’MMMMddyy’))) | yyyy-MM-dd |
| MMMM-dd-yy | to_date(from_unixtime(unix_timestamp(dt,’MMMM-dd-yy’))) | yyyy-MM-dd |
| MMMM/dd/yy | to_date(from_unixtime(unix_timestamp(dt,’MMMM/dd/yy’))) | yyyy-MM-dd |
| MMMM dd yy | to_date(from_unixtime(unix_timestamp(dt,’MMMM dd yy’))) | yyyy-MM-dd |
| MMMM.dd.yy | to_date(from_unixtime(unix_timestamp(dt,’MMMM.dd.yy’))) | yyyy-MM-dd |
| yyyyMMdd | to_date(from_unixtime(unix_timestamp(dt,’yyyyMMdd’))) | yyyy-MM-dd |
| yyyy-MM-dd | to_date(from_unixtime(unix_timestamp(dt,’yyyy-MM-dd’))) | yyyy-MM-dd |
| yyyy/MM/dd | to_date(from_unixtime(unix_timestamp(dt,’yyyy/MM/dd’))) | yyyy-MM-dd |
| yyyy MM dd | to_date(from_unixtime(unix_timestamp(dt,’yyyy MM dd ‘))) | yyyy-MM-dd |
| yyyy.MM.dd | to_date(from_unixtime(unix_timestamp(dt,’yyyy.MM.dd’))) | yyyy-MM-dd |
| yyyyMMMdd | to_date(from_unixtime(unix_timestamp(dt,’yyyyMMMdd’))) | yyyy-MM-dd |
| yyyy-MMM-dd | to_date(from_unixtime(unix_timestamp(dt,’yyyy-MMM-dd’))) | yyyy-MM-dd |
| yyyy/MMM/dd | to_date(from_unixtime(unix_timestamp(dt,’yyyy/MMM/dd’))) | yyyy-MM-dd |
| yyyy MMM dd | to_date(from_unixtime(unix_timestamp(dt,’yyyy MMM dd ‘))) | yyyy-MM-dd |
| yyyy.MMM.dd | to_date(from_unixtime(unix_timestamp(dt,’yyyy.MMM.dd’))) | yyyy-MM-dd |
| yyyyMMMMdd | to_date(from_unixtime(unix_timestamp(dt,’yyyyMMMMdd’))) | yyyy-MM-dd |
| yyyy-MMMM-dd | to_date(from_unixtime(unix_timestamp(dt,’yyyy-MMMM-dd’))) | yyyy-MM-dd |
| yyyy/MMMM/dd | to_date(from_unixtime(unix_timestamp(dt,’yyyy/MMMM/dd’))) | yyyy-MM-dd |
| yyyy MMMM dd | to_date(from_unixtime(unix_timestamp(dt,’yyyy MMMM dd ‘))) | yyyy-MM-dd |
| yyyy.MMMM.dd | to_date(from_unixtime(unix_timestamp(dt,’yyyy.MMMM.dd’))) | yyyy-MM-dd |
| yyMMdd | to_date(from_unixtime(unix_timestamp(dt,’yyMMdd’))) | yyyy-MM-dd |
| yy-MM-dd | to_date(from_unixtime(unix_timestamp(dt,’yy-MM-dd’))) | yyyy-MM-dd |
| yy/MM/dd | to_date(from_unixtime(unix_timestamp(dt,’yy/MM/dd’))) | yyyy-MM-dd |
| yy MM dd | to_date(from_unixtime(unix_timestamp(dt,’yy MM dd ‘))) | yyyy-MM-dd |
| yy.MM.dd | to_date(from_unixtime(unix_timestamp(dt,’yy.MM.dd’))) | yyyy-MM-dd |
| yyMMMdd | to_date(from_unixtime(unix_timestamp(dt,’yyMMMdd’))) | yyyy-MM-dd |
| yy-MMM-dd | to_date(from_unixtime(unix_timestamp(dt,’yy-MMM-dd’))) | yyyy-MM-dd |
| yy/MMM/dd | to_date(from_unixtime(unix_timestamp(dt,’yy/MMM/dd’))) | yyyy-MM-dd |
| yy MMM dd | to_date(from_unixtime(unix_timestamp(dt,’yy MMM dd ‘))) | yyyy-MM-dd |
| yy.MMM.dd | to_date(from_unixtime(unix_timestamp(dt,’yy.MMM.dd’))) | yyyy-MM-dd |
| yyMMMMdd | to_date(from_unixtime(unix_timestamp(dt,’yyMMMMdd’))) | yyyy-MM-dd |
| yy-MMMM-dd | to_date(from_unixtime(unix_timestamp(dt,’yy-MMMM-dd’))) | yyyy-MM-dd |
| yy/MMMM/dd | to_date(from_unixtime(unix_timestamp(dt,’yy/MMMM/dd’))) | yyyy-MM-dd |
| yy MMMM dd | to_date(from_unixtime(unix_timestamp(dt,’yy MMMM dd ‘))) | yyyy-MM-dd |
| yy.MMMM.dd | to_date(from_unixtime(unix_timestamp(dt,’yy.MMMM.dd’))) | yyyy-MM-dd |

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
