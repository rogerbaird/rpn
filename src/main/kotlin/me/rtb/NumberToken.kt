package me.rtb

data class NumberToken (private val n: Double) : Token() {
    override fun eval(mem: Memory,  out: (Any)->Unit) : Boolean {
        mem.stack.push(n)
        return false
    }
}