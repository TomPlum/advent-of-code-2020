package io.github.tomplum.aoc.password

import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import io.github.tomplum.aoc.password.strategy.CorporatePolicy
import io.github.tomplum.aoc.password.strategy.SledRentalPolicy
import io.github.tomplum.aoc.password.strategy.TobogganPolicy
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PasswordDatabaseTest {
    private val exampleData = TestInputReader.read<String>("password/example-database-list.txt")

    @Nested
    inner class Import {
        @Test
        fun exampleDataSledPolicy() {
            val database = PasswordDatabase(SledRentalPolicy::class.java)
            database.import(exampleData.value)
            assertThat(database.passwords).isEqualTo(listOf(
                Pair("abcde", SledRentalPolicy("1-3 a")),
                Pair("cdefg", SledRentalPolicy("1-3 b")),
                Pair("ccccccccc", SledRentalPolicy("2-9 c"))
            ))
        }

        @Test
        fun emptyDataSledPolicy() {
            val database = PasswordDatabase(SledRentalPolicy::class.java)
            database.import(emptyList())
            assertThat(database.passwords).isEmpty()
        }

        @Test
        fun exampleDataTobogganPolicy() {
            val database = PasswordDatabase(TobogganPolicy::class.java)
            database.import(exampleData.value)
            assertThat(database.passwords).isEqualTo(listOf(
                Pair("abcde", TobogganPolicy("1-3 a")),
                Pair("cdefg", TobogganPolicy("1-3 b")),
                Pair("ccccccccc", TobogganPolicy("2-9 c"))
            ))
        }

        @Test
        fun emptyDataTobogganPolicy() {
            val database = PasswordDatabase(TobogganPolicy::class.java)
            database.import(emptyList())
            assertThat(database.passwords).isEmpty()
        }

        @Test
        fun unknownPolicyType() {
            val e = assertThrows<IllegalArgumentException> { PasswordDatabase(FakePolicy::class.java).import(exampleData.value) }
            assertThat(e.message).isEqualTo("Unknown Policy Type: FakePolicy")
        }

        private abstract inner class FakePolicy : CorporatePolicy
    }

    @Nested
    inner class Validate {
        @Test
        fun exampleDataSledPolicy() {
            val database = PasswordDatabase(SledRentalPolicy::class.java)
            database.import(exampleData.value)
            assertThat(database.validate()).isEqualTo(2)
        }

        @Test
        fun exampleDataTobogganPolicy() {
            val database = PasswordDatabase(TobogganPolicy::class.java)
            database.import(exampleData.value)
            assertThat(database.validate()).isEqualTo(1)
        }
    }
}
