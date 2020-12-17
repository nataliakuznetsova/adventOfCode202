package com.`fun`.adventofcode2020

import java.util.*

class Assignment13 {

    fun getBus() {
        val inputs = ReadFileUtils.readFile("src/input13.txt").split("\n")

        val time = inputs[0].toLong()

        val buses = inputs[1].split(",")
            .filter { it != "x" }
            .map { it.toLong() }

        var min = Long.MAX_VALUE
        var bus = 0L

        buses.forEach {
            val last = time - time.rem(it)
            val next = last + it
            val wait = next - time
            if (wait < min) {
                min = wait
                bus = it
            }
        }

        print("min = ${min * bus}")
    }

    fun getSequence() {
        val inputs = ReadFileUtils.readFile("src/input13.txt").split("\n")

        val buses = inputs[1].split(",")
            .filter { it != "x" }
            .map { it.toLong() }

        val timetable = inputs[1].split(",")
        val offsets = mutableListOf<Int>()

        for (item in timetable) {
            if (item != "x") {
                offsets.add(timetable.indexOf(item))
            }
        }

        val currentOffsets = mutableListOf<Long>()
        val currentNum = mutableListOf<Long>()
        repeat(offsets.size) {
            currentOffsets.add(0)
            currentNum.add(0L)
        }

        while (currentOffsets != offsets) {

            for (i in currentNum.indices) {
                currentNum[i] += buses[i]
                currentOffsets[i] = currentNum[i] - currentNum[0]
            }

            println("bus 7 = ${currentNum.first()}")
        }

        print("offsets = ${currentNum.last()}")
    }

    fun getSequence2() {
        val inputs = ReadFileUtils.readFile("src/input13.txt").split("\n")

        val buses = inputs[1].split(",")
            .filter { it != "x" }
            .map { it.toLong() }

        val timetable = inputs[1].split(",")
        val offsets = mutableListOf<Long>()

        for (item in timetable) {
            if (item != "x") {
                offsets.add(timetable.indexOf(item).toLong())
            }
        }

        val currentOffsets = mutableListOf<Long>()

        repeat(offsets.size) {
            currentOffsets.add(0)
        }

        val max = buses.max()
        val maxIndex = buses.indexOf(max)

        var currentMax = buses.reduce { a, b -> a * b }

        val start = Date()
        while (currentOffsets != offsets) {
            currentMax -= buses[maxIndex]

            val firstBusTime = getTime(currentMax, buses[0])
            for (i in 1 until buses.size) {
                currentOffsets[i] = getOffset(firstBusTime, buses[i])
                if (currentOffsets[i] != offsets[i]) break
            }
        }

        print("offsets = ${getTime(currentMax, buses[0])}")
    }

    private fun getTime(time: Long, bus: Long): Long {
        return time - time.rem(bus)
    }

    private fun getOffset(time: Long, bus: Long): Long {
        val rem = time.rem(bus)
        if (rem == 0L) return 0
        val last = time - rem
        val next = last + bus
        return next - time
    }
}