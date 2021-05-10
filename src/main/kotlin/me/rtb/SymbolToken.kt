package me.rtb

import java.math.MathContext
import java.util.*
import kotlin.math.ln
import kotlin.math.log
import kotlin.math.log2
import kotlin.math.pow

data class SymbolToken( private val symbol: String) : Token() {
    override fun eval(mem: Memory, out: (Any)->Unit) : Boolean {
        when(symbol) {
            "abs" -> unaryOperation(mem, Math::abs)
            "acos" -> unaryOperation(mem, Math::acos)
            "asin" -> unaryOperation(mem, Math::asin)
            "atan" -> unaryOperation(mem, Math::atan)
            "clear" -> mem.stack.clear()
            "copy" -> mem.stackMap.getOrDefault(mem.stack.pop().toInt(), Stack<Double>()).push(mem.stack.peek())
            "cos" -> unaryOperation(mem, Math::cos)
            "deg" -> unaryOperation(mem) { n1->n1*180/Math.PI}
            "dup" -> mem.stack.push(mem.stack.peek())
            "e" -> mem.stack.push(Math.E)
            "exit" -> return true
            "fv" -> quaternaryOperation(mem) {pv,i,n,t -> pv*((1.0+(i/n)).pow(n*t))}
            "hex" -> out.invoke("[0x${mem.stack.peek().toULong().toString(16)}]")
            "inv" -> unaryOperation(mem) { n1->1.0/n1}
            "ln" -> unaryOperation(mem) { n1->ln(n1)}
            "log" -> binaryOperation(mem) { n1, n2-> log(n1,n2)}
            "log2" -> unaryOperation(mem) { n1-> log2(n1)}
            "neg" -> unaryOperation(mem) { n1->-1.0*n1}
            "pi" -> mem.stack.push(Math.PI)
            "pop" -> mem.stack.pop()
            "product" -> reduceOperation(mem, 1.0){ x, y->x*y}
            "pv" -> quaternaryOperation(mem) {fv,i,n,t -> fv/((1.0+(i/n)).pow(n*t))}
            "rad" -> unaryOperation(mem) { n1->n1*Math.PI/180}
            "range" -> {
                val high = mem.stack.pop().toInt()
                val low = mem.stack.pop().toInt()
                IntRange(low,high).forEach { mem.stack.push(it.toDouble()) }
            }
            "show" -> out.invoke(mem.stack.map(::format))
            "sin" -> unaryOperation(mem, Math::sin)
            "stack" -> mem.stackIndex = mem.stack.pop().toInt()
            "sum" -> reduceOperation(mem, 0.0){ x, y->x+y}
            "swap" -> {
                swap(mem.stack.pop(), mem.stack.pop()).also {
                    mem.stack.push(it[1])
                    mem.stack.push(it[0])
                }
            }
            "tan" -> unaryOperation(mem, Math::tan)
            else -> out.invoke("unrecognized symbol <$symbol>")
        }
        return false
    }

    private fun format(num: Double) : String {
        return if (num.isNaN())
            num.toString()
        else
            num.toBigDecimal(MathContext.DECIMAL128).toPlainString()
    }
}