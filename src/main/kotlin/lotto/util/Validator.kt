package lotto.util

import lotto.constant.ErrorType

object Validator {
    private const val MIN_POSITIVE_NUMBER = 1
    private const val MULTIPLE_TARGET_NUMBER = 1000

    @Suppress("NOTHING_TO_INLINE")
    private inline fun requireWithError(condition: Boolean, error: ErrorType) {
        require(condition) { error.message }
    }

    fun validateNonEmpty(input: String) {
        requireWithError(input.isNotBlank(), ErrorType.EMPTY_INPUT)
    }

    fun validateIntegerFormat(input: String) {
        requireWithError(input.toIntOrNull() != null, ErrorType.INVALID_INTEGER)
    }

    fun validateIntegerSizeMatch(input: List<Int>, size: Int) {
        requireWithError(input.size == size, ErrorType.INVALID_INPUT_COUNT)
    }

    fun validateIntegerRange(input: List<Int>, range: IntRange) {
        requireWithError(input.all { it in range }, ErrorType.INVALID_NUMBER_RANGE)
    }

    fun validateUniqueNumber(input: List<Int>) {
        requireWithError(input.size == input.toSet().size, ErrorType.DUPLICATE_NUMBER)
    }

    fun validatePositiveInteger(input: Int) {
        requireWithError(MIN_POSITIVE_NUMBER <= input, ErrorType.NOT_POSITIVE_NUMBER)
    }

    fun validateMultipleOf1000(input: Int) {
        requireWithError(input % MULTIPLE_TARGET_NUMBER == 0, ErrorType.NOT_MULTIPLE_OF_1000)
    }

    fun validateArgumentCounts(actual: Int, expected: Int) {
        requireWithError(actual == expected, ErrorType.MESSAGE_ARGUMENT_NOT_MATCHED)
    }
}