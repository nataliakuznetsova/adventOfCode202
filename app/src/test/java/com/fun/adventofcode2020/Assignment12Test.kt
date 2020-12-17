package com.`fun`.adventofcode2020

import junit.framework.TestCase

class Assignment12Test : TestCase(){

    fun testName() {
        val a = Assignment12_2()

        val test = a.navigate("src/input12.txt")

        assertEquals(286, test)
    }
}