package lotto.model

import lotto.constant.LottoRank
import lotto.util.Validator

object LottoRankCalculator {
    fun getRank(matchCount: Int, bonusMatched: Boolean): LottoRank {
        Validator.validateMatchCount(matchCount)
        return LottoRank.entries.firstOrNull { it.matchCount == matchCount && it.bonusRequired == bonusMatched }
            ?: LottoRank.entries.firstOrNull { it.matchCount == matchCount && !it.bonusRequired }
            ?: LottoRank.NONE
    }
}
