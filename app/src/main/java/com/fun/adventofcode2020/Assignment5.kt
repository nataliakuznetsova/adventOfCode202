package com.`fun`.adventofcode2020

import kotlin.math.pow

class Assignment5 {

    fun get() {
        val tickets = ReadFileUtils.readFile("src/input5.txt").split("\n")
        val ids = mutableListOf<Int>()

        for (ticket in tickets) {
            var row = ticket.substring(0, 7)
            row = row.replace("F", "0").replace("B", "1")
            val rowNumber = toDecimal(row).toInt()

            var column = ticket.substring(7, 10)
            column = column.replace("L", "0").replace("R", "1")
            val columnNumber = toDecimal(column).toInt()

            val id = rowNumber * 8 + columnNumber
            ids.add(id)
            ids.sort()
        }

        for (i in 2 until ids.size) {
            val diff = ids[i - 1] - ids[i - 2]
            if (diff == 2) {
                println("mine = ${ids[i- 1] - 1}")
                break
            }
        }
        println("higherst = $ids")
    }

    private fun toDecimal(number: String): Double {
        var sum = 0.0
        number.reversed().forEachIndexed { k, v ->
            sum += v.toString().toInt() * 2.0.pow(k.toDouble())
        }
        return sum
    }
}