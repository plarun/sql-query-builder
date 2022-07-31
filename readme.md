# SQL Query Builder
It helps to build parameterized SQL query statement.

## MySql Query Builder
```java
MysqlBuilder mysql = new MysqlBuilder();
```

## Select `statement`
```java
SelectStmt stmt1 = mysql.select()
        .columns("name", "age", "mail")
        .from(ref -> ref.tbl("customer"))
        .where(cond -> cond.isTrue("is_active")
                .orWrap().gt("last_action"))
        .order("name")
        .limit(10);

String query = stmt1.getQuery();
```
```mysql
Select name, age, mail
    From customer
    Where (is_active Is TRUE) Or (last_action > ?)
    Order By name
    Limit 10
```
---
```java
SelectStmt stmt2 = mysql.select()
        .columns("city", "town", "Count(*)")
        .from(ref -> ref.tbl("address"))
        .where(cond -> cond.eq("country").and().in("state", 5))
        .group("city", "town")
        .having(cond -> cond.gt("Count(*)"))
        .order("Count(*) Desc");
        
String query = stmt2.getQuery();
```
```mysql
Select city, town, Count(*) 
    From address 
    Where country = ? 
        And state In (?,?,?,?,?) 
    Group By city, town 
    Having Count(*) > ? 
    Order By Count(*) Desc
```