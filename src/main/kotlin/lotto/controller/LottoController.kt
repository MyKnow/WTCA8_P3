package lotto.controller

import lotto.model.DHLottery
import lotto.model.UserWallet
import lotto.model.UserWalletCalculator
import lotto.view.InputView
import lotto.view.OutputView

object LottoController {
    fun run() {
        val amount = InputView.readPurchaseAmount()
        val wallet = UserWallet(amount)

        val lottos = wallet.purchaseLottos()
        OutputView.printLottos(lottos)

        val winningNumbers = InputView.readWinningLottoNumbers()
        val bonusNumber = InputView.readBonusLottoNumber()
        val lottery = DHLottery(winningNumbers, bonusNumber)

        val sortedResult = UserWalletCalculator.getSortedResult(wallet, lottery)
        OutputView.printStatistics(sortedResult)

        val rateOfReturn = UserWalletCalculator.getRateOfReturn(amount, sortedResult)
        OutputView.printRateOfReturn(rateOfReturn)
    }
}