package puzzledays

enum class ShapeScore(val corrLetters: List<String>, val score: Int) {
    ROCK(listOf("A", "X"), 1),
    PAPER(listOf("B", "Y"), 2),
    SCISSORS(listOf("C", "Z"), 3)
}

enum class GameOperation(val letter: String) {
    LOSE("X"),
    DRAW("Y"),
    WIN("Z")
}

class Day2 : PuzzleDay(2) {
    var totalScoreAnswer1 = 0
    var totalScoreAnswer2 = 0

    override fun main() {
        puzzleInput.forEachLine { line ->
            val shapes =
                line.split(" ").map { getShapeByLetter(it) }
            totalScoreAnswer1 += shapes[1].score

            totalScoreAnswer1 += getGameScore(shapes[1], shapes[0])
        }
        log.info("Answer puzzle 1: $totalScoreAnswer1")

        puzzleInput.forEachLine { line ->
            val shapeLeftPlayer = getShapeByLetter(line.split(" ")[0])

            val shapeRightPlayer = when (getGameOperationByLetter(line.split(" ")[1])) {
                GameOperation.DRAW -> shapeLeftPlayer
                GameOperation.WIN -> when (shapeLeftPlayer) {
                    ShapeScore.ROCK -> ShapeScore.PAPER
                    ShapeScore.PAPER -> ShapeScore.SCISSORS
                    ShapeScore.SCISSORS -> ShapeScore.ROCK
                }
                GameOperation.LOSE -> when (shapeLeftPlayer) {
                    ShapeScore.ROCK -> ShapeScore.SCISSORS
                    ShapeScore.PAPER -> ShapeScore.ROCK
                    ShapeScore.SCISSORS -> ShapeScore.PAPER
                }
            }

            totalScoreAnswer2 += shapeRightPlayer.score

            totalScoreAnswer2 += getGameScore(shapeRightPlayer, shapeLeftPlayer)
        }
        log.info("Answer puzzle 2: $totalScoreAnswer2")
    }

    /**
     * A win returns 6
     * A draw return 3
     * A loss returns 0
     */
    private fun getGameScore(player1: ShapeScore, player2: ShapeScore): Int {
        return if (player1 == player2) {
            3
        } else if (
            (player1 == ShapeScore.ROCK && player2 == ShapeScore.SCISSORS) ||
            (player1 == ShapeScore.PAPER && player2 == ShapeScore.ROCK) ||
            (player1 == ShapeScore.SCISSORS && player2 == ShapeScore.PAPER)) {
            6
        } else {
            0
        }
    }

    private fun getShapeByLetter(letter: String): ShapeScore =
        ShapeScore.values().find { it.corrLetters.contains(letter) }!!

    private fun getGameOperationByLetter(letter: String): GameOperation =
        GameOperation.values().find { it.letter == letter }!!

}
