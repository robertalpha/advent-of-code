package y2025.day06

import io.kotest.matchers.shouldBe
import kotlin.test.Test
import y2025.day06.Day06.Companion.readHomeWork
import y2025.day06.Day06.Companion.sumOfLines
import y2025.day06.Day06.Companion.sumOfVerticalColumns

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
        val exampleInventory = exampleInput.lines().readHomeWork()

        exampleInventory.sumOfLines() shouldBe 4277556

        exampleInventory.sumOfVerticalColumns() shouldBe 3263827

    }
}