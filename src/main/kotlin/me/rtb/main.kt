
package me.rtb

import java.math.MathContext

var reader = {readLine()!!}
var writer : (Any)->Unit = {x->if (x==":") print(x) else println(x)}
val memory = Memory()

fun main(args: Array<String>) {
    writer("RPN Calculator version 1.0")
    runInterpreter ()
}

fun runInterpreter(){

    while(true) {
        writer(":")

        Tokenizer().tokenize(reader.invoke()).forEach {
            if (it.eval(memory, writer)) return
        }

        printTop()
    }
}

fun printTop() {
    if (!memory.stack.empty()) {
        memory.stack.peek().also {
            if (it.isNaN())
                writer("[NaN]")
            else
                writer("[${it.toBigDecimal(MathContext.DECIMAL128).toPlainString()}]")
        }
    }
    else
        writer("[]")
}