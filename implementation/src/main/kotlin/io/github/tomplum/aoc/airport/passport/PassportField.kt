package io.github.tomplum.aoc.airport.passport

/**
 * Data fields belonging to a [Passport].
 * Each fields encapsulates the conditions of its validity and exposes [isValid] to check against a value.
 */
enum class PassportField {
    BIRTH_YEAR {
        override fun isValid(value: String): Boolean {
            return value.length == 4 && value.toInt() in 1920..2002
        }
    },
    ISSUE_YEAR {
        override fun isValid(value: String): Boolean {
            return value.length == 4 && value.toInt() in 2010..2020
        }
    },
    EXPIRATION_YEAR {
        override fun isValid(value: String): Boolean {
            return value.length == 4 && value.toInt() in 2020..2030
        }
    },
    HEIGHT {
        override fun isValid(value: String) = when(value.takeLast(2)) {
            "cm" -> value.dropLast(2).toInt() in 150..193
            "in" -> value.dropLast(2).toInt() in 59..76
            else -> false
        }
    },
    HAIR_COLOUR {
        override fun isValid(value: String): Boolean {
            val letters = value.filter { it.isLetter() }
            val other = value.drop(1).filterNot { it.isLetter() }
            val isHex = letters.all { it.toInt() in 97..102 } && other.all { it.isDigit() }
            return value.length == 7 && value.first() == '#' && isHex
        }
    },
    EYE_COLOUR {
        override fun isValid(value: String): Boolean {
            return value in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
        }
    },
    PASSPORT_ID {
        override fun isValid(value: String): Boolean {
            return value.length == 9 && value.all { it.isDigit() }
        }
    },
    COUNTRY_ID {
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
            else -> throw IllegalArgumentException("Invalid Field Code: $code")
        }
    }
}