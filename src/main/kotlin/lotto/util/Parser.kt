package lotto.util

object Parser {
    private val delimiters = listOf(",")
    private val regex: Regex = delimiters.joinToString("|") { Regex.escape(it) }.toRegex()

    fun toInteger(input: String): Int {
        Validator.validateNonEmpty(input)
        Validator.validateIntegerFormat(input)
        return input.toInt()
    }

    fun parseByDelimiters(input: String): List<String> {
        Validator.validateNonEmpty(input)
        return input.split(regex)
    }

    fun splitNumbers(input: String): List<Int> {
        val tokens = parseByDelimiters(input).map { it.trim() }
        return tokens.map { toInteger(it) }
    }
}