package me.rtb

import org.junit.After
import org.junit.Assert.*
import org.junit.Test
import java.io.ByteArrayInputStream

class MainKtTest {

    private var savedReader : ()->String = reader

    @After
    fun restoreValues() {
        reader = savedReader
    }

    @Test
    fun `main`() {
        val output = mutableListOf<String>()
        val inputs = mutableListOf("1.2", "exit")
        memory.stack.clear()
        reader = { inputs.removeFirst() }
        writer = {output.add(it.toString())}
        main(arrayOf())
        assertEquals(1.2, memory.stack.peek(), Double.MIN_VALUE)
        assertEquals("1.2", output[0])
    }

    @Test
    fun `main with eof`() {
        val output = mutableListOf<String>()
        val inputs = mutableListOf("1.2", "exit")
        memory.stack.clear()
        reader = { inputs.removeFirst() }
        writer = {output.add(it.toString())}
        main(arrayOf())
        assertEquals(1.2, memory.stack.peek(), Double.MIN_VALUE)
        assertEquals("1.2", output[0])
    }

    @Test
    fun `main with args`() {
        val output = mutableListOf<String>()
        memory.stack.clear()
        writer = {output.add(it.toString())}
        main(arrayOf("2", "3", "+"))
        assertEquals(5.0, memory.stack.peek(), Double.MIN_VALUE)
        assertEquals("5.0", output[0])
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

    @Test
    fun `exit on eof`() {
        val oldIn = System.`in`
        System.setIn(ByteArrayInputStream(byteArrayOf()))
        reader.invoke().also {
            System.setIn(oldIn)
            assertEquals("exit", it)
        }
    }
}