package com.`fun`.adventofcode2020

class Assignment16_2 {

    fun getMyTicket() {
        val inputs = ReadFileUtils.readFile("src/input16.txt").split("\n\n")

        val ranges = inputs[0].split("\n").map { Assignment16.Range.convert(it) }

        val i = inputs[2].split("\n")
        val tickets = i.subList(1, i.size)
        val otherTickets = tickets.map { it.split(",").map { str -> str.toInt() } }

        val validTickets = mutableListOf<List<Int>>()

        for (ticket in otherTickets) {
            var validCount = 0
            for (num in ticket) {
                for (range in ranges) {
                    if (range.isInRange(num)) {
                        validCount++
                        break
                    }
                }
            }

            if (validCount == ticket.size) validTickets.add(ticket)
        }

        val rangeNameIndex = mutableMapOf<String, Int>()

        while (rangeNameIndex.size != validTickets[0].size) {

            for (t in validTickets[0].indices) {

                val remaining = ranges.filter { !rangeNameIndex.keys.contains(it.name) }
                    .toMutableList()

                for (n in validTickets.indices) {
                    if(remaining.size == 0) break
                    val number = validTickets[n][t]

                    val copy = mutableListOf<Assignment16.Range>()
                    copy.addAll(remaining)
                    for (range in copy) {
                        if (!range.isInRange(number)) {
                            remaining.remove(range)
                            if(remaining.size == 1) break
                        }
                    }
                }

                if (remaining.size == 1) {
                    rangeNameIndex[remaining[0].name] = t
                }
            }
        }

        val my = listOf(71,127,181,179,113,109,79,151,97,107,53,193,73,83,191,101,89,149,103,197).map { it.toLong() }

        var m = 1L
        for(key in rangeNameIndex.keys){
            if(key.contains("departure")){
                val index = rangeNameIndex[key]!!
                val value = my[index]
                m *= value
            }
        }
        println("m = $m")
    }
}

