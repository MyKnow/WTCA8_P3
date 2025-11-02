package lotto.model

import lotto.model.LottoShop.calculateLottoCount
import lotto.model.LottoShop.generateNumbers
import lotto.util.Validator

class UserWallet(val amount: Int) {
    var lottos = listOf<Lotto>()
        private set

    init {
        Validator.validateAmountRule(amount)
    }

    fun purchaseLottos(provider: RandomProvider = DefaultRandomProvider): List<Lotto> {
        val lottoCount = calculateLottoCount(amount)
        lottos = List(lottoCount) { Lotto(generateNumbers(provider = provider)) }
        return lottos
    }
}