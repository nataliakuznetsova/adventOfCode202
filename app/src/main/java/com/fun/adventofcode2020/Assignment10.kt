package com.`fun`.adventofcode2020

class Assignment10 {

    fun find() {
        val inputs = ReadFileUtils.readFile("src/input10.txt").split("\n")

        var ones = 0
        var twos = 0
        var threes = 0
        val adapters = inputs.map { it.toLong() }.sorted().toMutableList()

        var start = 0L
        for (adapter in adapters) {
            if (adapter - start == 1L) ones++
            if (adapter - start == 2L) twos++
            if (adapter - start == 3L) threes++

            start = adapter
        }

        print("ones = $ones threes = ${threes + 1} * = ${ones * (threes + 1)}")
    }

    fun find2() {
        val inputs = ReadFileUtils.readFile("src/input10.txt").split("\n")

        val result = mutableListOf<Int>()
        result.add(0)

        val adapters = inputs.map { it.toInt() }.sorted().toMutableList()
        adapters.add(0, 0)
        for (i in 0 until adapters.size - 1) {

            val current = adapters[i]
            println(" working on $i -> $current")
            val nextPossibleAdapter = mutableListOf<Int>()

            for (j in i + 1 until adapters.size) {
                val diff = adapters[j] - current
                if (diff <= 3) {
                    nextPossibleAdapter.add(adapters[j])
                } else break
            }

            if (nextPossibleAdapter.size == 1) {
                result.replaceAll { nextPossibleAdapter[0] }
            } else {
                val count = result.count { it == current }
                result.removeAll { it == current }
                for (next in nextPossibleAdapter) {
                    repeat(count) { result.add(next) }
                    print(" inserted")
                }
            }
        }

        println("total = ${result.size}")
    }

    fun find3() {
        val inputs = ReadFileUtils.readFile("src/input10.txt").split("\n")

        val result = mutableMapOf<Long, Long>()

        val adapters = inputs.map { it.toLong() }.sorted().toMutableList()
        for (i in 0 until adapters.size - 1) {

            val current = adapters[i]
            println(" working on $i -> $current")
            val nextPossibleAdapter = mutableMapOf<Long, Long>()

            for (j in i + 1 until adapters.size) {
                val diff = adapters[j] - current
                if (diff <= 3) {
                    val get = nextPossibleAdapter[diff]
                    if (get == null) {
                        nextPossibleAdapter[diff] = 1L
                    } else {
                        nextPossibleAdapter[diff] = get + 1
                    }
                } else break
            }

            val count = result[current] ?: 1L
            result.remove(current)

            for (next in nextPossibleAdapter.keys) {
                val num = current + next
                val node = result[num]
                if (node == null) {
                    result[num] = count
                } else {
                    result[num] = node + count
                }
            }
        }

        println("total = ${result[146]}")
    }
}