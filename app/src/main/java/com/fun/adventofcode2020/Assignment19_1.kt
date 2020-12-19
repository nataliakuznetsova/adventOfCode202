package com.`fun`.adventofcode2020

class Assignment19_1 {

    private val values = ReadFileUtils.readFile("src/input19.txt").split("\n\n")

    private val rules1 = values.first().split("\n")
        .map { it.split(":").let { (id, rule) -> id.toInt() to rule.trim() } }
        .toMap()

    private val lines = values[1].split("\n")

    fun part1() = lines.count { isMatch(rules1, it, listOf(0)) }

    private fun isMatch(ruleMap: Map<Int, String>, line: CharSequence, rules: List<Int>): Boolean {
        if (line.isEmpty()) {
            return rules.isEmpty()
        } else if (rules.isEmpty()) {
            return false
        }
        return ruleMap.getValue(rules[0]).let { rule ->
            if (rule[0] in 'a'..'b') {
                if (line.startsWith(rule[0])) {
                    isMatch(ruleMap, line.drop(1), rules.drop(1))
                } else false
            } else rule.split(" | ").any {
                isMatch(ruleMap, line, it.split(" ").map(String::toInt) + rules.drop(1))
            }
        }
    }
}