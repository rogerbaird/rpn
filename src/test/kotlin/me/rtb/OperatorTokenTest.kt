package me.rtb

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

class OperatorTokenTest {

    @Test
    fun `minus`() {
        testBinaryOperation('-', 9.0)
    }

    @Test
    fun `plus`() {
        testBinaryOperation('+', 13.0)
    }

    @Test
    fun `multiply`() {
        testBinaryOperation('*', 22.0)
    }

    @Test
    fun `divide`() {
        testBinaryOperation('/', 5.5)
    }

    @Test
    fun `modulus`() {
        testBinaryOperation('%', 1.0)
    }

    @Test
    fun `power`() {
        testBinaryOperation('^', 121.0)
    }

    private fun testBinaryOperation( c : Char, expected: Double) {
        val mem = Memory()
        mem.stack.push(11.0)
        mem.stack.push(2.0)
        assertFalse(OperatorToken(c).eval(mem){})
        assertEquals(1, mem.stack.size)
        assertEquals(expected, mem.stack.peek(), Double.MIN_VALUE)
    }
}