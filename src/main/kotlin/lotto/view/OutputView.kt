package lotto.view

import lotto.constant.LottoRank
import lotto.constant.Message
import lotto.model.Lotto
import lotto.util.NumberFormatter.formatWithComma
import java.util.*

object OutputView {
    private fun formatLottoNumbers(lotto: Lotto): String = lotto.getNumbers().joinToString(", ", "[", "]")

    fun printLottos(lottos: List<Lotto>) {
        println(Message.PURCHASED_COUNT.format(lottos.size))
        lottos.forEach { println(formatLottoNumbers(it)) }
        println()
    }

    private fun formatLottoResultByRanking(rank: LottoRank, count: Int) {
        val formattedReward = formatWithComma(rank.reward)
        val args = listOf(rank.matchCount, formattedReward, count).map { it.toString() }
        if (rank.bonusRequired) println(Message.MATCH_LOG_WITH_BONUS.format(*args.toTypedArray()))
        else println(Message.MATCH_LOG.format(*args.toTypedArray()))
    }

    fun printStatistics(result: SortedMap<LottoRank, Int>) {
        println(Message.RESULT_TITLE.format())
        result.map { formatLottoResultByRanking(it.key, it.value) }
        println()
    }

    fun printRateOfReturn(rate: Double) {
        println(Message.RATES_OF_RETURN.format(rate))
    }
}