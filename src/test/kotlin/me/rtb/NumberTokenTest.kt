package me.rtb

import org.junit.Test

import org.junit.Assert.*
import java.util.*

class NumberTokenTest {

    @Test
    fun eval() {
        val mem = Memory()
        val num : Double = 12.0
        val instance = NumberToken(num)

        assertFalse(instance.eval(mem){})
        assertEquals(num, mem.stack.peek(), Double.MIN_VALUE)

    }
}