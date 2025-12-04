package y2025.day03

import io.kotest.matchers.shouldBe
import kotlin.test.Test
import y2025.day03.Day03.Companion.getJoltage
import y2025.day03.Day03.Companion.getPivots
import y2025.day03.Day03.Companion.max

class Y2025Day03Test {
val exampleInput =
    """ 987654321111111
        811111111111119
        234234234234278
        818181911112111  """

    @Test
    fun day3Test() {

        "987654321111111".getPivots() shouldBe 23

        "12345987547321".getJoltage(4) shouldBe 9877
        "7226674585681665678872986726536752224582889349253576886637426367472455966247623181266826796786544536".getJoltage(12) shouldBe 999998654536

        exampleInput.lines().map { it.trim() }[0].getPivots().maxOf { it.max() } shouldBe 98
        exampleInput.lines().map { it.trim() }[1].getPivots().maxOf { it.max() } shouldBe 89
        exampleInput.lines().map { it.trim() }[2].getPivots().maxOf { it.max() } shouldBe 78
        exampleInput.lines().map { it.trim() }[3].getPivots().maxOf { it.max() } shouldBe 92
    }
}