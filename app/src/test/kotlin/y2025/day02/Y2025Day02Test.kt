package y2025.day02

import io.kotest.matchers.shouldBe
import kotlin.collections.map
import kotlin.test.Test
import y2025.day02.Day02.Companion.grouped
import y2025.day02.Day02.Companion.invalidsHalf
import y2025.day02.Day02.Companion.invalidsPattern

class Y2025Day02Test {
val exampleInput = "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,\n" +
        "1698522-1698528,446443-446449,38593856-38593862,565653-565659,\n" +
        "824824821-824824827,2121212118-2121212124"

    @Test
    fun day2Test() {
        grouped(listOf("1-1010")).map { it.invalidsHalf() }.flatten().sum() shouldBe 1505
        grouped(listOf("11-22")).map { it.invalidsHalf() }.flatten().sum() shouldBe 33
        grouped(listOf("222220-222224")).map { it.invalidsHalf() }.flatten().sum() shouldBe 222222
        grouped(listOf("2121212118-2121212124")).map { it.invalidsHalf() }.flatten().sum() shouldBe 0L
        grouped(listOf("4-14")).map { it.invalidsHalf() }.flatten().sum() shouldBe 11L

        grouped(listOf("565653-565659")).map { it.invalidsPattern() }.flatten() shouldBe listOf(565656)

        grouped(exampleInput.lines()).map { it.invalidsHalf() }.flatten().sum() shouldBe 1227775554
    }
}