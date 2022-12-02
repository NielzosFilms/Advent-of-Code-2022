package puzzledays

class Day1 : PuzzleDay(1) {
    private val elfCalories = mutableListOf<Int>()
    override fun main() {
        val currElf = mutableListOf<Int>()
        puzzleInput.forEachLine {
            if (it.isEmpty()) {
                elfCalories.add(currElf.sum())
                currElf.clear()
            } else {
                currElf.add(it.toInt())
            }
        }
        log.info("Answer puzzle 1: " + elfCalories.max().toString())

        elfCalories.sortDescending()
        log.info("Answer puzzle 2: " + (elfCalories[0] + elfCalories[1] + elfCalories[2]))
    }
}
