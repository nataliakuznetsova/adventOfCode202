package com.`fun`.adventofcode2020

class Assignment17 {

    fun cube() {
        val currentTimeMillis = System.currentTimeMillis()
        val inputs = ReadFileUtils.readFile("src/input17.txt").split("\n")

        var output = mutableMapOf<Coord, Char>()

        for (i in inputs.indices) {
            for (j in inputs[i].indices) {
                val coord = Coord(i, j, 0, 0)
                output[coord] = inputs[i][j]
            }
        }

        repeat(6) {

            val current = mutableMapOf<Coord, Char>()
            val all = expand(output)

            for (cube in all.keys) {

                val x = cube.x
                val y = cube.y
                val z = cube.z
                val w = cube.w
                val me = Coord(x, y, z, w)
                val meValue = output[me] ?: '.'

                val count = getActiveCountForCube(x, y, z, w, all)

                if (meValue == '#' && count in 2..3) {
                    current[me] = '#'
                } else if (meValue == '.' && count == 3) {
                    current[me] = '#'
                } else current[me] = '.'
            }

            output = current

        }
        val count = output.values.sumBy { char -> if (char == '#') 1 else 0 }
        println("count = $count")

        println("total = ${System.currentTimeMillis() - currentTimeMillis} ms")
    }

    private fun expand(output: MutableMap<Coord, Char>): Map<Coord, Char> {
        val all = mutableMapOf<Coord, Char>()

        for (cube in output.keys) {

            val x = cube.x
            val y = cube.y
            val z = cube.z
            val w = cube.w

            for (i in -1..1) {
                for (j in -1..1) {
                    for (k in -1..1) {
                        for (l in -1..1) {

                            val newX = x + i
                            val newY = y + j
                            val newZ = z + k
                            val newW = w + l

                            val e = Coord(newX, newY, newZ, newW)
                            val v = output[e] ?: '.'

                            all[e] = v
                        }
                    }
                }
            }
        }

        return all
    }

    private fun getActiveCountForCube(
        x: Int,
        y: Int,
        z: Int,
        w: Int,
        output: Map<Coord, Char>
    ): Int {
        var count = 0
        for (i in -1..1) {
            for (j in -1..1) {
                for (k in -1..1) {
                    for (l in -1..1) {
                        val newX = x + i
                        val newY = y + j
                        val newZ = z + k
                        val newW = w + l

                        if (newX == x && newY == y && newZ == z && newW == w) continue

                        val n = Coord(newX, newY, newZ, newW)
                        val nv = output[n] ?: '.'

                        if (nv == '#') count++
                    }
                }
            }
        }
        return count
    }

    data class Coord(val x: Int, val y: Int, val z: Int, val w: Int)
}