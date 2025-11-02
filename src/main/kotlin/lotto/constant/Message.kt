package lotto.constant

import lotto.util.Parser
import lotto.util.Validator

enum class Message(private val template: String) {
    PURCHASE_AMOUNT("구입금액을 입력해 주세요."),
    WINNING_LOTTO_NUMBERS("당첨 번호를 입력해 주세요."),
    BONUS_LOTTO_NUMBERS("보너스 번호를 입력해 주세요."),
    PURCHASED_COUNT("%d개를 구매했습니다."),
    MATCH_LOG("%s개 일치 (%s원) - %s개"),
    MATCH_LOG_WITH_BONUS("%s개 일치, 보너스 볼 일치 (%s원) - %s개"),
    RATES_OF_RETURN("총 수익률은 %.1f%%입니다."),
    RESULT_TITLE("당첨 통계\n---");

    fun format(vararg args: Any): String {
        val expectedCount = Parser.countArguments(template)

        Validator.validateArgumentCounts(args.size, expectedCount)

        return template.format(*args)
    }
}
