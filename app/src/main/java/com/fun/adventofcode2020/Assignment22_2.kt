package com.`fun`.adventofcode2020

class Assignment22_2 {

    fun play() {
        val inputs = ReadFileUtils.readFile("src/input22.txt").split("\n\n")

        val p1 = inputs[0].split("\n").map { it.toInt() }.toMutableList()
        val p2 = inputs[1].split("\n").map { it.toInt() }.toMutableList()

        playGame(p1, p2)

        var sum = 0
        for (i in p1.indices) {
            sum += (p1.size - i) * p1[i]
        }
        for (i in p2.indices) {
            sum += (p2.size - i) * p2[i]
        }
        print("score = $sum")
    }

    private fun playGame(
        p1: MutableList<Int>,
        p2: MutableList<Int>
    ) {
        val rounds = mutableListOf<Round>()
        while (p1.isNotEmpty() && p2.isNotEmpty()) {
            playRound(rounds, p1, p2)
        }
    }

    private fun playRound(
        rounds: MutableList<Round>,
        p1: MutableList<Int>, p2: MutableList<Int>
    ) {

        val element = Round(p1.toList(), p2.toList())
        if (rounds.contains(element)) {
            p2.clear()
            return
        }

        rounds.add(element)
        val card1 = p1.removeAt(0)
        val card2 = p2.removeAt(0)

        if (card1 <= p1.size && card2 <= p2.size) {
            val p1Short = p1.slice(0 until card1).toMutableList()
            val p2Short = p2.slice(0 until card2).toMutableList()
            playGame(p1Short, p2Short)

            if (p1Short.isNotEmpty()) {
                p1.add(card1)
                p1.add(card2)
            } else {
                p2.add(card2)
                p2.add(card1)
            }

        } else {
            if (card1 > card2) {
                p1.add(card1)
                p1.add(card2)
            } else {
                p2.add(card2)
                p2.add(card1)
            }
        }
    }

    data class Round(val l1: List<Int>, val l2: List<Int>)
}