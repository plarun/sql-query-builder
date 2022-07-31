# MySQL Select Statement

## Syntax
`Simplified version`
```text
SELECT
    [DISTINCT]
    select_expr [, select_expr] ...
    [FROM table_references]
    [WHERE where_condition]
    [GROUP BY {col_name | expr | position}, ...]
    [HAVING where_condition]
    [ORDER BY {col_name | expr | position}
      [ASC | DESC], ...]
    [LIMIT {[offset,] row_count | row_count OFFSET offset}]
```

## Examples
```roomsql
Select
    id, name As 'customer name', age 'age', email
    From customer
    Where age > 18
    Order By name Asc, age Desc
    Limit 10;
```
Distinct
```roomsql
Select
    Distinct
    id, name As 'customer name', age 'age', email
    From customer
    Where age > 18
    Order By name Asc, age Desc
    Limit 10;
```
Group By Clause
```roomsql
Select
    age, Count(*)
    From customer
    Group By age
    Having age > 10
    Order By Count(*)
```