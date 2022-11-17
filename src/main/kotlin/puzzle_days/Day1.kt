package puzzle_days

class Day1 : PuzzleDay(1) {
    override fun main() {
        puzzleInput.forEachLine {
            log.info(it)
        }
    }
}