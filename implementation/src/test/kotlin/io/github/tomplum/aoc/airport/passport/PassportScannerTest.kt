package io.github.tomplum.aoc.airport.passport

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.airport.passport.strategy.RelaxedValidation
import io.github.tomplum.aoc.airport.passport.strategy.StrictValidation
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Test

class PassportScannerTest {
    @Test
    fun exampleBatch() {
        val data = TestInputReader.read<String>("passport/example-batch.txt").asSingleString()
        val passports = PassportReader.read(data)
        assertThat(PassportScanner(RelaxedValidation()).scan(passports)).isEqualTo(2)
    }

    @Test
    fun exampleValidBatch() {
        val data = TestInputReader.read<String>("passport/valid-batch.txt").asSingleString()
        val passports = PassportReader.read(data)
        assertThat(PassportScanner(StrictValidation()).scan(passports)).isEqualTo(4)
    }

    @Test
    fun exampleInvalidBatch() {
        val data = TestInputReader.read<String>("passport/invalid-batch.txt").asSingleString()
        val passports = PassportReader.read(data)
        assertThat(PassportScanner(StrictValidation()).scan(passports)).isEqualTo(0)
    }
}