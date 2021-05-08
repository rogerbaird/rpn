package me.rtb

abstract class Token {
    abstract fun eval(mem: Memory,  out: (Any)->Unit) : Boolean

    fun reduceOperation( mem: Memory, initial: Double, op: (Double,Double)->Double) {

        if (!mem.stack.isEmpty()) {
            var agg = initial
            while (!mem.stack.isEmpty())
                agg = op.invoke(agg, mem.stack.pop())
            mem.stack.push(agg)
        }
    }

    fun unaryOperation( mem: Memory, op: (Double)->Double) {
        mem.stack.push(op(mem.stack.pop()))
    }

    fun binaryOperation( s: Memory, op: (Double,Double)->Double) {
        val n1 = s.stack.pop()
        val n2 = s.stack.pop()
        s.stack.push(op(n2,n1))
    }

    fun swap(a: Double, b: Double) : List<Double> { return listOf(b, a) }
}





