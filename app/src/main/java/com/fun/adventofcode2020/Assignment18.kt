package com.`fun`.adventofcode2020

class Assignment18 {

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
        val parts = expr.split(Regex(" "))

        var total = parts[0].toLong()
        var i = 1
        while (i < parts.size) {
            if (parts[i] == "+") {
                i++
                total += parts[i].toLong()
            } else {
                i++
                total *= parts[i].toLong()
            }
            i++
        }

        return total
    }
}