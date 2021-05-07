package me.rtb

import java.util.*

class Memory {
    private val defaultStack = Stack<Double>()
    val stackMap = mutableMapOf( 0 to defaultStack)
    var stackIndex = 0
    var stack: Stack<Double> = defaultStack
        get() =  stackMap.getOrPut(stackIndex) { Stack<Double>() }
}