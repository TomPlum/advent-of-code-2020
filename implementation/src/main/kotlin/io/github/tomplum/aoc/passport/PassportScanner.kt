package io.github.tomplum.aoc.passport

class PassportScanner {
    fun scan(passports: List<Passport>): Int = passports.filter { it.isValid() }.count()
}