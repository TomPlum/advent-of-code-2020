package io.github.tomplum.aoc.passport

enum class PassportField(val code: String) {
    BIRTH_YEAR("byr"),
    ISSUE_YEAR("iyr"),
    EXPIRATION_YEAR("eyr"),
    HEIGHT("hgt"),
    HAIR_COLOUR("hcl"),
    EYE_COLOUR("ecl"),
    PASSPORT_ID("pid"),
    COUNTRY_ID("cid");

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