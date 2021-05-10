
package me.rtb

import java.math.MathContext

var reader = { readLine() ?: "exit" }
var writer : (Any)->Unit = {x->if (x==":") System.out.print(x) else System.out.println(x)}
var ewriter : (Any)->Unit = {x->if (x==":") System.err.print(x) else System.err.println(x)}
val memory = Memory()

fun main(args: Array<String>) {

    if (args.isNotEmpty()) {
        runBatch(args.joinToString (separator=" "))
    }
    else {
        ewriter("RPN Calculator version 1.0")
        runInterpreter ()
    }
}

fun runBatch( cmd : String ) {
    Tokenizer().tokenize(cmd).forEach { it.eval(memory, writer) }
    printTop()
}

fun runInterpreter(){
    var exitFlag = false
    while(!exitFlag) {
        ewriter(":")
        exitFlag = Tokenizer().tokenize(reader.invoke())
            .map { it.eval(memory, writer) }
            .firstOrNull() ?: false
        printTop()
    }
}

fun printTop() {
    if (!memory.stack.empty()) {
        memory.stack.peek().also {
            if (it.isNaN())
                writer("NaN")
            else
                writer("${it.toBigDecimal(MathContext.DECIMAL128).toPlainString()}")
        }
    }
    else
        writer("<empty>")
}

