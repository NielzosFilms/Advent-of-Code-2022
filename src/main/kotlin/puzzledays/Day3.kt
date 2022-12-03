package puzzledays

class Day3 : PuzzleDay(3) {
    private val characters = 'a'..'z'

    override fun main() {
        var puzzle1Sum = 0
        var puzzle2Sum = 0

        puzzleInput.forEachLine { line ->
            val firstCompartment = line.substring(0, line.length / 2)
            val secondCompartment = line.substring(line.length / 2)

            val item = firstCompartment.find { secondCompartment.contains(it) }!!
            puzzle1Sum += getCharIndex(item)
        }
        log.info("Answer puzzle 1: $puzzle1Sum")

        var groupBadgeCount: MutableMap<Char, Int> = mutableMapOf()
        for (i in 0 until puzzleInput.readLines().size step 3) {
            val elf1 = puzzleInput.readLines()[i]
            val elf2 = puzzleInput.readLines()[i + 1]
            val elf3 = puzzleInput.readLines()[i + 2]

            groupBadgeCount = addToMapWhenElvesMatchCharacter(groupBadgeCount, elf1, elf2)
            groupBadgeCount = addToMapWhenElvesMatchCharacter(groupBadgeCount, elf1, elf3)
            groupBadgeCount = addToMapWhenElvesMatchCharacter(groupBadgeCount, elf2, elf3)

            val groupBadge = groupBadgeCount.entries.maxBy { it.value }.key
            puzzle2Sum += getCharIndex(groupBadge)

            groupBadgeCount.clear()
        }
        log.info("Answer puzzle 2: $puzzle2Sum")
    }

    private fun getCharIndex(char: Char): Int =
        1 + characters.indexOf(char.lowercaseChar()) + if (char.isUpperCase()) 26 else 0

    private fun addToMapWhenElvesMatchCharacter(charMap: MutableMap<Char, Int>, elf1: String, elf2: String): MutableMap<Char, Int> {
        val charsAdded: MutableList<Char> = mutableListOf()
        val matchingItems = elf1.filter { elf2.contains(it) }
        matchingItems.forEach {
            if (!charsAdded.contains(it)) {
                charMap[it] = charMap.getOrDefault(it, 0) + 1
                charsAdded.add(it)
            }
        }
        return charMap
    }
}