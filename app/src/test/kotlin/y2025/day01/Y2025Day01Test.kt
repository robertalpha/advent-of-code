package y2025.day01

import io.kotest.matchers.shouldBe
import kotlin.test.Test

class Y2025Day01Test {

    val exampleInput =
        """
            L68
            L30
            R48
            L5
            R60
            L55
            L1
            L99
            R14
            L82
            """.lines()


    @Test
    fun day1Test() {
        Day01.solution(exampleInput).zeroCounts shouldBe 3
        Day01.solution(exampleInput).zeroSkipped shouldBe 3
    }

    @Test
    fun sumup() {

        Day01.sumup(Day01.Aggregate(50,0,0), Day01.Move(Day01.Direction.L, 68)).let { it.zeroCounts + it.zeroSkipped } shouldBe 1


        Day01.sumup(Day01.Aggregate(0,1,0), Day01.Move(Day01.Direction.R, 201)).let { it.zeroCounts + it.zeroSkipped } shouldBe 3
        Day01.sumup(Day01.Aggregate(0,1,0), Day01.Move(Day01.Direction.R, 200)).let { it.zeroCounts + it.zeroSkipped } shouldBe 4
        Day01.sumup(Day01.Aggregate(0,1,0), Day01.Move(Day01.Direction.L, 201)).let { it.zeroCounts + it.zeroSkipped } shouldBe 3
        Day01.sumup(Day01.Aggregate(0,1,0), Day01.Move(Day01.Direction.L, 200)).let { it.zeroCounts + it.zeroSkipped } shouldBe 4

        // mod checks
        Day01.sumup(Day01.Aggregate(82,0,0), Day01.Move(Day01.Direction.L, 30)).dialPosition shouldBe 52
        Day01.sumup(Day01.Aggregate(0,0,0), Day01.Move(Day01.Direction.L, 1)).dialPosition shouldBe 99
        Day01.sumup(Day01.Aggregate(0,0,0), Day01.Move(Day01.Direction.L, 101)).dialPosition shouldBe 99
        Day01.sumup(Day01.Aggregate(0,0,0), Day01.Move(Day01.Direction.R, 10)).dialPosition shouldBe 10
        Day01.sumup(Day01.Aggregate(0,0,0), Day01.Move(Day01.Direction.R, 110)).dialPosition shouldBe 10

        // zerocounts checks
        Day01.sumup(Day01.Aggregate(0,0,0), Day01.Move(Day01.Direction.R, 100)).zeroCounts shouldBe 1
        Day01.sumup(Day01.Aggregate(0,1,0), Day01.Move(Day01.Direction.R, 100)).zeroCounts shouldBe 2
        Day01.sumup(Day01.Aggregate(0,2,0), Day01.Move(Day01.Direction.L, 100)).zeroCounts shouldBe 3


    }

}