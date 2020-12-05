package io.github.tomplum.aoc.airport.passport

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import io.github.tomplum.aoc.airport.passport.strategy.RelaxedValidation
import io.github.tomplum.aoc.airport.passport.strategy.StrictValidation
import org.junit.jupiter.api.Test

class PassportScannerTest {
    @Test
    fun exampleBatch() {
        val data = TestInputReader().readInputAsString("passport/example-batch.txt")
        val passports = PassportReader.read(data.value)
        assertThat(PassportScanner(RelaxedValidation()).scan(passports)).isEqualTo(2)
    }

    @Test
    fun exampleValidBatch() {
        val data = TestInputReader().readInputAsString("passport/valid-batch.txt")
        val passports = PassportReader.read(data.value)
        assertThat(PassportScanner(StrictValidation()).scan(passports)).isEqualTo(4)
    }

    @Test
    fun exampleInvalidBatch() {
        val data = TestInputReader().readInputAsString("passport/invalid-batch.txt")
        val passports = PassportReader.read(data.value)
        assertThat(PassportScanner(StrictValidation()).scan(passports)).isEqualTo(0)
    }
}