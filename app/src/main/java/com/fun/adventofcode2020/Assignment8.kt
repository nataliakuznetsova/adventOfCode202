package com.`fun`.adventofcode2020

class Assignment8 {

    fun count() {
        val inputs = ReadFileUtils.readFile("src/input8.txt")

        checkCommand(inputs, "nop", "jmp")
        checkCommand(inputs, "jmp", "nop")
    }

    private fun checkCommand(inputs: String, command: String, replace: String) {
        val allNops = Regex(command).findAll(inputs)
        for (nop in allNops) {
            val inputsNew = inputs.replaceRange(nop.range.first, nop.range.last + 1, replace)
            val check = isLoopingForever(inputsNew.split("\n"))
            if (!check.first) {
                print("acc = ${check.second}")
                break
            }
        }
    }

    private fun isLoopingForever(inputs: List<String>): Pair<Boolean, Int> {
        var acc = 0
        val indices = mutableSetOf<Int>()
        var index = 0

        while (!indices.contains(index)) {
            indices.add(index)
            if (index == inputs.size) {
                return Pair(false, acc)
            }
            val parts = inputs[index].split(" ")
            val count = Integer.parseInt(parts[1])
            when {
                parts[0].contains("nop") -> index += 1
                parts[0].contains("acc") -> {
                    acc += count; index += 1;
                }
                parts[0].contains("jmp") -> index += count
            }
        }

        return Pair(true, acc)
    }

    /*
    nop +0  | 1
    acc +1  | 2
    jmp +4  | 3
    acc +3  |
    jmp -3  |
    acc -99 |
    acc +1  | 4
    nop -4  | 5
    acc +6  | 6
     */
}