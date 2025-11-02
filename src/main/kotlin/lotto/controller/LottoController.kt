package lotto.controller

import lotto.constant.LottoRank
import lotto.model.DHLottery
import lotto.model.Lotto
import lotto.model.UserWallet
import lotto.model.UserWalletCalculator
import lotto.view.InputView
import lotto.view.OutputView
import java.util.SortedMap

object LottoFactory {
    fun createUserWallet(): UserWallet {
        val amount = InputView.readPurchaseAmount()
        return UserWallet(amount)
    }

    fun createLottery(): DHLottery {
        val winningNumbers = InputView.readWinningLottoNumbers()
        val bonusNumber = InputView.readBonusLottoNumber()
        return DHLottery(winningNumbers, bonusNumber)
    }
}

class LottoController {
    private val wallet: UserWallet = LottoFactory.createUserWallet()
    private val lottery: DHLottery by lazy { LottoFactory.createLottery() }
    val result: SortedMap<LottoRank, Int> by lazy { handleRankResult() }

    fun handlePurchase(): List<Lotto> {
        return wallet.purchaseLottos()
    }

    fun handleRankResult(): SortedMap<LottoRank, Int> {
        return UserWalletCalculator.getSortedResult(wallet, lottery)
    }

    fun handleRateOfReturn(): Double {
        return UserWalletCalculator.getRateOfReturn(wallet.amount, result)
    }

    fun run() {
        val lottos = handlePurchase()
        val rankResult = result
        val rate = handleRateOfReturn()

        // 출력까지 여기서 처리
        OutputView.printLottos(lottos)
        OutputView.printStatistics(rankResult)
        OutputView.printRateOfReturn(rate)
    }
}
