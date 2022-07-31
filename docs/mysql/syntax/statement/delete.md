# MySQL Delete Statement

## Syntax
`simplified version`
```text
DELETE
    FROM tbl_name [[AS] tbl_alias]
    [WHERE where_condition]
    [ORDER BY ...]
    [LIMIT row_count]

DELETE
    tbl_name[.*] [, tbl_name[.*]] ...
    FROM table_references
    [WHERE where_condition]

DELETE
    FROM tbl_name[.*] [, tbl_name[.*]] ...
    USING table_references
    [WHERE where_condition]
```

## Examples
```roomsql
Delete
    From customer
    Where customer_name = 'test name';
```
```roomsql
Delete
    From item
    Where status_flag = 'marked for delete'
    Order By created_time
    Limit 5;
```
```roomsql
Delete cust, addr
    From customer cust Inner Join cust_address addr
    Where cust.addr_id = addr.addr_id
        And cust.cust_id = 1;

Delete
    From cust, addr
    Using customer As cust Inner Join cust_address As addr
    Where cust.addr_id = addr.addr_id
        And cust.cust_id = 1;
```