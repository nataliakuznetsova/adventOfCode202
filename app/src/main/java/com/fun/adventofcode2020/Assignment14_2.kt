package com.`fun`.adventofcode2020

import kotlin.math.pow

class Assignment14_2 {

    fun getSum() {
        val inputs = ReadFileUtils.readFile("src/input14.txt").split("\n")

        val result = mutableMapOf<Long, Long>()

        var i = 0
        while (i < inputs.size) {
            val mask = inputs[i]
            val decimalMask = mask.replace("mask = ", "")

            var command = inputs[++i]
            while (!command.contains("mask")) {
                val addresses = getMem(command, decimalMask)

                for (address in addresses) {
                    result[address.first] = address.second
                }

                ++i
                if (i == inputs.size) break
                command = inputs[i]
            }
        }

        print("sum = ${result.values.sum()}")
    }

    private fun getMem(command: String, mask: String): List<Pair<Long, Long>> {
        val mem = command.substring(command.indexOf("[") + 1, command.indexOf("]")).toLong()
        val num = command.substring(command.indexOf("=") + 2).toLong()

        var memStr = mem.toString(2).padStart(mask.length, '0')

        for (i in mask.indices) {
            if (mask[i] == '0') continue

            if (mask[i] == '1') memStr = memStr.replaceRange(i, i + 1, "1")
            if (mask[i] == 'X') memStr = memStr.replaceRange(i, i + 1, "X")
        }

        val addresses = mutableListOf<String>()
        val countFloating = memStr.count { it == 'X' }
        val number = 2.0.pow(countFloating).toInt()

        binaryStrings(addresses, number, memStr,  memStr.indexOf("X"))

        val result = mutableListOf<Pair<Long, Long>>()
        for (address in addresses) {
            val pair = Pair(toDecimal(address).toLong(), num)
            result.add(pair)
        }

        return result
    }

    private fun toDecimal(number: String): Double {
        var sum = 0.0
        number.reversed().forEachIndexed { k, v ->
            sum += v.toString().toInt() * 2.0.pow(k.toDouble())
        }
        return sum
    }

    private fun binaryStrings(result: MutableList<String>, total: Int, string: String, nextIndex: Int) : List<String>{
        if (!string.contains("X")) {
            result.add(string)
            return result
        }

        val string0 = string.replaceRange(nextIndex, nextIndex+1, "0")
        binaryStrings(result, total, string0, string0.indexOf("X"))

        val string1 = string.replaceRange(nextIndex, nextIndex+1, "1")
        binaryStrings(result, total, string1,  string1.indexOf("X"))

        return result
    }
}