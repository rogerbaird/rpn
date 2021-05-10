# rpn
###*An interactive calculator that uses **R**everse **P**olish **N**otation*

Note: All operations are performed as IEEE 64-bit floating point.
###Operators

<table>
<tr><th>Operator</th><th>Purpose</th><th>Effect</th></tr>
<tr><td>+</td><td>addition</td><td>pops n1, n2 off of the stack, then pushes n1+n2 onto the stack</td></tr>
<tr><td>-</td><td>subtraction</td><td>pops n1, n2 off of the stack, then pushes n1-n2 onto the stack</td></tr>
<tr><td>*</td><td>multiplication</td><td>pops n1, n2 off of the stack, then pushes n1*n2 onto the stack</td></tr>
<tr><td>/</td><td>division</td><td>pops n1, n2 off of the stack, then pushes n1/n2 onto the stack</td></tr>
<tr><td>%</td><td>modulus</td><td>pops n1, n2 off of the stack, then pushes n1 mod n2 onto the stack</td></tr>
<tr><td>^</td><td>exponent</td><td>pops n1, n2 off of the stack, then pushes n1 to the n2 power onto the stack</td></tr>

</table>

### Symbols
<table>
<tr><th>Symbol</th><th>Purpose</th><th>Effect</th></tr>
<tr><td>abs</td><td>absolute value</td><td>pops n1 off of the stack, then pushes the absolute value of n1 onto the stack</td></tr>
<tr><td>acos</td><td>arc cosine</td><td>pops n1 off of the stack, then pushes the arc cosine of n1 onto the stack</td></tr>
<tr><td>asin</td><td>arc sine</td><td>pops n1 off of the stack, then pushes the arc sine of n1 onto the stack</td></tr>
<tr><td>atan</td><td>arc tangent</td><td>pops n1 off of the stack, then pushes the arc tangent of n1 onto the stack</td></tr>
<tr><td>clear</td><td>clear stack</td><td>sets the current stack to zero elements</td></tr>
<tr><td>copy</td><td>copy top element</td><td>pops n1 off of the current stack, pushes a copy of the resulting top item onto stack number n1</td></tr>
<tr><td>cos</td><td>cosine</td><td>pops n1 off of the stack, then pushes the cosine of n1 onto the stack</td></tr>
<tr><td>deg</td><td>convert to degrees</td><td>pops n1 off of the stack, then converts it from radians to degrees, then pushes the result onto the stack</td></tr>
<tr><td>dup</td><td>duplicate</td><td>pushes a copy of the top element of the current stack onto the current stack</td></tr>
<tr><td>e</td><td>natural number e</td><td>pushes the value of the natural number e onto the current stack</td></tr>
<tr><td>exit</td><td>exit</td><td>exits interactive mode</td></tr>


</table>


### Examples
<table>
<tr><th>Example</th><th>Result</th></tr>
<tr><td>34</td><td>pushes 34.0 onto the current stack</td></tr>
<tr><td>2 3.1 +</td><td>top of stack contains 5.1</td></tr>
<tr><td>2 3.1 +</td><td>top of stack contains 5.1</td></tr>
<tr><td>2 3.1 + 2 /</td><td>top of stack contains 2.55</td></tr>
<tr><td>2 8 2 + /</td><td>top of stack contains 0.2</td></tr>
<tr><td>-4 abs</td><td>top of stack contains 4.0</td></tr>
</table>