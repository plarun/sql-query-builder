# MySql Update Statement

## Syntax
`simplified version`
```text
UPDATE table_reference
    SET assignment_list
    [WHERE where_condition]
    [ORDER BY ...]
    [LIMIT row_count]

value:
    {expr | DEFAULT}

assignment:
    col_name = value

assignment_list:
    assignment [, assignment] ...
```

## Examples
```roomsql
Update item
	Set status = 'marked for delete', expired = 1
    Where created_time < current_date - Interval 100 Day
        And id In (1,2,3)
    Order By created_time Desc
    Limit 1;
```