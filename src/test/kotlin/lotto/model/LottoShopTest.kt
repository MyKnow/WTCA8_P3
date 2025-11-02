package lotto.model

import lotto.constant.ErrorType
import lotto.constant.LottoRule
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@Suppress("NonAsciiCharacters")
class LottoShopTest {
    @Test
    fun `generateNumbers 기본값으로 반환`() {
        val numbers = LottoShop.generateNumbers()
        val lottoRange = LottoRule.START_NUMBER.value..LottoRule.END_NUMBER.value
        assertThat(numbers.size).isEqualTo(LottoRule.SIZE.value)
        assertThat(numbers.all { it in lottoRange }).isTrue
    }

    @Test
    fun `calculateLottoCount 정상 계산`() {
        val amount = 5000
        val count = LottoShop.calculateLottoCount(amount)
        assertThat(count).isEqualTo(5)
    }

    @Test
    fun `calculateLottoCount 음수 입력 시 예외`() {
        val amount = -1000
        assertThrows<IllegalArgumentException> {
            LottoShop.calculateLottoCount(amount)
        }
    }

    @Test
    fun `purchaseLottos 정상 생성`() {
        val mockProvider = object : RandomProvider {
            override fun pick(start: Int, end: Int, count: Int) = listOf(1, 2, 3, 4, 5, 6)
        }
        val lottos = LottoShop.purchaseLottos(3000, provider = mockProvider)
        assertThat(lottos.size).isEqualTo(3)
        assertThat(lottos[0].getNumbers()).containsExactly(1, 2, 3, 4, 5, 6)
    }

    @Test
    fun `purchaseLottos 중복숫자 입력 시 예외`() {
        val mockProvider = object : RandomProvider {
            override fun pick(start: Int, end: Int, count: Int) = List(count) { 42 }
        }

        val result = assertThrows<IllegalArgumentException> {
            LottoShop.purchaseLottos(3000, provider = mockProvider)
        }
        assertThat(result.message).isEqualTo(ErrorType.DUPLICATE_NUMBER.message)
    }
}