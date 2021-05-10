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
        val n2 = s.stack.pop()
        val n1 = s.stack.pop()
        s.stack.push(op(n1,n2))
    }

    fun ternaryOperation(s: Memory, op: (Double, Double, Double)->Double) {
        val n3 = s.stack.pop()
        val n2 = s.stack.pop()
        val n1 = s.stack.pop()
        s.stack.push(op(n1,n2,n3))
    }

    fun quaternaryOperation( s: Memory, op: (Double,Double,Double,Double)->Double) {
        val n4 = s.stack.pop()
        val n3 = s.stack.pop()
        val n2 = s.stack.pop()
        val n1 = s.stack.pop()
        s.stack.push(op(n1,n2,n3,n4))
    }

    fun swap(a: Double, b: Double) : List<Double> { return listOf(b, a) }
}





