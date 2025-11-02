package lotto.view

import camp.nextstep.edu.missionutils.Console
import lotto.constant.Message
import lotto.util.Parser

object InputView {
    private fun readInt(
        message: Message,
        reader: () -> String = { Console.readLine() },
    ): Int {
        println(message.format())
        val input = reader().trim()
        println()
        return Parser.toInteger(input)
    }

    fun readWinningLottoNumbers(reader: () -> String = { Console.readLine() }): List<Int> {
        println(Message.WINNING_LOTTO_NUMBERS.format())
        val input = reader().trim()
        println()
        return Parser.splitNumbers(input)
    }

    fun readPurchaseAmount(
        reader: () -> String = { Console.readLine() },
    ): Int = readInt(Message.PURCHASE_AMOUNT, reader)

    fun readBonusLottoNumber(
        reader: () -> String = { Console.readLine() },
    ): Int = readInt(Message.BONUS_LOTTO_NUMBERS, reader)
}
