package zip.jespersen.korekt.extension

import zip.jespersen.korekt.logging.log.Log
import java.math.BigInteger
import java.util.UUID

fun String.red(): String {
    return "${Log.RED}${this}${Log.RESET}"
}

fun String.reset(): String {
    return "${Log.RESET}${this}${Log.RESET}"
}

fun String.blue(): String {
    return "${Log.BLUE}${this}${Log.RESET}"
}

fun String.gray(): String {
    return "${Log.GRAY}${this}${Log.RESET}"
}

fun String.white(): String {
    return "${Log.WHITE}${this}${Log.RESET}"
}

fun String.green(): String {
    return "${Log.GREEN}${this}${Log.RESET}"
}

fun String.lightgreen(): String {
    return "${Log.LIGHTGREEN}${this}${Log.RESET}"
}

fun String.yellow(): String {
    return "${Log.YELLOW}${this}${Log.RESET}"
}

fun String.magenta(): String {
    return "${Log.MAGENTA}${this}${Log.RESET}"
}

fun String.toUUID(): UUID {
    if (contains("-")) return UUID.fromString(this)
    return UUID(BigInteger(substring(0, 16), 16).toLong(), BigInteger(substring(16, 32), 16).toLong())
}

enum class Case {
    CAMEL,
    SNAKE,
    KEBAB,
    PASCAL,
}

var CASE_DELIMITER_REGEX = Regex("(?<!^)(?=[A-Z])|[_\\-\\s]+")

fun String.formatCase(case: Case): String = CASE_DELIMITER_REGEX.split(this)
    .filter { it.isNotEmpty() }
    .map { it.lowercase() }
    .run {
        when (case) {
            Case.CAMEL -> mapIndexed { index, word ->
                if (index == 0) word else word.capitalizeFirstLetter()
            }.joinToString("")

            Case.SNAKE -> joinToString("_")

            Case.KEBAB -> joinToString("-")

            Case.PASCAL -> joinToString("") { it.capitalizeFirstLetter() }
        }
    }

/**
 * Capitalizes the first letter of a string.
 *
 * If the first character of the string is lowercase, it is converted to uppercase. Otherwise, the string is returned unchanged.
 *
 * @return The string with its first letter capitalized.
 */
fun String.capitalizeFirstLetter(): String = replaceFirstChar {
    if (it.isLowerCase()) it.titlecase() else it.toString()
}