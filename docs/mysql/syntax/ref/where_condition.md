# Sub syntax for where_condition

`simplified version`
```text
where_condition:
    expr [, expr] ...

expr:
    expr OR expr
  | expr XOR expr
  | expr AND expr
  | NOT expr
  | ! expr
  | boolean_primary IS [NOT] {TRUE | FALSE | UNKNOWN}
  | boolean_primary

boolean_primary:
    boolean_primary IS [NOT] NULL
  | boolean_primary <=> predicate
  | boolean_primary comparison_operator predicate
  | boolean_primary comparison_operator {ALL | ANY} (subquery)
  | predicate

comparison_operator: = | >= | > | <= | < | <> | !=

predicate:
    simple_expr [NOT] IN (subquery)
  | simple_expr [NOT] IN (expr [, expr] ...)
  | simple_expr [NOT] BETWEEN bit_expr AND predicate
  | simple_expr [NOT] LIKE simple_expr [ESCAPE simple_expr]
  | simple_expr [NOT] REGEXP bit_expr
  | simple_expr

simple_expr:
    literal
  | identifier
  | (subquery)
  | EXISTS (subquery)
```

## Examples
```roomsql
Select *
    From customer
    Where col1 = 1
        And col2 = 2
        Or col3 In (1, 2)
        Or col4 Not In (3, 4)
        Or col5 In ('a', 'b')
```