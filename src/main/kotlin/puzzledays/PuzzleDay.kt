package puzzledays

import helpers.Logger
import java.io.File
import java.io.FileNotFoundException

abstract class PuzzleDay(dayNumber: Int) {
    protected val log = Logger()
    protected var puzzleInput: File =
        PuzzleDay::class.java.getResource("/$dayNumber/input.txt")?.toURI()?.let { File(it) }
            ?: throw FileNotFoundException("Failed to find puzzleInput for day [$dayNumber]")

    abstract fun main()
}
