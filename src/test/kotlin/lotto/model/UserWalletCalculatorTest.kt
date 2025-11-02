package lotto.model

import lotto.constant.ErrorType
import lotto.constant.LottoRank
import lotto.constant.LottoRule
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@Suppress("NonAsciiCharacters")
class UserWalletCalculatorTest {
    private val userWallet = UserWallet(AMOUNT)
    private val lottery = DHLottery(WINNING_NUMBERS, BONUS_NUMBER)

    private val mockRandomProvider = object : RandomProvider {
        override fun pick(start: Int, end: Int, count: Int) = WINNING_NUMBERS
    }

    @Test
    fun `getSortedResult 정상 입력 시 구매 수량 확인`() {
        userWallet.purchaseLottos(mockRandomProvider)
        val result = UserWalletCalculator.getSortedResult(userWallet, lottery)
        assertThat(result.entries.sumOf { it.value }).isEqualTo(LottoShop.calculateLottoCount(AMOUNT))
    }

    @Test
    fun `getSortedResult 정상 입력 시 정렬 상태 확인`() {
        userWallet.purchaseLottos(mockRandomProvider)
        val result = UserWalletCalculator.getSortedResult(userWallet, lottery)
        result.entries.zipWithNext { a, b -> a.key.reward < b.key.reward }
    }

    @Test
    fun `getRateOfReturn 정상 입력 시 계산 확인`() {
        userWallet.purchaseLottos(mockRandomProvider)
        val result = UserWalletCalculator.getSortedResult(userWallet, lottery)
        val actualRate = UserWalletCalculator.getRateOfReturn(AMOUNT, result)
        val expectedRate = LottoRank.FIRST.reward.toDouble() * (AMOUNT / LottoRule.PRICE.value) / AMOUNT * 100
        assertThat(actualRate).isEqualTo(expectedRate)
        println(expectedRate)
    }

    @Test
    fun `getRateOfReturn 비정상적으로 큰 수익률 확인`() {
        userWallet.purchaseLottos(mockRandomProvider)
        val result = UserWalletCalculator.getSortedResult(userWallet, lottery)
        val actualRate = UserWalletCalculator.getRateOfReturn(AMOUNT, result)
        val expectedRate = LottoRank.FIRST.reward.toDouble() * (AMOUNT / LottoRule.PRICE.value) / AMOUNT * 100
        assertThat(actualRate).isEqualTo(expectedRate)
    }

    @Test
    fun `getRateOfReturn 1인 당 구매 한도를 넘는 로또 구매 시 에러 발생`() {
        val largeAmount = 1_000_000_000
        val result = assertThrows<IllegalArgumentException> { UserWallet(largeAmount) }
        assertThat(result.message).isEqualTo(ErrorType.MAX_PURCHASE_LIMIT.message)
    }

    companion object {
        private const val AMOUNT = 1_000
        private val WINNING_NUMBERS = listOf(1, 2, 3, 4, 5, 6)
        private const val BONUS_NUMBER = 7
    }
}