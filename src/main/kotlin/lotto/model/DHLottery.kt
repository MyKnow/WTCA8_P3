package lotto.model

import lotto.constant.LottoRank
import lotto.constant.LottoRule
import lotto.util.Validator

class DHLottery(
    private val winningNumbers: List<Int>,
    private val bonusNumber: Int,
) {
    init {
        val lottoRange = LottoRule.START_NUMBER.value..LottoRule.END_NUMBER.value
        val entireNumbers: List<Int> = winningNumbers + bonusNumber
        Validator.validateUniqueNumber(entireNumbers)
        Validator.validateIntegerRange(entireNumbers, lottoRange)
        Validator.validateAscendingOrder(winningNumbers)
        Validator.validateIntegerSizeMatch(winningNumbers, LottoRule.SIZE.value)
    }

    fun getLottoRank(lotto: Lotto): LottoRank {
        val purchasedLottoNumbers = lotto.getNumbers().toSet()
        val winningLottoNumbers = winningNumbers.toSet()
        val matchCount = purchasedLottoNumbers.intersect(winningLottoNumbers).size
        val bonusMatched = bonusNumber in purchasedLottoNumbers

        return LottoRankCalculator.getRank(matchCount, bonusMatched)
    }

    fun checkAllLottos(lottos: List<Lotto>): List<LottoRank> = lottos.map { getLottoRank(it) }
}
