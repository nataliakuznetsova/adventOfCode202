package com.`fun`.adventofcode2020

class Assignment16 {

    fun getErrorRate() {
        val inputs = ReadFileUtils.readFile("src/input16.txt").split("\n\n")

        val ranges = inputs[0].split("\n").map { Range.convert(it) }

        val i = inputs[2].split("\n")
        val tickets = i.subList(1, i.size)
        val otherTickets = tickets.map { it.split(",") }.flatten().map { it.toInt() }

        var error = 0

        otherTickets.forEach { ticket ->
            var inOneRange = false
            for (range in ranges) {
                if (range.isInRange(ticket)) {
                    inOneRange = true
                    break
                }
            }
            if (!inOneRange) error += ticket
        }

        print("errorRate = $error")
    }

    class Range(
        val name: String,
        private val lower_a: Int,
        private val upper_a: Int,
        private val lower_b: Int,
        private val upper_b: Int
    ) {

        companion object {

            fun convert(str: String): Range {
                //class: 1-3 or 5-7
                val all = str.split(" ")

                val a = all[1].split("-")
                val b = all[3].split("-")

                return Range(
                    all[0],
                    a[0].toInt(),
                    a[1].toInt(),
                    b[0].toInt(),
                    b[1].toInt()
                )
            }
        }

        fun isInRange(num: Int): Boolean {
            return num in lower_a..upper_a || num in lower_b..upper_b
        }
    }
}