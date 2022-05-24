---
title: Drools DMN FEEL handbook

# language_tabs: # must be one of https://git.io/vQNgJ
#   - shell
#   - ruby
#   - python
#   - javascript

toc_footers:
  - Open the <a href='https://www.drools.org/learn/documentation.html'>full Drools manual (here)</a>.

# includes:
#   - errors

search: false

code_clipboard: true

meta:
  - name: Drools DMN FEEL handbook
    content: FEEL handbook, FEEL vademecum, FEEL cheatsheet, FEEL pocket reference of the FEEL expression language from the DMN specification
---

# DMN FEEL handbook

This is a vademecum for the FEEL expression language from the DMN specification, as also implemented by the <a href='https://www.drools.org/learn/dmn.html'>Drools DMN open source engine</a>.

This is not intented as an exahustive documentation of the DMN capabilities of Drools; this is intended as a handy a pocket reference for FEEL usage, a FEEL pocket guide, a FEEL reference, a FEEL quick reference, a FEEL cheatsheet,<br/>**a FEEL handbook**.

You can refer to the complete <a href='https://www.drools.org/learn/documentation.html'>Drools DMN Engine documentation</a> on the main Drools website <a href='https://www.drools.org/learn/documentation.html'>here</a>.

# String functions

This chapter explores the DMN FEEL specification built-in functions for `string`s.

<aside class="notice">
In FEEL, Unicode characters are counted based on their code points.
</aside>

## substring( string, start position, length? )

> Examples:

```FEEL
substring( "testing",3 ) = "sting"
substring( "testing",3,3 ) = "sti"
substring( "testing", -2, 1 ) = "n"
substring( "\U01F40Eab", 2 ) = "ab"
```

Returns the substring from the start position for the specified length. The first character is at position value `1`.

| Parameter          | Type                                            |
|-|-|
| `string`           | `string`                                        |
| `start position`   | `number`                                        |
| `length`  (Optional)           | `number`                                        |

<aside class="notice">
In FEEL, the string literal <code>"\U01F40Eab"</code> is the <code>"🐎ab"</code> string
(horse emoji followed by "a" and "b" characters).
</aside>

## string length( string )

> Examples: 

``` {.FEEL}
string length( "tes" ) = 3
string length( "\U01F40Eab" ) = 3
```

| Parameter          | Type                                            |
|-|-|
| `string`           | `string`                                        |

Calculates the length of the specified string.

## upper case( string )

> Examples:

``` {.FEEL}
upper case( "aBc4" ) = "ABC4"
```

| Parameter          | Type                                            |
|-|-|
| `string`           | `string`                                        |

Produces an uppercase version of the specified string.

## lower case( string )

> Examples:

``` {.FEEL}
lower case( "aBc4" ) = "abc4"
```

| Parameter          | Type                                            |
|-|-|
| `string`           | `string`                                        |

Produces a lowercase version of the specified string.

## substring before( string, match )

> Examples:

``` {.FEEL}
substring before( "testing", "ing" ) = "test"
substring before( "testing", "xyz" ) = ""
```

| Parameter          | Type                                            |
|-|-|
| `string`           | `string`                                        |
| `match`            | `string`                                        |

Calculates the substring before the match.

## substring after( string, match )

> Examples:

``` {.FEEL}
substring after( "testing", "test" ) = "ing"
substring after( "", "a" ) = ""
```

| Parameter          | Type                                            |
|-|-|
| `string`           | `string`                                        |
| `match`            | `string`                                        |

Calculates the substring after the match.

## replace( input, pattern, replacement, flags? )

> Examples: 

``` {.FEEL}
replace( "banana", "a", "o" ) = "bonono"
replace( "abcd", "(ab)|(a)", "[1=$1][2=$2]" ) = "[1=ab][2=]cd"
```

| Parameter          | Type                                            |
|-|-|
| `input`            | `string`                                        |
| `pattern`          | `string`                                        |
| `replacement`      | `string`                                        |
| `flags` (Optional) | `string`                                        |

Calculates the regular expression replacement.

<aside class="notice">
This function uses regular expression parameters as defined in
<a href="https://www.w3.org/TR/xquery-operators/#regex-syntax">XQuery 1.0 and XPath 2.0 Functions and Operators</a>.
</aside>

## contains( string, match )

> Examples: 

``` {.FEEL}
contains( "testing", "to" ) = false
```

Returns `true` if the string contains the match.

| Parameter          | Type                                            |
|-|-|
| `string`           | `string`                                        |
| `match`            | `string`                                        |

## starts with( string, match )

> Examples:

``` {.FEEL}
starts with( "testing", "te" ) = true
```

| Parameter          | Type                                            |
|-|-|
| `string`           | `string`                                        |
| `match`            | `string`                                        |

Returns `true` if the string starts with the match

## ends with( string, match )

> Examples:

``` {.FEEL}
ends with( "testing", "g" ) = true
```

| Parameter          | Type                                            |
|-|-|
| `string`           | `string`                                        |
| `match`            | `string`                                        |

Returns `true` if the string ends with the match.

## matches( input, pattern, flags? )

> Examples:

``` {.FEEL}
matches( "teeesting", "^te*sting" ) = true
```

| Parameter          | Type                                            |
|-|-|
| `input`            | `string`                                        |
| `pattern`          | `string`                                        |
| `flags` (Optional) | `string`                                        |

Returns `true` if the input matches the regular expression.

<aside class="notice">
This function uses regular expression parameters as defined in
<a href="https://www.w3.org/TR/xquery-operators/#regex-syntax">XQuery 1.0 and XPath 2.0 Functions and Operators</a>.
</aside>

## split( string, delimiter )

> Examples:

``` {.FEEL}
split( "John Doe", "\\s" ) = ["John", "Doe"]
split( "a;b;c;;", ";" ) = ["a","b","c","",""]
```

| Parameter          | Type                                            |
|-|-|
| `string`           | `string`                                        |
| `delimiter`        | `string` for a regular expression pattern       |

Returns a list of the original string and splits it at the delimiter
regular expression pattern.

