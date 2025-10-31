package lotto.view

import camp.nextstep.edu.missionutils.Console
import lotto.constant.Message
import lotto.util.Parser

object InputView {
    private fun readInt(message: Message, input: String): Int {
        println(message.format())
        return Parser.toInteger(input)
    }

    fun readWinningLottoNumbers(input: String = Console.readLine()): List<Int> {
        println(Message.WINNING_LOTTO_NUMBERS.format())
        return Parser.splitNumbers(input)
    }

    fun readPurchaseAmount(input: String = Console.readLine()): Int = readInt(Message.PURCHASE_AMOUNT, input)
    fun readBonusLottoNumber(input: String = Console.readLine()): Int = readInt(Message.BONUS_LOTTO_NUMBERS, input)
}