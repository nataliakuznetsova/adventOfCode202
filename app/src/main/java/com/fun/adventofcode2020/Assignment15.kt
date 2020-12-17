package com.`fun`.adventofcode2020

class Assignment15 {

    val input1 = "0,3,6"
    val input2 = "3,1,2"

    val input_last = "0,1,4,13,15,12,16"

    fun getNumberFast2() {
        val input = input_last.split(",").map { it.toInt() }.toMutableList()

        val map = mutableMapOf<String, Int>()
        for (i in input.indices) {
            map["${input[i]}_last"] = i
        }

        //0, 3, 6,  0, 3, 3, 1, 0, 4, 0, 2, 0, 2, 2, 1, 8, 0, 5, 0, 2, 6, 18, 0, 4, 15
        var size = input.size
        while (size != 30_000_000) {
            val num = input.last()

            val last = map["${num}_last"] ?: -1
            val previous = map["${num}_previous"] ?: -1

            // never said before
            if (last == -1) {
                input.add(0)
                map["${num}_last"] = size
            } else {

                if (previous == -1) {
                    input.add(0)
                    map["0_previous"] = map["0_last"]!!
                    map["0_last"] = size
                } else {
                    val nextNum = last - previous
                    input.add(nextNum)

                    val nextNumLast = map["${nextNum}_last"] ?: -1
                    if (nextNumLast != -1) {
                        map["${nextNum}_previous"] = nextNumLast
                    }
                    map["${nextNum}_last"] = size
                }
            }
            size++
        }
        println("30_000_000 fast = ${input[size - 1]}")
    }

    fun getNumber() {
        val input = input1.split(",").map { it.toInt() }.toMutableList()

        while (input.size != 2020) {
            val last = input.last()

            val subList = input.subList(0, input.size - 1)
            val indexOfPrevious = subList.indexOfLast { it == last }

            when (indexOfPrevious) {
                -1 -> input.add(0)
                else -> {
                    val turnNum = input.indexOfLast { it == last }

                    val age = turnNum - indexOfPrevious
                    input.add(age)
                }
            }

        }
        println("$input")
        println("2020 = ${input.last()}")
    }
}