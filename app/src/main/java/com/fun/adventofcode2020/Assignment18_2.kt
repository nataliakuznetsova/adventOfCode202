package com.`fun`.adventofcode2020

class Assignment18_2 {

    fun doMath() {
        val inputs = ReadFileUtils.readFile("src/input18.txt").split("\n")

        var total = 0L
        for (expr in inputs) {

            var new = expr
            while (new.contains("(")) {
                val i = new.indexOfLast { it == '(' } + 1
                val j = new.indexOf(')', i)

                val e = new.substring(i, j)
                val t = simple(e)

                new = new.replaceRange(i - 1, j + 1, t.toString())
            }
            val simple = simple(new)
            total += simple

            println("$expr = $simple")
        }

        print("total = $total")
    }

    private fun simple(expr: String): Long {
        val parts = expr.split(Regex(" ")).toMutableList()

        // 4 * 5 + 6
        while (parts.contains("+")) {
            val index = parts.indexOf("+")
            val sum = parts[index - 1].toLong() + parts[index + 1].toLong()

            parts.removeAt(index)
            parts.removeAt(index)
            parts.removeAt(index - 1)

            parts.add(index - 1, sum.toString())
        }

        while (parts.contains("*")) {
            val index = parts.indexOf("*")
            val m = parts[index - 1].toLong() * parts[index + 1].toLong()

            parts.removeAt(index)
            parts.removeAt(index)
            parts.removeAt(index - 1)

            parts.add(index - 1, m.toString())
        }

        return parts[0].toLong()
    }
}