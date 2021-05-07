package me.rtb

import org.junit.Test

import org.junit.Assert.*

class MemoryTest {

    private val instance = Memory()

    @Test
    fun getStackMap() {
        assertEquals(1, instance.stackMap.size)
    }

    @Test
    fun getStackIndex() {
        assertEquals(0, instance.stackIndex)
        instance.stackIndex=1
        assertNotNull(instance.stack)
        assertEquals(1, instance.stackIndex)
    }

    @Test
    fun setStackIndex() {
        assertNotNull(instance.stack)
        instance.stackIndex = 1
        assertEquals(1, instance.stackIndex)
        assertNotNull(instance.stack)
        instance.stackIndex=0
        assertEquals(0, instance.stackIndex)
        assertNotNull(instance.stack)

    }

    @Test
    fun getStack() {
        assertNotNull(instance.stack)
    }

}