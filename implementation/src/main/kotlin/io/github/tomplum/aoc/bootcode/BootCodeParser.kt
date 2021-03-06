package io.github.tomplum.aoc.bootcode

/**
 * Boot Code is represented as a text file with one instruction per line of text.
 * This class parses the input and produces a runnable [Program].
 * @see Runtime
 */
class BootCodeParser private constructor() {
    companion object {
        fun parse(data: List<String>): Program = data.map {
            val info = it.split(" ")
            val code = Operation.fromString(info[0])
            val argument = info[1].toInt()
            Instruction(code, argument)
        }.let { instructions -> Program(instructions) }
    }
}