## Sub syntax for select_expr

`simplified version`
```text
select_alias:
    /* empty */
  | AS alias_name
  | alias_name

select_expr:
    expr select_alias
```

## Examples
```roomsql
Select
    id, name, age
    From customer;
```
```roomsql
Select
    id 'Id', name 'Name', age 'Age'
    From customer;
```
```roomsql
Select
    id As 'Id', name As 'Name', age As 'Age'
    From customer;
```