<aside class="notice">
This function uses regular expression parameters as defined in
<a href="https://www.w3.org/TR/xquery-operators/#regex-syntax">XQuery 1.0 and XPath 2.0 Functions and Operators</a>.
</aside>

## string join( list, delimiter )

> Examples:

``` {.FEEL}
string join(["a","b","c"], "_and_") = "a_and_b_and_c"
string join(["a","b","c"], "") = "abc"
string join(["a","b","c"], null) = "abc"
string join(["a"], "X") = "a"
string join(["a",null,"c"], "X") = "aXc"
string join([], "X") = ""
```

| Parameter          | Type                                            |
|-|-|
| `list`           | `list` of `string`                                        |
| `delimiter`        | `string`        |

Returns a string which is composed by joining all the string elements from the list parameter, separated by the delimiter.
The `delimiter` can be an empty string.
Null elements in the list parameter are ignored.
If `list` is empty, the result is the empty string.
If `delimiter` is null, the string elements are joined without a separator.

## string join( list )

> Examples:

``` {.FEEL}
string join(["a","b","c"], "_and_") = "a_and_b_and_c"
string join(["a","b","c"], "") = "abc"
string join(["a","b","c"], null) = "abc"
string join(["a"], "X") = "a"
string join(["a",null,"c"], "X") = "aXc"
string join([], "X") = ""
```

| Parameter          | Type                                            |
|-|-|
| `list`           | `list` of `string`                                        |

Returns a string which is composed by joining all the string elements from the list parameter.
Null elements in the `list` parameter are ignored.
If `list` is empty, the result is the empty string.

# List functions

This chapter explores the DMN FEEL specification built-in functions for `list`s.

<aside class="notice">
In FEEL, the index of the first element in a list is <code>1</code>. The index of
the last element in a list can be identified as <code>-1</code>.
</aside>

## list contains( list, element )

> Examples:

``` {.FEEL}
list contains( [1,2,3], 2 ) = true
```

| Parameter          | Type                                            |
|-|-|
| `list`             | `list`                                          |
| `element`          | Any type, including null                        |

Returns `true` if the list contains the element.

## count( list )

> Examples:

``` {.FEEL}
count( [1,2,3] ) = 3
count( [] ) = 0
count( [1,[2,3]] ) = 2
```

| Parameter          | Type                                            |
|-|-|
| `list`             | `list`                                          |

Counts the elements in the list.

## min( list )

> Alternative signature: `min( e1, e2, ..., eN )`

> Examples:

``` {.FEEL}
min( [1,2,3] ) = 1
min( 1 ) = 1
min( [1] ) = 1
```

| Parameter          | Type                                            |
|-|-|
| `list`             | `list`                                          |

Returns the minimum comparable element in the list.

## max( list )

> Alternative signature: `max( e1, e2, ..., eN )`

> Examples:

``` {.FEEL}
max( 1,2,3 ) = 3
max( [] ) = null
```

| Parameter          | Type                                            |
|-|-|
| `list`             | `list`                                          |

Returns the maximum comparable element in the list.

## sum( list )

> Alternative signature: `sum( n1, n2, ..., nN )`

> Examples:

``` {.FEEL}
sum( [1,2,3] ) = 6
sum( 1,2,3 ) = 6
sum( 1 ) = 1
sum( [] ) = null
```

| Parameter          | Type                                            |
|-|-|
| `list`             | `list` of `number` elements                     |

Returns the sum of the numbers in the list.

## mean( list )

> Alternative signature: `mean( n1, n2, ..., nN )`

> Examples:

``` {.FEEL}
mean( [1,2,3] ) = 2
mean( 1,2,3 ) = 2
mean( 1 ) = 1
mean( [] ) = null
```

| Parameter          | Type                                            |
|-|-|
| `list`             | `list` of `number` elements                     |

Calculates the average (arithmetic mean) of the elements in the
list.

## all( list )

> Alternative signature: `all( b1, b2, ..., bN )`

> Examples:

``` {.FEEL}
all( [false,null,true] ) = false
all( true ) = true
all( [true] ) = true
all( [] ) = true
all( 0 ) = null
```

| Parameter          | Type                                            |
|-|-|
| `list`             | `list` of `boolean` elements                    |

Returns `true` if all elements in the list are true.

## any( list )

> Alternative signature: `any( b1, b2, ..., bN )`

> Examples:

``` {.FEEL}
any( [false,null,true] ) = true
any( false ) = false
any( [] ) = false
any( 0 ) = null
```

| Parameter          | Type                                            |
|-|-|
| `list`             | `list` of `boolean` elements                    |

Returns `true` if any element in the list is true.

## sublist( list, start position, length? )

> Examples:

``` {.FEEL}
sublist( [4,5,6], 1, 2 ) = [4,5]
```

| Parameter          | Type                                            |
|-|-|
| `list`             | `list`                                          |
| `start position`   | `number`                                        |
| `length`      (Optional)      | `number`                                        |

Returns the sublist from the start position, limited to the length
elements.

## append( list, item )

> Examples:

``` {.FEEL}
append( [1], 2, 3 ) = [1,2,3]
```

| Parameter          | Type                                            |
|-|-|
| `list`             | `list`                                          |
| `item`             | Any type                                        |

Creates a list that is appended to the item or items.

## concatenate( list )

> Examples: 

``` {.FEEL}
concatenate( [1,2],[3] ) = [1,2,3]
```

| Parameter          | Type                                            |
|-|-|
| `list`             | `list`                                          |

Creates a list that is the result of the concatenated lists.

## insert before( list, position, newItem )

> Examples: 

``` {.FEEL}
insert before( [1,3],1,2 ) = [2,1,3]
```

| Parameter          | Type                                            |
|-|-|
| `list`             | `list`                                          |
| `position`         | `number`                                        |
| `newItem`          | Any type                                        |

Creates a list with the `newItem` inserted at the specified
position.

## remove( list, position )

> Examples:

``` {.FEEL}
remove( [1,2,3], 2 ) = [1,3]
```

| Parameter          | Type                                            |
|-|-|
| `list`             | `list`                                          |
| `position`         | `number`                                        |

Creates a list with the removed element excluded from the specified
position.

## reverse( list )

> Examples:

