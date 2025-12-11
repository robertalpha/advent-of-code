package y2025.day10


class Day10 {
    companion object {

        var input: List<String> = this::class.java.getResourceAsStream("./input.txt")?.bufferedReader()?.readLines()!!

        data class Button(val toggles: List<Int>)
        data class Machine(val solution: String, val buttons: List<Button>)
        data class PressState(val state: String, val buttonsPressed: Set<Int>)

        fun Machine.press(buttonIndex : Int, state: String) =
            buttons[buttonIndex].toggles.fold(state) {acc, nextToggle -> acc.flip(nextToggle) }
        fun Machine.press(button : Button, state: String) =
            button.toggles.fold(state) {acc, nextToggle -> acc.flip(nextToggle) }
        fun Char.flip() = if (this=='.') '#' else '.'
        fun String.flip(index: Int) = this.take(index) + this[index].flip() + this.drop(index+1)


        fun bestSolution(m: Machine, buttonsLeft: List<Button>, state: String, pressed: Int) : Int {
            val next = buttonsLeft.map { b->
                Pair(buttonsLeft.filter { it!=b }, m.press(b, state))
            }
            if(next.isEmpty()) {
                return pressed
            } else if(next.any { it.second == m.solution }) {
                return pressed + 1
            } else {
                return next.minOf { bestSolution(m, it.first, it.second, pressed + 1) }
            }
        }

        fun List<String>.readMachines(): List<Machine> {
            val buttonRegex = Regex("\\(([\\d,]+)\\)")
            return this.map {
                val solution = it.takeWhile { s -> s != ']' }.drop(1)
                val buttons = buttonRegex.findAll(it).map { matched ->
                    Button(matched.groups[1]!!.value.split(",").map{it.toInt()})
                }
                Machine(solution = solution, buttons = buttons.toList())
            }
        }

        fun Machine.findBestPresses() : Int {
            val best = bestSolution(this, this.buttons, this.solution.map { '.' }.joinToString(""), 0 )
            return best
        }

        fun part1() = input.readMachines().sumOf { m -> m.findBestPresses() }


        fun part2() = 123
    }
}

