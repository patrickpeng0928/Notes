# Jupyter Notebook

## Set up password protection
```python
from getpass import getpass

username = ''
password = getpass(prompt = 'password: ')
```

## Data warehouse connection
```python
import cx_Oracle

db_host = '<db_url>'
port = <db_port>
service_id = '<service_id>'

conn = cx_Oracle.connect(f"{username}/{password}@{db_host}:{port}/{service_id}")

%%time
import pandas as pd
pd.read_sql("""select * from table where condition""", conn)
```