``` {.FEEL}
reverse( [1,2,3] ) = [3,2,1]
```

| Parameter          | Type                                            |
|-|-|
| `list`             | `list`                                          |

Returns a reversed list.

## index of( list, match )

> Examples:

``` {.FEEL}
index of( [1,2,3,2],2 ) = [2,4]
```

| Parameter          | Type                                            |
|-|-|
| `list`             | `list`                                          |
| `match`            | Any type                                        |

Returns indexes matching the element.

## union( list )

> Examples:

``` {.FEEL}
union( [1,2],[2,3] ) = [1,2,3]
```

| Parameter          | Type                                            |
|-|-|
| `list`             | `list`                                          |

Returns a list of all the elements from multiple lists and excludes
duplicates.

## distinct values( list )

> Examples:

``` {.FEEL}
distinct values( [1,2,3,2,1] ) = [1,2,3]
```

| Parameter          | Type                                            |
|-|-|
| `list`             | `list`                                          |

Returns a list of elements from a single list and excludes
duplicates.


## flatten( list )

> Examples

``` {.FEEL}
flatten( [[1,2],[[3]], 4] ) = [1,2,3,4]
```

| Parameter          | Type                                            |
|-|-|
| `list`             | `list`                                          |

Returns a flattened list.

## product( list )

> Alternative signature: `product( n1, n2, ..., nN )`

> Examples:

``` {.FEEL}
product( [2, 3, 4] ) = 24
product( [] ) = null
product( 2, 3, 4 ) = 24
```

| Parameter          | Type                                            |
|-|-|
| `list`             | `list` of `number` elements                     |

Returns the product of the numbers in the list.

## median( list )

> Alternative signature: `median( n1, n2, ..., nN )`

> Examples:

``` {.FEEL}
median( 8, 2, 5, 3, 4 ) = 4
median( [6, 1, 2, 3] ) = 2.5
median( [ ] ) = null
```

| Parameter          | Type                                            |
|-|-|
| `list`             | `list` of `number` elements                     |

Returns the median of the numbers in the list. If the number of
elements is odd, the result is the middle element. If the number of
elements is even, the result is the average of the two middle
elements.

## stddev( list )

> Alternative signature: `stddev( n1, n2, ..., nN )`

> Examples:

``` {.FEEL}
stddev( 2, 4, 7, 5 ) = 2.081665999466132735282297706979931
stddev( [47] ) = null
stddev( 47 ) = null
stddev( [ ] ) = null
```

| Parameter          | Type                                            |
|-|-|
| `list`             | `list` of `number` elements                     |

Returns the standard deviation of the numbers in the list.

## mode( list )

> Alternative signature: `mode( n1, n2, ..., nN )`

> Examples:

``` {.FEEL}
mode( 6, 3, 9, 6, 6 ) = [6]
mode( [6, 1, 9, 6, 1] ) = [1, 6]
mode( [ ] ) = [ ]
```

| Parameter          | Type                                            |
|-|-|
| `list`             | `list` of `number` elements                     |

Returns the mode of the numbers in the list. If multiple elements
are returned, the numbers are sorted in ascending order.

# Numeric functions

This chapter explores the DMN FEEL specification built-in functions for `number`s.

## decimal( n, scale )

> Examples: 

``` {.FEEL}
decimal( 1/3, 2 ) = .33
decimal( 1.5, 0 ) = 2
decimal( 2.5, 0 ) = 2
decimal( 1.035, 2 ) = 1.04
decimal( 1.045, 2 ) = 1.04
decimal( 1.055, 2 ) = 1.06
decimal( 1.065, 2 ) = 1.06
```

| Parameter          | Type                                            |
|-|-|
| `n`                | `number`                                        |
| `scale`            | `number` in the range `[−6111..6176]`           |

Returns a number with the specified scale.

<aside class="notice">
This function is implemented to be consistent with the
<code>FEEL:number</code> definition for rounding decimal numbers to the
nearest even decimal number.
</aside>

## floor( n )

> Alternative signature: `floor( n, scale )`

> Examples:

``` {.FEEL}
floor( 1.5 ) = 1
floor( -1.56, 1 ) = -1.6
floor( -1.5 ) = -2
```

| Parameter          | Type                                            |
|-|-|
| `n`                | `number`                                        |

Returns `n` with given scale and rounding mode _flooring_.
If at least one of `n` or `scale` is null, the result is null.

## ceiling( n )

> Alternative signature: `ceiling( n, scale )`

> Examples:

``` {.FEEL}
ceiling( 1.5 ) = 2
ceiling( -1.56, 1 ) = -1.5
ceiling( -1.5 ) = -1
```

Returns `n` with given scale and rounding mode _ceiling_.
If at least one of `n` or `scale` is null, the result is null.

| Parameter          | Type                                            |
|-|-|
| `n`                | `number`                                        |

## round up( n, scale )

> Examples:

``` {.FEEL}
round up( 5.5, 0 ) = 6 
round up( -5.5, 0 ) = -6 
round up( 1.121, 2 ) = 1.13
round up( -1.126, 2 ) = -1.13
```

Returns `n` with given scale and rounding mode _round up_.
If at least one of `n` or `scale` is null, the result is null.

| Parameter          | Type                                            |
|-|-|
| `n`                | `number`                                        |
| `scale`            | `number`                                        |

## round down( n, scale )

> Examples:

``` {.FEEL}
round down( 5.5, 0 ) = 5 
round down( -5.5, 0 ) = -5 
round down( 1.121, 2 ) = 1.12
round down( -1.126, 2 ) = -1.12
```

Returns `n` with given scale and rounding mode _round down_.
If at least one of `n` or `scale` is null, the result is null.

| Parameter          | Type                                            |
|-|-|
| `n`                | `number`                                        |
| `scale`            | `number`                                        |

## round half up( n, scale )

> Examples:

``` {.FEEL}
round half up( 5.5, 0 ) = 6 
round half up( -5.5, 0 ) = -6 
round half up( 1.121, 2 ) = 1.12
round half up( -1.126, 2 ) = -1.13
```

Returns `n` with given scale and rounding mode _round half up_.
If at least one of `n` or `scale` is null, the result is null.

