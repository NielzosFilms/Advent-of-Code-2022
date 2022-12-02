import helpers.Logger
import puzzledays.Day1
import puzzledays.Day2
import puzzledays.PuzzleDay

val days: List<PuzzleDay> = listOf(
    Day1(),
    Day2()
)

val log = Logger()

fun main(args: Array<String>) {
    // Run a single day if an argument is given
    if (args.isNotEmpty()) {
        val singleDay: Int? = args.first().toIntOrNull()
        if (singleDay == null || singleDay <= 0 || singleDay > days.size) {
            log.error("Failed to find PuzzleDay with index [${args.first()}]")
            return
        }
        days[singleDay - 1].main()
        return
    }

    // If no argument is given just run all the days
    days.forEachIndexed { index, puzzleDay ->
        log.divider("Day ${index + 1}")
        puzzleDay.main()
    }
}
