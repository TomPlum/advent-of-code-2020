package io.github.tomplum.aoc.ferry.docking.strategy

import io.github.tomplum.aoc.ferry.docking.InitProgram
import io.github.tomplum.aoc.ferry.docking.Memory

abstract class DecodingStrategy {
    protected val memory = Memory()
    abstract fun decode(program: InitProgram): Long
}