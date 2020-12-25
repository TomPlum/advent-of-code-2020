package io.github.tomplum.aoc.island.room

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class DecrypterTest {
    @Test
    fun example() {
        assertThat(Decrypter().getEncryptionKey(5764801, 17807724)).isEqualTo(14897079)
    }
}