package io.github.tomplum.aoc.input

import io.github.tomplum.libs.input.InputReader
import io.github.tomplum.libs.input.types.Input
import io.github.tomplum.libs.input.types.IntegerInput
import io.github.tomplum.libs.input.types.StringInput
import java.io.File

class TestInputReader : InputReader() {
    fun readInputAsString(filePath: String): Input<String> = StringInput(readFile("/input/$filePath").readLines())
    fun readInputAsInt(filePath: String): Input<Int> = IntegerInput(readFile("/input/$filePath").readLines())
    private fun readFile(filePath: String) = File(javaClass.getResource(filePath).path)
}