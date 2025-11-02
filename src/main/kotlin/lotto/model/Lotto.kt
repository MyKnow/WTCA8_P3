package lotto.model

import lotto.constant.LottoRule
import lotto.util.Validator

class Lotto(private val numbers: List<Int>) {
    init {
        val lottoRange = LottoRule.START_NUMBER.value..LottoRule.END_NUMBER.value
        Validator.validateIntegerSizeMatch(numbers, LottoRule.SIZE.value)
        Validator.validateIntegerRange(numbers, lottoRange)
        Validator.validateUniqueNumber(numbers)
        Validator.validateAscendingOrder(numbers)
    }

    fun getNumbers(): List<Int> = numbers
}
