package com.`fun`.adventofcode2020

import java.util.*
import kotlin.math.*

class Assignment12_2 {

    /*
     N
    W E
     S

    // ship 0 0
     F10 -> east 100, north 10
     N3 -> east 100, north 10
     F7 -> east 170, north 38.
     R90 -> east 170, north 38.
     F11 -> east 214, south 72.


    // waypoint east 10, north 1
     F10 -> east 10, north 1
     N3 -> east 10, north 4
     F7 -> east 10, north 4
     R90 -> east 4, south 10
     F11 -> east 4, south 10
     */

    fun navigate(fileName: String): Int {
        val inputs = ReadFileUtils.readFile(fileName).split("\n")

        val ship_NESW = mutableListOf(0, 0, 0, 0)
        val waypoint_NESW = mutableListOf(1, 10, 0, 0)

        for (command in inputs) {
            val direction = command.substring(0, 1)
            val count = command.substring(1).toInt()

            when (direction) {
                "N" -> waypoint_NESW[0] += count
                "E" -> waypoint_NESW[1] += count
                "S" -> waypoint_NESW[2] += count
                "W" -> waypoint_NESW[3] += count

                "F" -> for (i in waypoint_NESW.indices) {
                    ship_NESW[i] += (waypoint_NESW[i] * count)
                }

                "R" -> when (count) {
                    90 -> Collections.rotate(waypoint_NESW, 1)
                    180 -> Collections.rotate(waypoint_NESW, 2)
                    270 -> Collections.rotate(waypoint_NESW, 3)
                }
                "L" -> when (count) {
                    90 -> Collections.rotate(waypoint_NESW, -1)
                    180 -> Collections.rotate(waypoint_NESW, -2)
                    270 -> Collections.rotate(waypoint_NESW, -3)
                }
            }
        }

        val NS = abs(ship_NESW[0] - ship_NESW[2])
        val WE = abs(ship_NESW[1] - ship_NESW[3])

        return NS + WE
    }
}