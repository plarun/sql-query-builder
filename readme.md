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
    .from("customer")
    .where(cond -> cond.isTrue("is_active")
            .orWrap().gt("last_action"))
    .order("name")
    .limit(10);
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
    .table("customer")
    .set("age", "mail")
    .where(cond -> cond.eq("name").and().eq("id"));
```
```mysql
Update customer
    Set age = ?, mail = ?
    Where name = ? 
        And id = ?
```
```java
UpdateStmt stmt2 = mysql.update()
        .table(ref -> ref.tbl("address", "addr")
                .leftJoinUsing("temp1", "t1", using -> using.columns("id"))
                .leftJoinUsing("temp2", "t2", using -> using.columns("id"))
        ).set("town")
        .where(cond -> cond.eq("addr.id"));
```
```mysql
Update address addr
    Left Join temp1 t1 Using (id)
    Left Join temp2 t2 Using (id)
    Set town = ?
    Where addr.id = ?
```
---
## Delete `statement`
```java
DeleteStmt stmt1 = mysql.delete()
        .from(ref -> ref.tbl("customer"))
        .where(cond -> cond.eq("name").and().eq("id"));
```
```mysql
Delete
    From customer
    Where name = ? 
        And id = ?
```
```java
DeleteStmt stmt2 = mysql.delete()
        .ref("cust", "addr")
        .from(ref -> ref.tbl("customer", "cust")
        .innerJoin("cust_address", "addr"))
        .where(cond -> cond.eq("cust.addr_id", "addr.id").and().eq("cust.id"));
```
```mysql
Delete cust, addr
    From customer cust
    Inner Join cust_address addr
    Where cust.addr_id = addr.id
        And cust.id = ?
```
---
## Insert `statement`
```java
InsertStmt stmt1 = mysql.insert()
        .into("schema", "customer")
        .columns("id", "name", "age", "email", "gender")
        .rows(3);
```
```mysql
Insert Into schema.customer (id, name, age, email, gender)
    Values (?, ?, ?, ?, ?), 
        (?, ?, ?, ?, ?), 
        (?, ?, ?, ?, ?)
```
```java
InsertStmt stmt1 = mysql.insert()
        .into("customer")
        .columns(5)
        .rows(3);
```
```mysql
Insert Into customer
    Values (?, ?, ?, ?, ?), 
        (?, ?, ?, ?, ?), 
        (?, ?, ?, ?, ?)
```
