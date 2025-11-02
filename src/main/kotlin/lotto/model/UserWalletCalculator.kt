package lotto.model

import lotto.constant.LottoRank
import lotto.util.Validator
import java.util.SortedMap

object UserWalletCalculator {
    fun getSortedResult(userWallet: UserWallet, lottery: DHLottery): SortedMap<LottoRank, Int> {
        val ranks = lottery.checkAllLottos(userWallet.lottos)
        val countedRanks = ranks.groupingBy { it }.eachCount()
        val allRanks = LottoRank.entries.associateWith { 0 }.toMutableMap()
        for ((rank, count) in countedRanks) {
            allRanks[rank] = count
        }
        return allRanks.toSortedMap(compareBy { it.reward })
    }

    fun getRateOfReturn(amount: Int, result: Map<LottoRank, Int>): Double {
        Validator.validatePositiveInteger(amount)
        val totalProfit = result.entries.sumOf { (rank, count) -> rank.reward.toLong() * count }
        return (totalProfit.toDouble() / amount) * 100
    }
}
