package helpers

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class Logger {
    private val reset = "\u001B[0m"
    private val red = "\u001B[31m"
    private val green = "\u001B[32m"
    private val yellow = "\u001B[33m"
    private val blue = "\u001B[34m"
    private val purple = "\u001B[35m"
    private val cyan = "\u001B[36m"

    fun info(msg: Any) = printToConsole("${blue}INFO$reset", msg.toString())
    fun debug(msg: Any) = printToConsole("${purple}DEBUG$reset", msg.toString())
    fun warn(msg: Any) = printToConsole("${cyan}WARN$reset", msg.toString())
    fun error(msg: Any) = printToConsole("${red}ERROR$reset", msg.toString())

    fun divider(title: String = "") {
        val formattedTitle = if (title.isNotEmpty()) "$title " else ""
        val dividerSize = 57 * 2 - formattedTitle.length
        println("\n$purple$formattedTitle$reset${"=".repeat(dividerSize)}\n")
    }

    private fun getTime(): String = DateTimeFormatter.ofPattern("HH:mm:ss.SSS").format(OffsetDateTime.now())

    private fun getCalledClass(): String =
        StackWalker.getInstance().walk { frames ->
            frames.skip(3).findFirst().map { it.className.split(".").last().split("$").first() }.orElse("")
        }

    private fun printToConsole(level: String, msg: String) {
        val time = getTime()
        val calledClass = getCalledClass()

        val formatted = String.format("%s %-30s %-16s", "[$cyan$time$reset]", "[$yellow$calledClass$reset]", "[$level]")
        println("$formatted $green>$reset $msg")
    }
}