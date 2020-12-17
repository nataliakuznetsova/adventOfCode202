package com.`fun`.adventofcode2020

import java.util.*

class Assignment13_2 {

    val input = "23,x,x,x,x,x,x,x,x,x,x,x,x,41,x,x,x,37,x,x,x,x,x,479,x,x,x,x,x,x,x,x,x,x,x,x,13,x,x,x,17,x,x,x,x,x,x,x,x,x,x,x,29,x,373,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,19"

    fun getSequence2() {
        val busLines = mutableListOf<Pair<Int, Long>>()
        input.split(",").forEachIndexed { index, it -> if (it != "x") busLines.add(Pair(index,it.toLong())) }
        val productOfAllLineNumbers = busLines.fold(1L, { acc, int -> acc*int.second })

        var timeStamp = busLines.first().second
        for (i in 1 until busLines.size) {
            //  The fold function on the next line of code essentially takes the product of the numbers we already found the timestamp for,
            //  so to find the timestamp where t % 17 == 0, (t+7) % 41 == 0, and (t+17) % 523 == 0, we can iterate from the timestamp of t % 17 == 0, (t+7) % 41 == 0 with steps of (7*41)
            timeStamp = (timeStamp..productOfAllLineNumbers step (busLines.take(i).fold(1L, { acc, int -> acc*int.second })))
                .first { (it + busLines[i].first) % busLines[i].second == 0L }
        }
        println(timeStamp)
    }
}