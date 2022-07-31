# Sub syntax for `table_references`

`simplified version`
```text
table_references:
    table_reference [, table_reference] ...

table_reference: {
    table_factor
  | joined_table
}

table_factor: {
    tbl_name
        [[AS] alias]
  | ( table_references )
}

joined_table: {
    table_reference {[INNER | CROSS] JOIN | STRAIGHT_JOIN} table_factor [join_specification]
  | table_reference {LEFT|RIGHT} [OUTER] JOIN table_reference join_specification
  | table_reference NATURAL [INNER | {LEFT|RIGHT} [OUTER]] JOIN table_factor
}

join_specification: {
    ON search_condition
  | USING (join_column_list)
}

join_column_list:
    column_name [, column_name] ...
```

## Examples
`table_factor`
```roomsql
Select * From customer;
```
```roomsql
Select * From customer cust;
```
```roomsql
Select * From customer As cust;
```
---
`joined_table`

Inner Join with `On`
```roomsql
Select
    cust.id, cust.name, cust.mail, addr.city, addr.pincode
    From customer cust
    Inner Join address addr
        On cust.addr_id = addr.id;
```
Inner Join without `On`
```roomsql
Select
    cust.id, cust.name, cust.mail, addr.city, addr.pincode
    From customer cust
    Inner Join address addr
    Where cust.addr_id = addr.id;
```
Outer Join
```roomsql
Select
    cust.id, cust.name, cust.mail, addr.city, addr.pincode
    From customer cust
    Left Outer Join address addr
        On cust.addr_id = addr.id;
```
Natural Join
```roomsql
Select
    cust.id, cust.name, cust.mail, addr.city, addr.pincode
    From customer cust
    Natural Inner Join address addr;
```
Join with `Using`
```roomsql
Select
    id, name, addr_id, city
    From customer cust
    Inner Join address addr
        Using (addr_id);
```
Inner and Outer Join
```roomsql
Select
    usr.id As 'User Id', dept.id As 'Department Id', dept.name, usr.points, Count(idea.id) As 'Total Ideas'
    From user usr
    Inner Join department dept
        Using (id)
    Left Join customer_idea idea
        On usr.id = idea.id
    Order By idea.id;
```
