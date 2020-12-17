package com.`fun`.adventofcode2020

import kotlin.math.*

class Assignment12 {

    /*
     N
    W E
     S

     F10 -> east 10, north 0.
     N3 -> east 10, north 3.
     F7 -> east 17, north 3.
     R90 -> east 17, north 3.
     F11 -> east 17, south 8.

     */

    fun navigate(fileName: String): Int {
        val inputs = ReadFileUtils.readFile(fileName).split("\n")

        var currentIndex = 1
        val NESW = arrayOf(0, 0, 0, 0)

        for (command in inputs) {
            val direction = command.substring(0, 1)
            val count = command.substring(1).toInt()

            when (direction) {
                "N" -> NESW[0] += count
                "E" -> NESW[1] += count
                "S" -> NESW[2] += count
                "W" -> NESW[3] += count

                "F" -> NESW[currentIndex] += count

                "R" -> when (count) {
                    90 -> currentIndex += 1
                    180 -> currentIndex += 2
                    270 -> currentIndex += 3
                }
                "L" -> when (count) {
                    90 -> currentIndex -= 1
                    180 -> currentIndex -= 2
                    270 -> currentIndex -= 3
                }
            }

            if(currentIndex < 0 ) currentIndex += NESW.size
            if(currentIndex >= NESW.size ) currentIndex -= NESW.size
        }

        val NS = abs(NESW[0] - NESW[2])
        val WE = abs(NESW[1] - NESW[3])

        return NS + WE
    }
}