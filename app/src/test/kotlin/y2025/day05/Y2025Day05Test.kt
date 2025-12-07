package y2025.day05

import io.kotest.matchers.shouldBe
import kotlin.test.Test
import y2025.day05.Day05.Companion.countFreshItemNumbers
import y2025.day05.Day05.Companion.countFreshItems
import y2025.day05.Day05.Companion.mergeRanges
import y2025.day05.Day05.Companion.readInventory

class Y2025Day05Test {

    val exampleInput =
"""
3-5
10-14
16-20
12-18

1
5
8
11
17
32
"""

    @Test
    fun day5Test() {
        val exampleInventory = exampleInput.lines().readInventory()

        exampleInventory.countFreshItems() shouldBe 3

        exampleInventory.mergeRanges().size shouldBe 2

        exampleInventory.countFreshItemNumbers() shouldBe 14

    }
}