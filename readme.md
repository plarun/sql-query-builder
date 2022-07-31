# SQL Query Builder

## MySql Query Builder
```java
MysqlBuilder mysql = new MysqlBuilder();
```

## Select `statement`
```java
SelectStmt stmt = mysql.select()
        .columns("name", "age", "mail")
        .from(ref -> ref.tbl("customer"))
        .where(cond -> cond.isTrue("is_active")
                .orWrap().gt("last_action"))
        .order("name")
        .limit(10);

String query = stmt.getQuery();
```
```mysql
Select name, age, mail
    From customer
    Where (is_active Is TRUE) Or (last_action > ?)
    Order By name
    Limit 10
```