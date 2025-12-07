package y2025.day06

import io.kotest.matchers.shouldBe
import kotlin.test.Test
import y2025.day06.Day06.Companion.readHomeWork
import y2025.day06.Day06.Companion.rightToLeft
import y2025.day06.Day06.Companion.sumOfLines

class Y2025Day06Test {

    val exampleInput =
"""
123 328  51 64 
 45 64  387 23 
  6 98  215 314
*   +   *   +  
"""

    @Test
    fun day3Test() {
        // example p1
        exampleInput.lines().filter { it.isNotBlank() }.readHomeWork().sumOfLines() shouldBe 4277556

        // example p2
        exampleInput.lines().filter { it.isNotBlank() }.rightToLeft() shouldBe 3263827
    }
}