package y2025.day04

import io.kotest.matchers.shouldBe
import kotlin.test.Test
import y2025.day04.Day04.Companion.countProcessable
import y2025.day04.Day04.Companion.countUntillNotProcessable
import y2025.day04.Day04.Companion.toMatrix

class Y2025Day04Test {

        val exampleInput =
"""
..@@.@@@@.
@@@.@.@.@@
@@@@@.@.@@
@.@@@@..@.
@@.@@@@.@@
.@@@@@@@.@
.@.@.@.@@@
@.@@@.@@@@
.@@@@@@@@.
@.@.@@@.@.
"""

    @Test
    fun day3Test() {
        val example = exampleInput.lines().map { it.trim() }.filter { it.isNotBlank() }

        // example part 1
        example.toMatrix().countProcessable() shouldBe 13

        // example part 2
        example.toMatrix().countUntillNotProcessable() shouldBe 43
    }


}