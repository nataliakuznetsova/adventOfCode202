package com.`fun`.adventofcode2020

class Assignment6 {

    fun countAnyone() {
        val answers = ReadFileUtils.readFile("src/input6.txt").split("\n\n")
        var count = 0

        for (answer in answers) {
            var answerNew = answer.replace("\n", "")
            var char = 'a'
            while (answerNew.isNotEmpty()) {
                if (answerNew.contains(char)) {
                    answerNew = answerNew.replace(char.toString(), "")
                    count++
                }
                char++
            }
        }
        println("anyone yes = $count")
    }

    fun countEveryone() {
        val answers = ReadFileUtils.readFile("src/input6.txt").split("\n\n")
        var count = 0

        for (answer in answers) {
            val groupSize = answer.count { it == '\n' } + 1
            var answerNew = answer.replace("\n", "")
            var char = 'a'
            while (answerNew.isNotEmpty()) {
                val answerCount = answer.count { it == char }
                if (groupSize == answerCount) {
                    count++
                }
                answerNew = answerNew.replace(char.toString(), "")
                char++
            }
        }
        println("everyone yes = $count")
    }
}