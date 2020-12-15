package io.github.tomplum.aoc.ferry.docking.strategy

import io.github.tomplum.aoc.ferry.docking.program.InitProgram
import io.github.tomplum.aoc.ferry.docking.emulator.Memory

abstract class DecodingStrategy {
    protected val memory = Memory()
    abstract fun decode(program: InitProgram): Long
}