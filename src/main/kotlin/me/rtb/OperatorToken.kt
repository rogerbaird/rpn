package me.rtb

import kotlin.math.pow

data class OperatorToken(private val c: Char) : Token() {
    override fun eval(mem: Memory, out: (Any)->Unit) : Boolean {
        when (c) {
            '-' ->  binaryOperation(mem) { n1, n2->n1-n2}
            '+' ->  binaryOperation(mem) { n1, n2->n1+n2}
            '*' ->  binaryOperation(mem) { n1, n2->n1*n2}
            '/' ->  binaryOperation(mem) { n1, n2->n1/n2}
            '%' ->  binaryOperation(mem) { n1, n2->n1 % n2}
            '^' ->  binaryOperation(mem) { n1, n2-> n1.pow(n2) }
        }
        return false
    }
}