| Parameter          | Type                                            |
|-|-|
| `n`                | `number`                                        |
| `scale`            | `number`                                        |

## round hald down( n, scale )

> Examples:

``` {.FEEL}
round half down( 5.5, 0 ) = 5 
round half down( -5.5, 0 ) = -5 
round half down( 1.121, 2 ) = 1.12
round half down( -1.126, 2 ) = -1.13
```

Returns `n` with given scale and rounding mode _round half down_.
If at least one of `n` or `scale` is null, the result is null.

| Parameter          | Type                                            |
|-|-|
| `n`                | `number`                                        |
| `scale`            | `number`                                        |

## abs( n )

> Examples:

``` {.FEEL}
abs( 10 ) = 10
abs( -10 ) = 10
abs( @"PT5H" ) = @"PT5H"
abs( @"-PT5H" ) = @"PT5H"
```

| Parameter          | Type                                            |
|-|-|
| `n`                | `number`, `days and time duration`, or  `years and months duration`                     |

Returns the absolute value.

## modulo( dividend, divisor )

> Examples:

``` {.FEEL}
modulo( 12, 5 ) = 2
modulo( -12,5 )= 3
modulo( 12,-5 )= -3
modulo( -12,-5 )= -2
modulo( 10.1, 4.5 )= 1.1
modulo( -10.1, 4.5 )= 3.4
modulo( 10.1, -4.5 )= -3.4
modulo( -10.1, -4.5 )= -1.1
```

| Parameter          | Type                                            |
|-|-|
| `dividend`         | `number`                                        |
| `divisor`          | `number`                                        |

Returns the remainder of the division of the dividend by the
divisor. If either the dividend or divisor is negative, the result
is of the same sign as the divisor.

<aside class="notice">
This function is also expressed as
<code>modulo(dividend, divisor) = dividend - divisor*floor(dividen/divisor)</code>.
</aside>

## sqrt( number )

> Examples:

``` {.FEEL}
sqrt( 16 ) = 4
```

Returns the square root of the specified number.

| Parameter          | Type                                            |
|-|-|
| `n`                | `number`                                        |

## log( number )

> Examples:

``` {.FEEL}
decimal( log( 10 ), 2 ) = 2.30
```

| Parameter          | Type                                            |
|-|-|
| `n`                | `number`                                        |

Returns the logarithm of the specified number.

## exp( number )

> Examples:

``` {.FEEL}
decimal( exp( 5 ), 2 ) = 148.41
```

| Parameter          | Type                                            |
|-|-|
| `n`                | `number`                                        |

Returns Euler's number `e` raised to the power of the specified
number.

## odd( number )

> Examples:

``` {.FEEL}
odd( 5 ) = true
odd( 2 ) = false
```

| Parameter          | Type                                            |
|-|-|
| `n`                | `number`                                        |

Returns `true` if the specified number is odd.

## even( number )

> Examples:

``` {.FEEL}
even( 5 ) = false
even ( 2 ) = true
```

| Parameter          | Type                                            |
|-|-|
| `n`                | `number`                                        |

Returns `true` if the specified number is even.

# Boolean functions 

This chapter explores the DMN FEEL specification built-in functions for `boolean`s.

## not( negand )

> Examples

``` {.FEEL}
not( true ) = false
not( null ) = null
```

| Parameter          | Type                                            |
|-|-|
| `negand`           | `boolean`                                       |

Performs the logical negation of the `negand` operand.

# Date and time functions

This chapter explores the DMN FEEL specification built-in functions for temporal `date and time` comparisons.

## is( value1, value2 )

> Examples:

``` {.FEEL}
is( date( "2012-12-25" ), time( "23:00:50" ) ) = false
is( date( "2012-12-25" ), date( "2012-12-25" ) ) = true
is( time( "23:00:50z" ), time( "23:00:50" ) ) = false
is( time( "23:00:50z" ), time( "23:00:50+00:00" ) ) = true
```

| Parameter          | Type                                            |
|-|-|
| `value1`           | Any type                                        |
| `value2`           | Any type                                        |

Returns `true` if both values are the same element in the FEEL
semantic domain.

# Range functions

