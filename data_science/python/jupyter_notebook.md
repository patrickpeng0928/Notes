# Jupyter Notebook

## Set up password protection
```python
from getpass import getpass
import cx_Oracle

username = ''
password = getpass(prompt = 'password: ')
db_host = '<db_url>'
port = <db_port>
service_id = '<service_id>'
```
```python
conn = cx_Oracle.connect(f"{username}/{password}@{db_host}:{port}/{service_id}")
```
```python
%%time
import pandas as pd
pd.read_sql("""select * from table where condition""", conn)
```
