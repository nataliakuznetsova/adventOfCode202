package com.`fun`.adventofcode2020

class Assignment4 {

    private val requiredFields = listOf(
        "byr",
        "iyr",
        "eyr",
        "hgt",
        "hcl",
        "ecl",
        "pid"
    )

    fun countJustFields() {
        val passports = ReadFileUtils.readFile("src/input4.txt").split("\n\n")
        var count = 0

        for (passport in passports) {
            for (i in requiredFields.indices) {
                if (!passport.contains(requiredFields[i])) {
                    break
                }
                if (i == requiredFields.size - 1) {
                    count++
                }
            }
        }
        print("valid just fields = $count")
    }

    fun countValid() {
        val passports = ReadFileUtils.readFile("src/input4.txt").split("\n\n")
        var count = 0

        for (current in passports.indices) {
            val passportNew = passports[current].replace("\n", " ")
            for (i in requiredFields.indices) {
                val field = requiredFields[i]
                if (!passportNew.contains(field)) {
                    break
                }

                val valid = when (field) {
                    "byr" -> checkDateOfBirth(passportNew)
                    "iyr" -> checkIssueDate(passportNew)
                    "eyr" -> checkExpDate(passportNew)
                    "hgt" -> checkHeight(passportNew)
                    "hcl" -> checkHair(passportNew)
                    "ecl" -> checkEye(passportNew)
                    "pid" -> checkPassword(passportNew)
                    else -> false
                }
                if(!valid){
                    break
                }
                if (i == requiredFields.size - 1) {
                    count++

                    println("$current")
                }
            }
        }

        print("valid = $count")
    }

    private fun getFieldValue(string: String, field: String): String? {
        val passport = string.split(" ")
        val value = passport.find { it.contains(field) }

        return value?.replace("$field:", "")
    }

    private fun checkDateOfBirth(passportS: String): Boolean {
        val field = getFieldValue(passportS, "byr") ?: return false
        val date = Integer.parseInt(field)

        return date in 1920..2002
    }

    private fun checkIssueDate(passportS: String): Boolean {
        val field = getFieldValue(passportS, "iyr") ?: return false
        val date = Integer.parseInt(field)

        return date in 2010..2020
    }

    private fun checkExpDate(passportS: String): Boolean {
        val field = getFieldValue(passportS, "eyr") ?: return false
        val date = Integer.parseInt(field)

        return date in 2020..2030
    }

    private fun checkHeight(passportS: String): Boolean {
        var field = getFieldValue(passportS, "hgt") ?: return false

        if(field.contains("cm")){
            field = field.replace("cm", "")
            val height = Integer.parseInt(field)
            return height in 150..193
        }
        if(field.contains("in")){
            field = field.replace("in", "")
            val height = Integer.parseInt(field)
            return height in 59..76
        }
        return false
    }

    private fun checkHair(passportS: String): Boolean {
        val field = getFieldValue(passportS, "hcl") ?: return false

        return Regex("#[a-f,0-9]{6}").matches(field)
    }

    private fun checkEye(passportS: String): Boolean {
        val field = getFieldValue(passportS, "ecl") ?: return false

        return setOf<String>("amb","blu","brn","gry","grn","hzl","oth").contains(field)
    }

    private fun checkPassword(passportS: String): Boolean {
        val field = getFieldValue(passportS, "pid") ?: return false

        return Regex("[0-9]{9}").matches(field)
    }
}