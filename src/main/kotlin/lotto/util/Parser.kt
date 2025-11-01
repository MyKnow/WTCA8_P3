package lotto.util

object Parser {

    fun toInteger(input: String): Int {
        Validator.validateNonEmpty(input)
        Validator.validateIntegerFormat(input)
        return input.toInt()
    }

    fun parseByDelimiters(input: String): List<String> {
        Validator.validateNonEmpty(input)
        val regex = Regex("[,]")
        return input.split(regex)
    }

    fun splitNumbers(input: String): List<Int> {
        val tokens = parseByDelimiters(input).map { it.trim() }
        return tokens.map { toInteger(it) }
    }

    fun countArguments(input: String): Int {
        return Regex("%[\\w.]*[a-zA-Z]").findAll(input).count()
    }
}