The following functions support temporal ordering operations to
establish relationships between single scalar values and ranges of such
values. These functions are similar to the components in the Health
Level Seven (HL7) International [Clinical Quality Language (CQL) 1.4
syntax](https://cql.hl7.org/08-a-cqlsyntax.html).

## before( )

> Examples:

``` {.FEEL}
before( 1, 10 ) = true
before( 10, 1 ) = false
before( 1, [1..10] ) = false
before( 1, (1..10] ) = true
before( 1, [5..10] ) = true
before( [1..10], 10 ) = false
before( [1..10), 10 ) = true
before( [1..10], 15 ) = true
before( [1..10], [15..20] ) = true
before( [1..10], [10..20] ) = false
before( [1..10), [10..20] ) = true
before( [1..10], (10..20] ) = true
```

Returns `true` when an element `A` is before an element `B` and when
the relevant requirements for evaluating to `true` are also met.

Signatures:

a.  `before( point1 point2 )`

b.  `before( point, range )`

c.  `before( range, point )`

d.  `before( range1,range2 )`

Semantic:

a.  `point1 < point2`

b.  `point < range.start or ( point = range.start and not(range.start included) )`

c.  `range.end < point or ( range.end = point and not(range.end included) )`

d.  `range1.end < range2.start or (( not(range1.end included) or not(range2.start included) ) and range1.end = range2.start )`

## after( )

> Examples:

``` {.FEEL}
after( 10, 5 ) = true
after( 5, 10 ) = false
after( 12, [1..10] ) = true
after( 10, [1..10) ) = true
after( 10, [1..10] ) = false
after( [11..20], 12 ) = false
after( [11..20], 10 ) = true
after( (11..20], 11 ) = true
after( [11..20], 11 ) = false
after( [11..20], [1..10] ) = true
after( [1..10], [11..20] ) = false
after( [11..20], [1..11) ) = true
after( (11..20], [1..11] ) = true
```

Returns `true` when an element `A` is after an element `B` and when
the relevant requirements for evaluating to `true` are also met.

Signatures:

a.  `after( point1 point2 )`

b.  `after( point range )`

c.  `after( range, point )`

d.  `after( range1 range2 )`

Semantic:

a.  `point1 > point2`

b.  `point > range.end or ( point = range.end and not(range.end included) )`

c.  `range.start > point or ( range.start = point and not(range.start included) )`

d.  `range1.start > range2.end or (( not(range1.start included) or not(range2.end included) ) and range1.start = range2.end )`

## meets( )

> Examples:

``` {.FEEL}
meets( [1..5], [5..10] ) = true
meets( [1..5), [5..10] ) = false
meets( [1..5], (5..10] ) = false
meets( [1..5], [6..10] ) = false
```

Returns `true` when an element `A` meets an element `B` and when the
relevant requirements for evaluating to `true` are also met.

Signatures:

a.  `meets( range1, range2 )`

Semantic:

a.  `range1.end included and range2.start included and range1.end = range2.start`

## met by( )

> Examples:

``` {.FEEL}
met by( [5..10], [1..5] ) = true
met by( [5..10], [1..5) ) = false
met by( (5..10], [1..5] ) = false
met by( [6..10], [1..5] ) = false
```

Returns `true` when an element `A` is met by an element `B` and when
the relevant requirements for evaluating to `true` are also met.

Signatures:

a.  `met by( range1, range2 )`

Semantic:

a.  `range1.start included and range2.end included and range1.start = range2.end`

## overlaps( )

> Examples:

``` {.FEEL}
overlaps( [1..5], [3..8] ) = true
overlaps( [3..8], [1..5] ) = true
overlaps( [1..8], [3..5] ) = true
overlaps( [3..5], [1..8] ) = true
overlaps( [1..5], [6..8] ) = false
overlaps( [6..8], [1..5] ) = false
overlaps( [1..5], [5..8] ) = true
overlaps( [1..5], (5..8] ) = false
overlaps( [1..5), [5..8] ) = false
overlaps( [1..5), (5..8] ) = false
overlaps( [5..8], [1..5] ) = true
overlaps( (5..8], [1..5] ) = false
overlaps( [5..8], [1..5) ) = false
overlaps( (5..8], [1..5) ) = false
```

Returns `true` when an element `A` overlaps an element `B` and when
the relevant requirements for evaluating to `true` are also met.

Signatures:

a.  `overlaps( range1, range2 )`

Semantic:

a. `( range1.end > range2.start or (range1.end = range2.start and range1.end included and range2.end included) ) and ( range1.start < range2.end or (range1.start = range2.end and range1.start included and range2.end included) )`

## overlaps before( )

> Examples:

``` {.FEEL}
overlaps before( [1..5], [3..8] ) = true
overlaps before( [1..5], [6..8] ) = false
overlaps before( [1..5], [5..8] ) = true
overlaps before( [1..5], (5..8] ) = false
overlaps before( [1..5), [5..8] ) = false
overlaps before( [1..5), (1..5] ) = true
overlaps before( [1..5], (1..5] ) = true
overlaps before( [1..5), [1..5] ) = false
overlaps before( [1..5], [1..5] ) = false
```

Returns `true` when an element `A` overlaps before an element `B`
and when the relevant requirements for evaluating to `true` are also
met.

Signatures:

a.  `overlaps before( range1 range2 )`

Semantic:

a. `( range1.start < range2.start or (range1.start = range2.start and range1.start included and not(range2.start included)) ) and ( range1.end > range2.start or (range1.end = range2.start and range1.end included and range2.start included) ) and ( range1.end < range2.end or (range1.end = range2.end and (not(range1.end included) or range2.end included )) )`

## overlaps after( )

> Examples:

``` {.FEEL}
overlaps after( [3..8], [1..5] )= true
overlaps after( [6..8], [1..5] )= false
overlaps after( [5..8], [1..5] )= true
overlaps after( (5..8], [1..5] )= false
overlaps after( [5..8], [1..5) )= false
overlaps after( (1..5], [1..5) )= true
overlaps after( (1..5], [1..5] )= true
overlaps after( [1..5], [1..5) )= false
overlaps after( [1..5], [1..5] )= false
overlaps after( (1..5), [1..5] )= false
overlaps after( (1..5], [1..6] )= false
overlaps after( (1..5], (1..5] )= false
overlaps after( (1..5], [2..5] )= false
```

Returns `true` when an element `A` overlaps after an element `B` and
when the relevant requirements for evaluating to `true` are also
met.

Signatures:

a.  `overlaps after( range1 range2 )`

Semantic:

a.  `( range2.start < range1.start or (range2.start = range1.start and range2.start included and not( range1.start included)) ) and ( range2.end > range1.start or (range2.end = range1.start  and range2.end included and range1.start included) ) and ( range2.end < range1.end or (range2.end = range1.end and (not(range2.end included) or range1.end included)) )`

## finishes( )

> Examples:

``` {.FEEL}
finishes( 10, [1..10] ) = true
finishes( 10, [1..10) ) = false
finishes( [5..10], [1..10] ) = true
finishes( [5..10), [1..10] ) = false
finishes( [5..10), [1..10) ) = true
finishes( [1..10], [1..10] ) = true
finishes( (1..10], [1..10] ) = true
```

Returns `true` when an element `A` finishes an element `B` and when
the relevant requirements for evaluating to `true` are also met.

Signatures:

a.  `finishes( point, range )`

b.  `finishes( range1, range2 )`

Semantic:

a.  `range.end included and range.end = point`

b.  `range1.end included = range2.end included and range1.end = range2.end and ( range1.start > range2.start or (range1.start = range2.start and (not(range1.start included) or range2.start included)) )`

## finished by( )

> Examples:

``` {.FEEL}
finished by( [1..10], 10 ) = true
finished by( [1..10), 10 ) = false
finished by( [1..10], [5..10] ) = true
finished by( [1..10], [5..10) ) = false
finished by( [1..10), [5..10) ) = true
finished by( [1..10], [1..10] ) = true
finished by( [1..10], (1..10] ) = true
```

Returns `true` when an element `A` is finished by an element `B` and
when the relevant requirements for evaluating to `true` are also
met.

Signatures:

a.  `finished by( range, point )`

b.  `finished by( range1 range2 )`

Semantic:

a.  `range.end included and range.end = point`

b.  `range1.end included = range2.end included and range1.end = range2.end and ( range1.start < range2.start or (range1.start = range2.start and (range1.start included or not(range2.start included))) )`

## includes( )

> Examples:

``` {.FEEL}
includes( [1..10], 5 ) = true
includes( [1..10], 12 ) = false
includes( [1..10], 1 ) = true
includes( [1..10], 10 ) = true
includes( (1..10], 1 ) = false
includes( [1..10), 10 ) = false
includes( [1..10], [4..6] ) = true
includes( [1..10], [1..5] ) = true
includes( (1..10], (1..5] ) = true
includes( [1..10], (1..10) ) = true
includes( [1..10), [5..10) ) = true
includes( [1..10], [1..10) ) = true
includes( [1..10], (1..10] ) = true
includes( [1..10], [1..10] ) = true
```

Returns `true` when an element `A` includes an element `B` and when
the relevant requirements for evaluating to `true` are also met.

Signatures:

a.  `includes( range, point )`

b.  `includes( range1, range2 )`

Semantic:

a.  `(range.start < point and range.end > point) or (range.start = point and range.start included) or (range.end = point and range.end included)`

b.  `( range1.start < range2.start or (range1.start = range2.start and (range1.start included or not(range2.start included))) ) and ( range1.end > range2.end or (range1.end = range2.end and (range1.end included or not(range2.end included))) )`

## during( )

> Examples:

``` {.FEEL}
during( 5, [1..10] ) = true
during( 12, [1..10] ) = false
during( 1, [1..10] ) = true
during( 10, [1..10] ) = true
during( 1, (1..10] ) = false
during( 10, [1..10) ) = false
during( [4..6], [1..10] ) = true
during( [1..5], [1..10] ) = true
during( (1..5], (1..10] ) = true
during( (1..10), [1..10] ) = true
during( [5..10), [1..10) ) = true
during( [1..10), [1..10] ) = true
during( (1..10], [1..10] ) = true
during( [1..10], [1..10] ) = true
```

Returns `true` when an element `A` is during an element `B` and when
the relevant requirements for evaluating to `true` are also met.

Signatures:

a.  `during( point, range )`

b.  `during( range1 range2 )`

Semantic:

a.  `(range.start < point and range.end > point) or (range.start = point and range.start included) or (range.end = point and range.end included)`

b.  `( range2.start < range1.start or (range2.start = range1.start and (range2.start included or not(range1.start included))) ) and ( range2.end > range1.end or (range2.end = range1.end and (range2.end included or not(range1.end included))) )`

## starts( )

> Examples:

``` {.FEEL}
starts( 1, [1..10] ) = true
starts( 1, (1..10] ) = false
starts( 2, [1..10] ) = false
starts( [1..5], [1..10] ) = true
starts( (1..5], (1..10] ) = true
starts( (1..5], [1..10] ) = false
starts( [1..5], (1..10] ) = false
starts( [1..10], [1..10] ) = true
starts( [1..10), [1..10] ) = true
starts( (1..10), (1..10) ) = true
```

Returns `true` when an element `A` starts an element `B` and when
the relevant requirements for evaluating to `true` are also met.

Signatures:

a.  `starts( point, range )`

b.  `starts( range1, range2 )`

Semantic:

a.  `range.start = point and range.start included`

b.  `range1.start = range2.start and range1.start included = range2.start included and ( range1.end < range2.end or (range1.end = range2.end and (not(range1.end included) or range2.end included)) )`

## started by( )

> Examples:

``` {.FEEL}
started by( [1..10], 1 ) = true
started by( (1..10], 1 ) = false
started by( [1..10], 2 ) = false
started by( [1..10], [1..5] ) = true
started by( (1..10], (1..5] ) = true
started by( [1..10], (1..5] ) = false
started by( (1..10], [1..5] ) = false
started by( [1..10], [1..10] ) = true
started by( [1..10], [1..10) ) = true
started by( (1..10), (1..10) ) = true
```

Returns `true` when an element `A` is started by an element `B` and
when the relevant requirements for evaluating to `true` are also
met.

Signatures:

a.  `started by( range, point )`

b.  `started by( range1, range2 )`

Semantic:

a.  `range.start = point and range.start included`

b.  `range1.start = range2.start and range1.start included = range2.start included and ( range2.end < range1.end or (range2.end = range1.end and (not(range2.end included) or range1.end included)) )`

## coincides( )

> Examples:

``` {.FEEL}
coincides( 5, 5 ) = true
coincides( 3, 4 ) = false
coincides( [1..5], [1..5] ) = true
coincides( (1..5), [1..5] ) = false
coincides( [1..5], [2..6] ) = false
```

Returns `true` when an element `A` coincides with an element `B` and
when the relevant requirements for evaluating to `true` are also
met.

Signatures:

a.  `coincides( point1, point2 )`

b.  `coincides( range1, range2 )`

Semantic:

a.  `point1 = point2`

b.  `range1.start = range2.start and range1.start included = range2.start included and range1.end = range2.end and range1.end included = range2.end included`

# Temporal functions

This chapter explores the DMN FEEL specification built-in functions for temporal operations.

## day of year( date )

> Examples:

``` {.FEEL}
day of year( date(2019, 9, 17) ) = 260
```

| Parameter          | Type                                            |
|-|-|
| `date`             | `date` or `date and time`                       |

Returns the Gregorian number of the day of the year.

## day of week( date )

> Examples:

``` {.FEEL}
day of week( date(2019, 9, 17) ) = "Tuesday"
```

| Parameter          | Type                                            |
|-|-|
| `date`             | `date` or `date and time`                       |

Returns the Gregorian day of the week: `"Monday"`, `"Tuesday"`,
`"Wednesday"`, `"Thursday"`, `"Friday"`, `"Saturday"`, or
`"Sunday"`.

## month of year( date )

> Examples:

``` {.FEEL}
month of year( date(2019, 9, 17) ) = "September"
```

| Parameter          | Type                                            |
|-|-|
| `date`             | `date` or `date and time`                       |

Returns the Gregorian month of the year: `"January"`, `"February"`,
`"March"`, `"April"`, `"May"`, `"June"`, `"July"`, `"August"`,
`"September"`, `"October"`, `"November"`, or `"December"`.

## month of year( date )

| Parameter          | Type                                            |
|-|-|
| `date`             | `date` or `date and time`                       |

Returns the Gregorian week of the year as defined by ISO 8601.

> Examples:

``` {.FEEL}
week of year( date(2019, 9, 17) ) = 38
week of year( date(2003, 12, 29) ) = 1
week of year( date(2004, 1, 4) ) = 1
week of year( date(2005, 1, 1) ) = 53
week of year( date(2005, 1, 3) ) = 1
week of year( date(2005, 1, 9) ) = 1
```

# Sort functions

This chapter explores the DMN FEEL specification built-in functions for sorting operations.

## sort( list, precedes )

> Examples:

``` {.FEEL}
sort( list: [3,1,4,5,2], precedes: function(x,y) x < y ) = [1,2,3,4,5]
```

Returns a list of the same elements but ordered according to the
sorting function.

| Parameter          | Type                                            |
|-|-|
| `list`             | `list`                                          |
| `precedes`         | `function`                                      |

# Context functions

This chapter explores the DMN FEEL specification built-in functions for `context`s.

## get value( m, key )

> Examples:

``` {.FEEL}
get value( {key1 : "value1"}, "key1" ) = "value1"
get value( {key1 : "value1"}, "unexistent-key" ) = null
```

| Parameter          | Type                                            |
|-|-|
| `m`                | `context`                                       |
| `key`              | `string`                                        |

Returns the value from the context for the specified entry key.

## get entries( m )

> Examples:

``` {.FEEL}
get entries( {key1 : "value1", key2 : "value2"} ) =
  [ { key : "key1", value : "value1" }, {key : "key2", value : "value2"} ]
```

| Parameter          | Type                                            |
|-|-|
| `m`                | `context`                                       |

Returns a list of key-value pairs for the specified context.

## context( entries )

> Examples:

``` {.FEEL}
context([{key:"a", value:1}, {key:"b", value:2}]) = {a:1, b:2}
context([{key:"a", value:1}, {key:"b", value:2, something: "else"}]) = {a:1, b:2}
context([{key:"a", value:1}, {key:"b"}]) = null
```

| Parameter          | Type                                            |
|-|-|
| `entries`          | `list` of `context` , each item SHALL have two entries having keys named "key" and "value" respectively                                     |

Returns a new context that includes all specified entries.
If a context item contains additional entries beyond the required "key" and "value" entries, the additional entries are ignored.
If a context item is missing the required "key" and "value" entries, the final result is null.
See also: get entries() built-in function.

## context put( context, key, value )

> Examples:

``` {.FEEL}
context put({x:1}, "y", 2) = {x:1, y:2}
context put({x:1, y:0}, "y", 2) = {x:1, y:2}
context put({x:1, y:0, z:0}, "y", 2) = {x:1, y:2, z:0}
```

| Parameter          | Type                                            |
|-|-|
| `context`          | `context`                                     |
| `key`          | `string`                                     |
| `value`          | `Any` type                                     |

Returns a new context that includes the new entry, or overrides the existing value if an entry for the same key already exists in the supplied context parameter.
A new entry is added as the last entry of the new context.
If overriding an existing entry, the order of the keys maintains the same order as in the original context.

## context put( context, keys, value )

> Examples:

``` {.FEEL}
context put({x:1}, ["y"], 2) = context put({x:1}, "y", 2)
context put({x:1}, ["y"], 2) = {x:1, y:2}
context put({x:1, y: {a: 0} }, ["y", "a"], 2) = context put({x:1, y: {a: 0} }, "y", context put({a: 0}, ["a"], 2))
context put({x:1, y: {a: 0} }, ["y", "a"], 2) = {x:1, y: {a: 2} }
context put({x:1, y: {a: 0} }, [], 2) = null
```

| Parameter          | Type                                            |
|-|-|
| `context`          | `context`                                     |
| `keys`          | `list` of `string`                                     |
| `value`          | `Any` type                                     |

Returns the composite of nested invocations to `context put()` for each item in `keys` hierarchy in context.

If `keys` is a list of 1 element, this is equivalent to `context put(context, key', value)`, where `key'` is the only element in the list keys.

If `keys` is a list of 2 or more elements, this is equivalent of calling `context put(context, key', value')`, with: <br/>
`key'` is the head element in the list keys, <br/>
`value'` is the result of invocation of `context put(context', keys', value)`, where: <br/>
`context'` is the result of `context.key'`, <br/>
`keys'` is the remainder of the list keys without the head element `key'`.

If `keys` is an empty list or null, the result is null.

## context merge( contexts )

> Examples:

``` {.FEEL}
context merge([{x:1}, {y:2}]) = {x:1, y:2}
context merge([{x:1, y:0}, {y:2}]) = {x:1, y:2}
```

| Parameter          | Type                                            |
|-|-|
| `contexts`          | `list` of `context`                                     |

Returns a new context that includes all entries from the given contexts; if some of the keys are equal, the entries are overridden.
The entries are overridden in the same order as specified by the supplied parameter, with new entries added as the last entry in the new context.

# Conversion functions

The following functions support conversion between values of different
types. Some of these functions use specific string formats, such as the
following examples:

-   `date string`: Follows the format defined in the [XML Schema Part 2:
    Datatypes](https://www.w3.org/TR/xmlschema-2/#date) document, such
    as `2020-06-01`

-   `time string`: Follows one of the following formats:

    -   Format defined in the [XML Schema Part 2:
        Datatypes](https://www.w3.org/TR/xmlschema-2/#time) document,
        such as `23:59:00z`

    -   Format for a local time defined by ISO 8601 followed by `@` and
        an IANA Timezone, such as `00:01:00@Etc/UTC`

-   `date time string`: Follows the format of a `date string` followed
    by `T` and a `time string`, such as `2012-12-25T11:00:00Z`

-   `duration string`: Follows the format of `days and time duration`
    and `years and months duration` defined in the [XQuery 1.0 and XPath
    2.0 Data Model](https://www.w3.org/TR/xpath-datamodel/#types), such
    as `P1Y2M`

## date( from: string )

> Examples:

``` {.FEEL}
date( "2012-12-25" ) - date( "2012-12-24" ) = duration( "P1D" )
```

| Parameter          | Type               | Format                    |
|-|-|-|
| `from`             | `string`           | `date string`             |

Converts `from` to a `date` value.

## date( from: date and time )

> Examples:

``` {.FEEL}
date(date and time( "2012-12-25T11:00:00Z" )) = date( "2012-12-25" )
```

| Parameter          | Type                                            |
|-|-|
| `from`             | `date and time`                                 |

Converts `from` to a `date` value and sets time components to null.

## date( year, month, day )

> Examples:

``` {.FEEL}
date( 2012, 12, 25 ) = date( "2012-12-25" )
```

| Parameter          | Type                                            |
|-|-|
| `year`             | `number`                                        |
| `month`            | `number`                                        |
| `day`              | `number`                                        |

Produces a `date` from the specified year, month, and day values.

## date and time( date, time )

> Examples:

``` {.FEEL}
date and time ( "2012-12-24T23:59:00" ) = date and time(date( "2012-12-24" ), time( "23:59:00" ))
```

Produces a `date and time` from the specified date and ignores any
time components and the specified time.

| Parameter          | Type                                            |
|-|-|
| `date`             | `date` or `date and time`                       |
| `time`             | `time`                                          |

## date and time( from )

> Examples:

``` {.FEEL}
date and time( "2012-12-24T23:59:00" ) + duration( "PT1M" ) = date and time( "2012-12-25T00:00:00" )
```

| Parameter          | Type               | Format                    |
|-|-|-|
| `from`             | `string`           | `date time string`        |

Produces a `date and time` from the specified string.

## time( from: string )

> Examples:

``` {.FEEL}
time( "23:59:00z" ) + duration( "PT2M" ) = time( "00:01:00@Etc/UTC" )
```

Produces a `time` from the specified string.

| Parameter          | Type               | Format                    |
|-|-|-|
| `from`             | `string`           | `time string`             |

## time( from: date and time )

> Examples:

``` {.FEEL}
time(date and time( "2012-12-25T11:00:00Z" )) = time( "11:00:00Z" )
```

Produces a `time` from the specified parameter and ignores any date
components.

| Parameter          | Type                                            |
|-|-|
| `from`             | `time` or `date and time`                       |

## time( hour, minute, second, offset? )

> Examples:

``` {.FEEL}
time( "23:59:00z" ) = time(23, 59, 0, duration( "PT0H" ))
```

| Parameter          | Type                                            |
|-|-|
| `hour`             | `number`                                        |
| `minute`           | `number`                                        |
| `second`           | `number`                                        |
| `offset`     (Optional)      | `days and time duration` or null                |

Produces a `time` from the specified hour, minute, and second
component values.

## number( from, grouping separator, decimal separator )

> Examples:

``` {.FEEL}
number( "1 000,0", " ", "," ) = number( "1,000.0", ",", "." )
```

| Parameter          | Type                                            |
|-|-|
| `from`             | `string` representing a valid number            |
| `grouping separator` | Space (` `), comma (`,`), period (`.`), or null   |
| `decimal separator` | Same types as `grouping separator`, but the values cannot match     |

Converts `from` to a `number` using the specified separators.

## string( from )

> Examples:

``` {.FEEL}
string( 1.1 ) = "1.1"
string( null ) = null
```

| Parameter          | Type                                            |
|-|-|
| `from`             | Non-null value                                  |

Provides a string representation of the specified parameter.

## duration( from )

> Examples:

``` {.FEEL}
date and time( "2012-12-24T23:59:00" ) - date and time( "2012-12-22T03:45:00" ) = duration( "P2DT20H14M" )
duration( "P2Y2M" ) = duration( "P26M" )
```

| Parameter          | Type               | Format                    |
|-|-|-|
| `from`             | `string`           | `duration string`         |

Converts `from` to a `days and time duration` value or
`years and months duration` value.

## years and months duration( from, to )

> Examples:

``` {.FEEL}
years and months duration( date( "2011-12-22" ), date( "2013-08-24" ) ) = duration( "P1Y8M" )
```

| Parameter          | Type                                            |
|-|-|
| `from`             | `date` or `date and time`                       |
| `to`               | `date` or `date and time`                       |

Calculates the `years and months duration` between the two specified
parameters.

# Miscellaneous functions

These functions provide support utilities for several miscellaneous use-cases.
For example, when a decision depends on the current date, like deciding the support SLA over the weekends, additional charges for weekend delivery, etc.

It is important to note that the functions in this section are intended to be side-effect-free, but they are not deterministic and not idempotent from the perspective of an external observer.

As a user, you are encouraged to isolate the decision logic that uses these functions in specific DRG elements, such as Decisions.
The encapsulation enables them to be overridden with synthetic values during scenario testing, that remain constant across executions of the DMN model's test cases.

## now()

> Examples:

```text
now()
```

Returns the current `date and time`.

## today()

> Examples:

```text
today()
```

Returns the current `date`.

# KIE Extended functions

These functions are provided as an extension to the DMN Standard to enable support for various use-cases on top of the <a href='https://www.drools.org/learn/dmn.html'>Drools DMN Engine</a>.

## invoke( namespace, modelName, decisionName, parameters )

> Examples:

```text
invoke(
    "http://namespace_of_model",
    "my model name",
    "my decision name",
    { a:1, b:2 }
)
```

| Parameter          | Type                                            |
|-|-|
| `namespace`        | `string`                                        |
| `modelName`        | `string`                                        |
| `decisionName`     | `string`                                        |
| `parameters`       | `context`                                       |

Returns the result of the decision evaluation in the specified DMN model available to the `DMNRuntime` environment in the current DMN model is executed.

This function is _deprecated_ in favor of encouraging the usage of DMN Standard capabilities wherever possible; since DMNv1.2 it shall be possible to use the DMN standard's _Import_ functionality to import Business Knowledge Model (BKM) nodes and Decision Service nodes, to be invoked from another model.
