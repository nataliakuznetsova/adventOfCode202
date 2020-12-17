package com.`fun`.adventofcode2020

import java.io.File

class ReadFileUtils {

    companion object {

        fun readFile(fileName: String): String
                = File(fileName).readText(Charsets.UTF_8)
    }
}