package lotto.util

import lotto.constant.ErrorType
import lotto.constant.LottoRule

object Validator {
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
        requireWithError(1 <= input, ErrorType.NOT_POSITIVE_NUMBER)
    }

    fun validateMultipleOf1000(input: Int) {
        requireWithError(input % LottoRule.PRICE.value == 0, ErrorType.NOT_MULTIPLE_OF_1000)
    }

    fun validateArgumentCounts(actual: Int, expected: Int) {
        requireWithError(actual == expected, ErrorType.MESSAGE_ARGUMENT_NOT_MATCHED)
    }

    fun validateAscendingOrder(input: List<Int>) {
        requireWithError(input.zipWithNext().all { (a, b) -> a < b }, ErrorType.NOT_ASCENDING_ORDER)
    }

    fun validateMatchCount(count: Int) {
        requireWithError(count in 0..LottoRule.SIZE.value, ErrorType.INVALID_MATCH_COUNT)
    }
}
