package com.`fun`.adventofcode2020

class Assignment19 {

    fun decode() {
        val inputs = ReadFileUtils.readFile("src/input19.txt").split("\n\n")

        val rules = inputs[0].split("\n")
        val rulesList = mutableMapOf<Int, String>()

        var a = 0
        var b = 0

        for (rule in rules) {
            val r = rule.split(":")
            val index = r[0].toInt()
            val value = r[1].removePrefix(" ")
            rulesList[index] = value

            if (value == "a") {
                a = index
            }

            if (value == "b") {
                b = index
            }
        }

        while (rulesList.size != 1) {
            println("size = ${rulesList.size}")
            val aValue = rulesList.remove(a)
            val bValue = rulesList.remove(b)

            for (key in rulesList.keys) {

                aValue?.let { newValue ->
                    val oldValue = a.toString()
                    rulesList[key] = replace(rulesList.getValue(key), oldValue, newValue)
                }

                bValue?.let { newValue ->
                    val oldValue = b.toString()
                    rulesList[key] = replace(rulesList.getValue(key), oldValue, newValue)
                }
            }

            for (key in rulesList.keys) {
                if (!rulesList[key]!!.contains(Regex("[0-9]")) && key != a) {
                    a = key
                    break
                }
            }

            for (key in rulesList.keys) {
                if (!rulesList[key]!!.contains(Regex("[0-9]")) && key != a && key != b) {
                    b = key
                    break
                }
            }
        }

        val rule = rulesList[0]!!.replace(" ", "")
        println("rule = $rule")

        val messages = inputs[1].split("\n")

        var count = 0
        for (message in messages) {
            if (Regex(rule).matches(message)) {
                count++
            }
        }

        println("count = $count")
    }

    private fun replace(str: String, oldValue: String, newValue: String): String {
        if (!str.contains(oldValue)) return str
        val split = str.split(" ")
        if (!split.contains(oldValue)) return str

        val parts = newValue.split("|")

        var result = str.replace(oldValue, parts[0])
        for (i in 1 until parts.size) {
            result += "|" + str.replace(oldValue, parts[i])
        }

        return result
    }
}