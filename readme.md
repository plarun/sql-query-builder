# SQL Query Builder
It helps to build parameterized SQL query statement.

## MySql Query Builder
```java
MysqlBuilder mysql = new MysqlBuilder();
```
---
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
---
## Update `statement`
```java
UpdateStmt stmt1 = mysql.update()
    .onTable("customer")
    .set("age", "mail")
    .where(clause -> clause.eq("name").and().eq("id"));

String query = stmt1.getQuery();
```
```mysql
Update customer
    Set age = ?, mail = ?
    Where name = ? 
        And id = ?
```
```java
UpdateStmt stmt2 = mysql.update()
        .onTable(ref -> ref.tbl("address", "addr")
                .leftJoinUsing("temp1", "t1", using -> using.columns("id"))
                .leftJoinUsing("temp2", "t2", using -> using.columns("id"))
        ).set("town")
        .where(cond -> cond.eq("addr.id"));

String query = stmt2.getQuery();
```
```mysql
Update address addr
    Left Join temp1 t1 Using (id)
    Left Join temp2 t2 Using (id)
    Set town = ?
    Where addr.id = ?
```