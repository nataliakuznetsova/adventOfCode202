package com.`fun`.adventofcode2020

class Assignment21 {

    fun find() {
        val inputs = ReadFileUtils.readFile("src/input21.txt").split("\n")

        val allI = mutableSetOf<String>()
        val allA = mutableListOf<String>()
        val items = mutableListOf<Item>()
        val mapped = mutableMapOf<String, String>()
        val map = mutableMapOf<String, MutableList<String>>()

        for (input in inputs) {
            val parts = input.split(" (contains ")

            val i = parts[0].split(" ").toMutableList()
            val a = parts[1].split(", ").toMutableList()
            items.add(Item(i, a))

            allI.addAll(i)
            allA.addAll(a)
        }

        for (a in allA) {
            map[a] = allI.toMutableList()
        }

        while (mapped.size != map.size) {

            loop@ for (item1 in items) {

                if (item1.alg.size == 1 && item1.ing.size == 1) {
                    val i = item1.ing[0]
                    val a = item1.alg[0]
                    removeIng(i, mapped, a, items, map)
                }

                for (item2 in items) {
                    if (item1 == item2) continue

                    val common_i = mutableListOf<String>()
                    common_i.addAll(item1.ing)
                    common_i.retainAll(item2.ing)

                    val common_a = mutableListOf<String>()
                    common_a.addAll(item1.alg)
                    common_a.retainAll(item2.alg)

                    if (common_a.size == 1 && common_i.size != 0) {
                        val a = common_a[0]
                        map.getValue(a).retainAll(common_i)
                    }
                }
            }

            for (a in map.keys) {
                val value = map.getValue(a)
                if (value.size == 1) {
                    val i = value[0]
                    removeIng(i, mapped, a, items, map)
                }
            }
        }

        val count = items.map { it.ing.size }.reduce { acc, i -> acc + i }
        print("count = $count")
        println("")

        val sorted = mapped.keys.toList().sorted()
        for (a in sorted) {
            print("${mapped[a]},")
        }
    }

    private fun removeIng(
        i: String,
        mapped: MutableMap<String, String>,
        a: String,
        items: MutableList<Item>,
        map: MutableMap<String, MutableList<String>>
    ) {
        mapped[a] = i

        for (item in items) {
            item.ing.remove(i)
            item.alg.remove(a)
        }

        for (v in map.values) {
            v.remove(i)
        }
    }

    data class Item(val ing: MutableList<String>, val alg: MutableList<String>)
}