# Exercise 1
```
<program> ::= begin <statement_list> end
<statement_list> ::= <statement> ; <statement_list>
<statement_list> ::= <statement> ;
<statement> ::= id = <expression>
<expression> ::= <factor> + <factor>
<expression> ::= <factor> - <factor>
<expression> ::= <factor>
<factor> ::= id | num
```

below is a LL(1) representation of the VSPL grammar provided above

```
<program> ::= begin <statement_list> end

<statement_list>  ::= <statement> ; <statement_list'>
<statement_list'> ::= <statement_list>
                    | e

<statement> ::= id = <expression>

<expression>  ::= <factor> <expression'>
<expression'> ::= +<factor>
                | -<factor>
                | e

<factor> ::= id | num
```
