package lotto.model

import lotto.util.Validator

class Lotto(private val numbers: List<Int>) {
    init {
        Validator.validateIntegerSizeMatch(numbers, SIZE)
        Validator.validateIntegerRange(numbers, RANGE)
        Validator.validateUniqueNumber(numbers)
    }

    override fun toString(): String {
        return numbers.sorted().joinToString(", ", "[", "]")
    }

    companion object {
        const val SIZE = 6
        val RANGE = 1..45
    }
}
