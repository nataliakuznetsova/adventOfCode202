package com.`fun`.adventofcode2020

class Assignment22 {

    fun play() {
        val inputs = ReadFileUtils.readFile("src/input22.txt").split("\n\n")

        val p1 = inputs[0].split("\n").map { it.toInt() }.toMutableList()
        val p2 = inputs[1].split("\n").map { it.toInt() }.toMutableList()

        while (p1.isNotEmpty() && p2.isNotEmpty()) {
            val card1 = p1.removeAt(0)
            val card2 = p2.removeAt(0)

            if (card1 > card2) {
                p1.add(card1)
                p1.add(card2)
            } else {
                p2.add(card2)
                p2.add(card1)
            }

            print(p1)
        }

        if (p1.isNotEmpty()) {
            var sum = 0
            for (i in p1.indices) {
                sum+= (p1.size - i) * p1[i]
            }
            print("score = $sum")
        }

        if (p2.isNotEmpty()) {
            var sum = 0
            for (i in p2.indices) {
                sum+= (p2.size - i) * p2[i]
            }
            print("score = $sum")
        }

    }
}