package com.`fun`.adventofcode2020

class Assignment11_2 {

    fun seat2() {
        val inputs = ReadFileUtils.readFile("src/input11.txt").split("\n")

        val output = mutableListOf<String>()
        output.addAll(inputs)
        var current = mutableListOf<String>()

        while (current != output) {

            current = mutableListOf()
            current.addAll(output)

            for (i in inputs.indices) {
                for (j in inputs[i].indices) {

                    if (inputs[i][j] == '.') continue

                    val (countEmpty, countTaken) = findSeating(i, j, current)

                    if (countEmpty == 8) {
                        output[i] = output[i].replaceRange(j, j + 1, "#")
                    }
                    if (countTaken >= 5) {
                        output[i] = output[i].replaceRange(j, j + 1, "L")
                    }
                }
            }
//            print(output)
        }

        val count = output.sumBy { it.count { char -> char == '#' } }

        print("count = $count")
    }

    private fun findSeating(
        currentX: Int,
        currentY: Int,
        current: MutableList<String>
    ): Pair<Int, Int> {
        var countEmpty = 0
        var countTaken = 0

        for (x in  - 1.. + 1) {
            for (y in  - 1.. + 1) {
                if (x == 0 && y == 0) continue
                val char =         findXY(currentX, currentY, currentX, current, x, y)

                when(char) {
                    '#' ->countTaken++
                    'L' ->countEmpty++
                    null -> countEmpty++
                }
            }
        }
        return Pair(countEmpty, countTaken)
    }

    private fun findXY(x: Int, y: Int, currentX: Int, current: MutableList<String>, directionX: Int, directionY: Int): Char? {
        var variableX = x + directionX
        var variableY = y + directionY
        while (variableX >= 0 && variableY >= 0 && variableX < current.size && variableY < current[currentX].length) {
            if(current[variableX][variableY] == '.') {
                variableX+= directionX
                variableY+= directionY
                continue
            }
            return current[variableX][variableY]
        }
        return null
    }
}