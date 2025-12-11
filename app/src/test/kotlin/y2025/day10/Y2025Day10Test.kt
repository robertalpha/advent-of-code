package y2025.day10

import io.kotest.matchers.shouldBe
import kotlin.collections.filter
import kotlin.collections.first
import kotlin.collections.map
import kotlin.test.Test
import y2025.day10.Day10.Companion.findBestPresses
import y2025.day10.Day10.Companion.press
import y2025.day10.Day10.Companion.readMachines

class Y2025Day10Test {

    val exampleInput =
"""
[.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}
[...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}
[.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}
"""



    @Test
    fun readInputTest() {
        val machines = this.exampleInput.lines().filter { it.isNotBlank() }.readMachines()
        machines.size shouldBe 3
        machines.sumOf { it.buttons.size } shouldBe 15
    }


    @Test
    fun testSomePresses() {
        val machines = this.exampleInput.lines().filter { it.isNotBlank() }.readMachines()
        val m = machines.first()

        m.press(0, "....") shouldBe "...#"
        m.press(1, "....") shouldBe ".#.#"
        m.press(2, "....") shouldBe "..#."

        machines.map { Pair(it.solution, it.findBestPresses()) }
            .sumOf { it.second } shouldBe 7
    }

    @Test
    fun testBestPresses() {
        val machines = this.exampleInput.lines().filter { it.isNotBlank() }.readMachines()
        val m = machines.first()
    }


}



