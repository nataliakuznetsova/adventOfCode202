package com.`fun`.adventofcode2020

class Assignment20_2 {

    fun image() {
        val inputs = ReadFileUtils.readFile("src/input20.txt").split("\n\n")

        val images = mutableListOf<Image>()

        for (image in inputs) {
            val lines = image.split("\n").toMutableList()

            val key = lines.removeAt(0)
            val left = lines.map { it.first() }.joinToString().replace(", ", "")
            val right = lines.map { it.last() }.joinToString().replace(", ", "")

            val sides = listOf(lines.first(), left, right, lines.last())

            images.add(Image(key, sides))
        }

        for (image1 in images) {
            for ((index, side_image1) in image1.sides.withIndex()) {
                for (image2 in images) {

                    if (image1 == image2) continue
                    for ((index2, side_image2) in image2.sides.withIndex()) {

                        if (side_image1 == side_image2 || side_image1.reversed() == side_image2) {
                            image1.count++
                        }
                    }
                }
            }
            println("")
        }

        for (image in images) {
            if (image.count == 2) {
                println("corner is ${image.key}")
            }
        }

    }

    data class Image(
        var key: String = "",
        var sides: List<String>,
        var count: Int = 0,
        var n: Image? = null,
        var e: Image? = null,
        var s: Image? = null,
        var w: Image? = null
    )
}