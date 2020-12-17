package com.`fun`.adventofcode2020

class Assignment11 {

    fun seat() {
        val inputs = ReadFileUtils.readFile("src/input11_s.txt").split("\n")

        val output = mutableListOf<String>()
        output.addAll(inputs)
        var current = mutableListOf<String>()

        while (current != output) {

            current = mutableListOf()
            current.addAll(output)

            for (i in inputs.indices) {
                for (j in inputs[i].indices) {

                    if (inputs[i][j] == '.') continue

                    val (countTotal, countEmpty, countTaken) = findSeating(i, j, current)

                    if (countEmpty == countTotal) {
                        output[i] = output[i].replaceRange(j, j + 1, "#")
                    }
                    if (countTaken >= 4) {
                        output[i] = output[i].replaceRange(j, j + 1, "L")
                    }
                }
            }
        }

        val count = output.sumBy { it.count { char -> char == '#' } }

        print("count = $count")
    }

    private fun findSeating(
        currentX: Int,
        currentY: Int,
        current: MutableList<String>
    ): Triple<Int, Int, Int> {
        var countTotal = 0
        var countEmpty = 0
        var countTaken = 0

        for (x in currentX - 1..currentX + 1) {
            for (y in currentY - 1..currentY + 1) {
                if (x == currentX && y == currentY) continue
                val triple = triple(x, y, currentX, current)
                countEmpty += triple.first
                countTaken += triple.second
                countTotal += triple.third
            }
        }
        return Triple(countTotal, countEmpty, countTaken)
    }

    private fun triple(x: Int, y: Int, i: Int, current: MutableList<String>
    ): Triple<Int, Int, Int> {
        var countEmpty = 0
        var countTaken = 0
        var countTotal = 0
        if (x >= 0 && y >= 0 && x < current.size && y < current[i].length) {
            val xa = current[x][y]
            if (xa == 'L' || xa == '.') countEmpty++
            if (xa == '#') countTaken++

            countTotal++
        }
        return Triple(countEmpty, countTaken, countTotal)
    }
}