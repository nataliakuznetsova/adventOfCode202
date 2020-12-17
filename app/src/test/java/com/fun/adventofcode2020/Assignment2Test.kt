package com.`fun`.adventofcode2020

import junit.framework.TestCase
import org.junit.Test

class Assignment2Test : TestCase() {

    @Test
    internal fun test1() {
        val assign2 = Assignment2()

        assign2.validate()
    }

    @Test
    internal fun test2() {
        val assign2 = Assignment2()

        assign2.validate2()
    }

}