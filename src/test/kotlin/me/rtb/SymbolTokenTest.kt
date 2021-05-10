package me.rtb

import org.junit.Assert.*
import org.junit.Test

class SymbolTokenTest {
    val mem = Memory()

    @Test
    fun `abs`() {
        mem.stack.push(-1.0)
        assertFalse(SymbolToken("abs").eval(mem) {})
        assertEquals(1.0, mem.stack.peek(), Double.MIN_VALUE)
    }

    @Test
    fun `acos`() {
        mem.stack.push(0.0)
        assertFalse(SymbolToken("acos").eval(mem) {})
        assertEquals(1.5707, mem.stack.peek(), 0.01)
    }

    @Test
    fun `asin`() {
        mem.stack.push(1.0)
        assertFalse(SymbolToken("asin").eval(mem) {})
        assertEquals(1.5707, mem.stack.peek(), 0.01)
    }

    @Test
    fun `atan`() {
        mem.stack.push(1.0)
        assertFalse(SymbolToken("atan").eval(mem) {})
        assertEquals(0.7853, mem.stack.peek(), 0.01)
    }

    @Test
    fun `clear`() {
        mem.stack.push(1.0)
        assertFalse(SymbolToken("clear").eval(mem) {})
        assertTrue(mem.stack.isEmpty())
    }

    @Test
    fun `copy`() {
        mem.stack.push(1.0)
        mem.stackIndex=1
        mem.stack.push(2.0)
        mem.stack.push( 0.0)
        assertFalse(SymbolToken("copy").eval(mem) {})
        mem.stackIndex=0
        assertEquals(2.0, mem.stack.peek(), Double.MIN_VALUE)
    }

    @Test
    fun `cos`() {
        mem.stack.push(0.0)
        assertFalse(SymbolToken("cos").eval(mem) {})
        assertEquals(1.0, mem.stack.peek(), Double.MIN_VALUE)
    }

    @Test
    fun `deg`() {
        mem.stack.push(Math.PI)
        assertFalse(SymbolToken("deg").eval(mem) {})
        assertEquals(180.0, mem.stack.peek(), Double.MIN_VALUE)
    }

    @Test
    fun `dup`() {
        mem.stack.push(Math.PI)
        assertFalse(SymbolToken("dup").eval(mem) {})
        assertEquals(Math.PI, mem.stack.pop(), Double.MIN_VALUE)
        assertEquals(Math.PI, mem.stack.pop(), Double.MIN_VALUE)
        assertTrue(mem.stack.isEmpty())
    }

    @Test
    fun `e`() {
        assertFalse(SymbolToken("e").eval(mem) {})
        assertEquals(Math.E, mem.stack.peek(), Double.MIN_VALUE)
    }

    @Test
    fun `fv`() {
        mem.stack.push(1000.0)
        mem.stack.push(0.1)
        mem.stack.push(2.0)
        mem.stack.push(3.0)
        assertFalse(SymbolToken("fv").eval(mem) {})
        assertEquals(1340.0956, mem.stack.pop(), 0.001)
    }

    @Test
    fun `hex`() {
        val output = mutableListOf<String>()
        writer = { output.add(it.toString()) }
        mem.stack.push(2.0)
        assertFalse(SymbolToken("hex").eval(mem, writer))
        assertEquals("[0x2]", output[0])
    }

    @Test
    fun `if`() {
        mem.stack.push(0.0)
        mem.stack.push(10.0)
        mem.stack.push(20.0)
        assertFalse(SymbolToken("if").eval(mem) {})
        assertEquals(20.0, mem.stack.peek(), 0.001)
        mem.stack.clear()
        mem.stack.push(1.0)
        mem.stack.push(10.0)
        mem.stack.push(20.0)
        assertFalse(SymbolToken("if").eval(mem) {})
        assertEquals(10.0, mem.stack.peek(), 0.001)
    }

    @Test
    fun `inv`() {
        mem.stack.push(10.0)
        assertFalse(SymbolToken("inv").eval(mem) {})
        assertEquals(0.1, mem.stack.peek(), Double.MIN_VALUE)
    }

    @Test
    fun `ln`() {
        mem.stack.push(10.0)
        assertFalse(SymbolToken("ln").eval(mem) {})
        assertEquals(2.3025, mem.stack.peek(), 0.001)
    }

    @Test
    fun `log`() {
        mem.stack.push(10.0)
        mem.stack.push(20.0)
        assertFalse(SymbolToken("log").eval(mem) {})
        assertEquals(0.7686, mem.stack.peek(), 0.001)
    }

