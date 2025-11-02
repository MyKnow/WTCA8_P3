package lotto.model

import lotto.constant.ErrorType
import lotto.constant.LottoRule
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@Suppress("NonAsciiCharacters")
class UserWalletTest {
    private val mockRandomProvider = object : RandomProvider {
        override fun pick(start: Int, end: Int, count: Int) = listOf(1, 2, 3, 4, 5, 6)
    }

    @Test
    fun `purchaseLottos 정확한 구매 금액 입력`() {
        val purchaseAmount = 10_000
        val userWallet = UserWallet(purchaseAmount)
        userWallet.purchaseLottos(mockRandomProvider)

        val expectedCount = purchaseAmount / LottoRule.PRICE.value
        assertThat(userWallet.lottos.size).isEqualTo(expectedCount)
    }

    @Test
    fun `purchaseLottos 1000원 단위가 아닌 구매 금액 입력 시 에러`() {
        val purchaseAmount = 10_100
        val result = assertThrows<IllegalArgumentException> { UserWallet(purchaseAmount) }
        assertThat(result.message).isEqualTo(ErrorType.NOT_MULTIPLE_OF_1000.message)
    }

    @Test
    fun `purchaseLottos 구매 금액에 양의 정수를 입력하지 않을 시 에러`() {
        val purchaseAmount = 0
        val result = assertThrows<IllegalArgumentException> { UserWallet(purchaseAmount) }
        assertThat(result.message).isEqualTo(ErrorType.NOT_POSITIVE_NUMBER.message)
    }
}
