package lotto.model

import lotto.constant.LottoRule
import lotto.util.Validator

object LottoShop {
    fun calculateLottoCount(amount: Int): Int {
        Validator.validateAmountRule(amount)
        return amount / LottoRule.PRICE.value
    }

    fun purchaseLottos(amount: Int, provider: RandomProvider = DefaultRandomProvider): List<Lotto> {
        val lottoCount = calculateLottoCount(amount)
        return List(lottoCount) { Lotto(generateNumbers(provider = provider)) }
    }

    fun generateNumbers(
        start: Int = LottoRule.START_NUMBER.value,
        end: Int = LottoRule.END_NUMBER.value,
        count: Int = LottoRule.SIZE.value,
        provider: RandomProvider = DefaultRandomProvider,
    ): List<Int> = provider.pick(start, end, count)
}
