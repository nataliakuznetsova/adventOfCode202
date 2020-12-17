package com.`fun`.adventofcode2020

import kotlin.math.pow

class Assignment14 {

    fun getSum() {
        val inputs = ReadFileUtils.readFile("src/input14.txt").split("\n")

        val result = mutableMapOf<Long, Long>()

        var i = 0
        while (i < inputs.size) {
            val mask = inputs[i]
            val decimalMask = mask.replace("mask = ", "")

            var command = inputs[++i]
            while (!command.contains("mask")) {
                val c = getMem(command, decimalMask)
                result[c.first] = c.second

                ++i
                if (i == inputs.size) break
                command = inputs[i]
            }
        }

        print("sum = ${result.values.sum()}")
    }

    private fun getMem(command: String, mask: String): Pair<Long, Long> {
        val mem = command.substring(command.indexOf("[") + 1, command.indexOf("]")).toLong()
        val num = command.substring(command.indexOf("=") + 2).toLong()

        var numStr = num.toString(2).padStart(mask.length, '0')

        for (i in mask.indices) {
            if (mask[i] == 'X') continue

            if (mask[i] == '1') numStr = numStr.replaceRange(i, i + 1, "1")
            if (mask[i] == '0') numStr = numStr.replaceRange(i, i + 1, "0")
        }

        return Pair(mem, toDecimal(numStr).toLong())
    }

    private fun toDecimal(number: String): Double {
        var sum = 0.0
        number.reversed().forEachIndexed { k, v ->
            sum += v.toString().toInt() * 2.0.pow(k.toDouble())
        }
        return sum
    }
}