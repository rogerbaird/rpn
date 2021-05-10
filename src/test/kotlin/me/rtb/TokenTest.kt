package me.rtb

import org.junit.Test

import org.junit.Assert.*

class TokenTest {

    @Test
    fun eval() {
    }

    @Test
    fun reduceOperation() {
        val mem = Memory()
        mem.stack.push(2.0)
        mem.stack.push(3.0)
        mem.stack.push(4.0)
        TestToken().reduceOperation(mem, 0.0){a,b->a+b}.also {
            assertEquals( 9.0, mem.stack.peek(), Double.MIN_VALUE)
        }
    }

    @Test
    fun unaryOperation() {
        val mem = Memory()
        mem.stack.push(2.0)
        TestToken().unaryOperation(mem){a->a*a}.also {
            assertEquals( 4.0, mem.stack.peek(), Double.MIN_VALUE)
        }
    }

    @Test
    fun binaryOperation() {
        val mem = Memory()
        mem.stack.push(2.0)
        mem.stack.push(3.0)
        TestToken().binaryOperation(mem){a,b->a+b}.also {
            assertEquals( 5.0, mem.stack.peek(), Double.MIN_VALUE)
        }
    }

    @Test
    fun ternaryOperation() {
        val mem = Memory()
        mem.stack.push(2.0)
        mem.stack.push(3.0)
        mem.stack.push(4.0)
        TestToken().ternaryOperation(mem){ a, b, c->a*b*c}.also {
            assertEquals( 24.0, mem.stack.peek(), Double.MIN_VALUE)
        }
    }

    @Test
    fun quaternaryOperation() {
        val mem = Memory()
        mem.stack.push(2.0)
        mem.stack.push(3.0)
        mem.stack.push(4.0)
        mem.stack.push(1.0)
        TestToken().quaternaryOperation(mem){ a, b, c, d->a*b*c-d}.also {
            assertEquals( 23.0, mem.stack.peek(), Double.MIN_VALUE)
        }
    }

    @Test
    fun swap() {
        val a = 2.0
        val b = 3.0
        TestToken().swap(a,b).also {
            assertEquals( b, it[0], Double.MIN_VALUE)
            assertEquals( a, it[1], Double.MIN_VALUE)
        }

    }

    class TestToken : Token() {
        override fun eval(s: Memory, out: (Any)->Unit): Boolean {
            TODO("Not yet implemented")
        }

    }
}