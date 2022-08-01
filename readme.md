# SQL Query Builder
It helps to build parameterized SQL query statement.

## MySql Query Builder
```java
MysqlBuilder mysql = new MysqlBuilder();
```
---
## Select `statement`
```java
SelectStmt stmt = mysql.select()
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
SelectStmt stmt = mysql.select()
    .columns("city", "town", "Count(*)")
    .from("address")
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
UpdateStmt stmt = mysql.update()
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
UpdateStmt stmt = mysql.update()
        .table(ref -> ref.tbl("address", "addr")
                .innerJoinUsing("customer", "cust", using -> using.columns("addr_id")))
        .set("addr.town")
        .where(cond -> cond.eq("cust.id"));
```
```mysql
Update address addr
        Inner Join customer cust Using (addr_id)
        Set addr.town = ?
        Where cust.id = ?
```
---
## Delete `statement`
```java
DeleteStmt stmt1 = mysql.delete()
        .from("customer")
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
        .where(cond -> cond.eq("cust.addr_id", "addr.id")
                .and().eq("cust.id"));
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
## With `clause`
```java
Selectstmt stmt = mysql.with()
        .as("indian", select -> select
                .columns("*")
                .from("states")
                .where(cond -> cond.eq("country")))
        .as("frequent", select -> select
                .columns("*")
                .from("activity")
                .where(cond -> cond.gt("frequency")))
        .select()
        .columns("c.name", "i.state", "f.last_activity")
        .from(ref -> ref
                .tbl("customer", "c")
                .innerJoinUsing("indian", "i", using -> using.columns("state_id"))
                .innerJoinUsing("frequent", "f", using -> using.columns("act_id")));
```
```mysql
With
        indian As (Select * From states Where country = ?), 
        frequent As (Select * From activity Where frequency > ?) 
Select c.name, i.state, f.last_activity 
        From customer c 
        Inner Join indian i Using (state_id) 
        Inner Join frequent f Using (act_id)
```