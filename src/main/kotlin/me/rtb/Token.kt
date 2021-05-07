package me.rtb

abstract class Token {
    abstract fun eval(s: Memory,  out: (Any)->Unit) : Boolean

    fun reduceOperation( s: Memory, initial: Double, op: (Double,Double)->Double) {

        if (!s.stack.isEmpty()) {
            var agg = initial
            while (!s.stack.isEmpty())
                agg = op.invoke(agg, s.stack.pop())
            s.stack.push(agg)
        }
    }

    fun unaryOperation( s: Memory, op: (Double)->Double) {
        s.stack.push(op(s.stack.pop()))
    }

    fun binaryOperation( s: Memory, op: (Double,Double)->Double) {
        val n1 = s.stack.pop()
        val n2 = s.stack.pop()
        s.stack.push(op(n2,n1))
    }

    fun swap(a: Double, b: Double) : List<Double> { return listOf(b, a) }
}





