package io.github.tomplum.aoc.input

import com.aoc.input.types.IntegerInput
import com.aoc.input.types.StringInput
import io.github.tomplum.aoc.input.types.Input
import java.io.File

class TestInputReader : InputReader() {
    fun readInputAsString(filePath: String): Input<String> = StringInput(readFile("/input/$filePath").readLines())
    fun readInputAsInt(filePath: String): Input<Int> = IntegerInput(readFile("/input/$filePath").readLines())
    private fun readFile(filePath: String) = File(javaClass.getResource(filePath).path)
}