    @Test
    fun `log2`() {
        mem.stack.push(16.0)
        assertFalse(SymbolToken("log2").eval(mem) {})
        assertEquals(4.0, mem.stack.peek(), Double.MIN_VALUE)
    }

    @Test
    fun `neg`() {
        mem.stack.push(-2.0)
        assertFalse(SymbolToken("neg").eval(mem) {})
        assertEquals(2.0, mem.stack.peek(), Double.MIN_VALUE)
    }

    @Test
    fun `pi`() {
        assertFalse(SymbolToken("pi").eval(mem) {})
        assertEquals(Math.PI, mem.stack.peek(), Double.MIN_VALUE)
    }

    @Test
    fun `pop`() {
        mem.stack.push(2.0)
        mem.stack.push(3.0)
        assertFalse(SymbolToken("pop").eval(mem) {})
        assertEquals(2.0, mem.stack.peek(), Double.MIN_VALUE)
    }

    @Test
    fun `product`() {
        mem.stack.push(2.0)
        mem.stack.push(3.0)
        mem.stack.push(4.0)
        assertFalse(SymbolToken("product").eval(mem) {})
        assertEquals(24.0, mem.stack.peek(), Double.MIN_VALUE)
    }

    @Test
    fun `pv`() {
        mem.stack.push(1000.0)
        mem.stack.push(0.1)
        mem.stack.push(2.0)
        mem.stack.push(3.0)
        assertFalse(SymbolToken("pv").eval(mem) {})
        assertEquals(746.2153, mem.stack.pop(), 0.001)
    }

    @Test
    fun `rad`() {
        mem.stack.push(180.0)
        assertFalse(SymbolToken("rad").eval(mem) {})
        assertEquals(Math.PI, mem.stack.peek(), Double.MIN_VALUE)
    }

    @Test
    fun `range`() {
        mem.stack.push(2.0)
        mem.stack.push(4.0)
        assertFalse(SymbolToken("range").eval(mem) {})
        assertEquals(4.0, mem.stack.pop(), Double.MIN_VALUE)
        assertEquals(3.0, mem.stack.pop(), Double.MIN_VALUE)
        assertEquals(2.0, mem.stack.pop(), Double.MIN_VALUE)
        assertTrue(mem.stack.isEmpty())
    }

    @Test
    fun `show`() {
        val output = mutableListOf<String>()
        writer = { output.add(it.toString()) }
        mem.stack.push(2.0)
        assertFalse(SymbolToken("show").eval(mem, writer))
        assertEquals("[2.0]", output[0])
    }

    @Test
    fun `sin`() {
        mem.stack.push(Math.PI/2)
        assertFalse(SymbolToken("sin").eval(mem) {})
        assertEquals(1.0, mem.stack.peek(), Double.MIN_VALUE)
    }

    @Test
    fun `stack`() {
        mem.stack.push(2.0)
        mem.stackIndex = 1
        mem.stack.push(3.0)
        mem.stack.push(0.0)
        assertFalse(SymbolToken("stack").eval(mem) {})
        assertEquals(2.0, mem.stack.peek(), Double.MIN_VALUE)
    }

    @Test
    fun `sum`() {
        mem.stack.push(2.0)
        mem.stack.push(3.0)
        mem.stack.push(4.0)
        assertFalse(SymbolToken("sum").eval(mem) {})
        assertEquals(9.0, mem.stack.peek(), Double.MIN_VALUE)
    }

    @Test
    fun `swap`() {
        mem.stack.push(2.0)
        mem.stack.push(3.0)
        assertFalse(SymbolToken("swap").eval(mem) {})
        assertEquals(2.0, mem.stack.pop(), Double.MIN_VALUE)
        assertEquals(3.0, mem.stack.pop(), Double.MIN_VALUE)
        assertTrue(mem.stack.isEmpty())
    }

    @Test
    fun `tan`() {
        mem.stack.push(10.0)
        assertFalse(SymbolToken("tan").eval(mem) {})
        assertEquals(0.64836, mem.stack.peek(), 0.0001)
    }

    @Test
    fun `show NaN`() {
        val output = mutableListOf<String>()
        writer = { output.add(it.toString()) }
        mem.stack.push(Double.NaN)
        assertFalse(SymbolToken("show").eval(mem, writer))
        assertEquals("[NaN]", output[0])
    }

    @Test
    fun `unrecogonized`() {
        val output = mutableListOf<String>()
        writer = { output.add(it.toString()) }
        assertFalse(SymbolToken("gobbledygooknonsense").eval(mem, writer))
        assertTrue(output[0].contains("unrecognized"))
    }

}