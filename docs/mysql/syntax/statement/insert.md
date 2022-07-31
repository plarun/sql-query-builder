# MySql Insert Statement

## Syntax
`simplified version`
```text
INSERT
    [INTO] tbl_name
    [(col_name [, col_name] ...)]
    { {VALUES | VALUE} (value_list) [, (value_list)] ... }

value:
    {expr | DEFAULT}

value_list:
    value [, value] ...
```

## Examples
```roomsql
Insert
    Into customer
    (name, age, gender, email)
    Values
        ('user1', 23, 'M', 'user1@mail.com'),
        ('user2', 22, 'F', 'user2@mail.com');
```