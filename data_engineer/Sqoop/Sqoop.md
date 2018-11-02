# Sqoop

## Change Sqoop job metastore
```bash
cd ~/.sqoop
vi metastore.db.script
```

### Change last value
```
INSERT INTO SQOOP_SESSIONS VALUES('<sqoop_job_name>','incremental.last.value','<value>','SqoopOptions')
```
