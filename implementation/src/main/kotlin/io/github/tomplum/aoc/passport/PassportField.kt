package io.github.tomplum.aoc.passport

enum class PassportField(val code: String) {
    BIRTH_YEAR("byr") {
        override fun isValid(value: String) = value.length == 4 && value.toInt() in IntRange(1920, 2002)
    },
    ISSUE_YEAR("iyr") {
        override fun isValid(value: String) = value.length == 4 && value.toInt() in IntRange(2010, 2020)
    },
    EXPIRATION_YEAR("eyr") {
        override fun isValid(value: String) = value.length == 4 && value.toInt() in IntRange(2020, 2030)
    },
    HEIGHT("hgt") {
        override fun isValid(value: String) = when(value.takeLast(2)) {
            "cm" -> value.dropLast(2).toInt() in IntRange(150, 193)
            "in" -> value.dropLast(2).toInt() in IntRange(59, 76)
            else -> false
        }
    },
    HAIR_COLOUR("hcl") {
        override fun isValid(value: String): Boolean {
            val isHex = value.filter { it.isLetter() }.all { it.toInt() in IntRange(97, 102) }
            return value.length == 7 && value.first() == '#' && isHex
        }
    },
    EYE_COLOUR("ecl") {
        override fun isValid(value: String) = listOf("amb", "blu", "brn,", "gry", "grn", "hzl", "oth").contains(value)
    },
    PASSPORT_ID("pid") {
        override fun isValid(value: String) = value.length == 9 && value.all { it.isDigit() }
    },
    COUNTRY_ID("cid") {
        override fun isValid(value: String) = true
    };

    abstract fun isValid(value: String): Boolean

    companion object {
        fun fromString(code: String): PassportField = when(code) {
            "byr" -> BIRTH_YEAR
            "iyr" -> ISSUE_YEAR
            "eyr" -> EXPIRATION_YEAR
            "hgt" -> HEIGHT
            "hcl" -> HAIR_COLOUR
            "ecl" -> EYE_COLOUR
            "pid" -> PASSPORT_ID
            "cid" -> COUNTRY_ID
            else -> throw IllegalArgumentException("Invalid Code: $code")
        }
    }
}