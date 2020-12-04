package io.github.tomplum.aoc.passport

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Test

class PassportScannerTest {
    @Test
    fun exampleBatch() {
        val data = TestInputReader().readInputAsString("passport/example-batch.txt")
        val passports = PassportReader.read(data.value)
        assertThat(PassportScanner().scan(passports)).isEqualTo(2)
    }
}