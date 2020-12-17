package com.`fun`.adventofcode2020

class Assignment9 {

    fun count() {
        val inputs = ReadFileUtils.readFile("src/input9.txt").split("\n")

//        for (i in 25..inputs.size) {
//            val number = inputs[i]
//
//            val start = i - 25
//            val subList = inputs.subList(start, i)
//            if (!findSum(subList, number.toLong())) {
//                print("number = $number")
//                break
//            }
//        }

        findSet(inputs)
    }

    private fun findSum(subList: List<String>, sum: Long): Boolean {
        for (a in subList) {
            for (b in subList) {
                if (a.toLong() + b.toLong() == sum) {
                    return true
                }
            }
        }

        return false
    }

    //105950735
    private fun findSet(list: List<String>) {
        loop@ for (i in list.indices) {
            val max = 0
            val min = 0
            var sum = list[i].toLong()
            for (j in i+1..list.size) {
                sum += list[j].toLong()
                if (sum > 105950735L) {
                    break
                }
                if (sum == 105950735L) {
                    val subList = list.subList(i, j+1)
                    println("max = ${subList.max()}")
                    println("min = ${subList.min()}")
                    break@loop
                }
            }
        }

        print("found nothing")
    }
}