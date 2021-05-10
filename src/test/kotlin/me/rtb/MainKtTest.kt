package me.rtb

import org.junit.Assert.*
import org.junit.Test

class MainKtTest {

    @Test
    fun `main`() {
        val output = mutableListOf<String>()
        val inputs = mutableListOf("1.2", "exit")
        memory.stack.clear()
        reader = { inputs.removeFirst() }
        writer = {output.add(it.toString())}
        main(arrayOf())
        assertEquals(1.2, memory.stack.peek(), Double.MIN_VALUE)
        assertEquals("1.2", output[2])
    }

    @Test
    fun `printTop NaN`() {
        val output = mutableListOf<String>()
        writer = {output.add(it.toString())}
        memory.stack.clear()
        memory.stack.push(Double.NaN)
        printTop()
        assertEquals("NaN", output[0])
    }

    @Test
    fun `printTop empty`() {
        val output = mutableListOf<String>()
        writer = {output.add(it.toString())}
        memory.stack.clear()
        printTop()
        assertEquals("<empty>", output[0])
    }
}