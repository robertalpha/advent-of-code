package y2025.day09

import io.kotest.matchers.shouldBe
import kotlin.test.Test
import y2025.day09.Day09.Companion.Area
import y2025.day09.Day09.Companion.PointTwoD
import y2025.day09.Day09.Companion.area
import y2025.day09.Day09.Companion.contains
import y2025.day09.Day09.Companion.getMaxArea

class Y2025Day09Test {

    val exampleInput =
"""
7,1
11,1
11,7
9,7
9,5
2,5
2,3
7,3
"""

    @Test
    fun areaTest() {
        PointTwoD(2, 5).area(PointTwoD(11, 1)) shouldBe 50
        PointTwoD(7, 1).area(PointTwoD(11, 7)) shouldBe 35
        PointTwoD(7,3).area(PointTwoD(2,3)) shouldBe 6
        PointTwoD(2,5).area(PointTwoD(9,7)) shouldBe 24
    }
    @Test
    fun areaContainsTest() {
        val someArea = Area(PointTwoD(1, 1), PointTwoD(3, 3))

        someArea.contains(PointTwoD(1,1)) shouldBe false
        someArea.contains(PointTwoD(1,2)) shouldBe false
        someArea.contains(PointTwoD(2,1)) shouldBe false

        someArea.contains(PointTwoD(2,2)) shouldBe true

        someArea.contains(PointTwoD(3,2)) shouldBe false
        someArea.contains(PointTwoD(2,3)) shouldBe false
        someArea.contains(PointTwoD(3,3)) shouldBe false
    }

    @Test
    fun areaMappedTest() {
        val points = exampleInput.lines().filter { it.isNotBlank() }
            .map { line -> line.split(',').let {  PointTwoD(
                it[0].toLong(),
                it[1].toLong()
            ) }}

        val virtualpoints = points.makeVirtualPoints(1L)
        val dontOverlap = points + virtualpoints

        val areas = points.map { p1 -> points.map { p2 -> Area(p1, p2) } }.flatten()
        val filteredAreas = areas.filter { area -> dontOverlap.none { px -> area.contains(px) } }

        val maxNonOverlap = filteredAreas.maxBy { it.a.area(it.b) }

        val result = maxNonOverlap.a.area(maxNonOverlap.b)
    }

    @Test
    fun day9Test() {
        val input = exampleInput.lines().filter { it.isNotBlank() }
            .map { line -> line.split(',').let {  PointTwoD(
                it[0].toLong(),
                it[1].toLong()
                ) }}

        // example p1
        val areas = input.map { p1 -> input.map { it.area(p1) } }
        val maxxed = areas.map { it.max() }
        maxxed.maxBy { it } shouldBe 50

        input.getMaxArea() shouldBe 50
    }

}

