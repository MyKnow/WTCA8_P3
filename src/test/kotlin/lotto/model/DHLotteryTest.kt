package lotto.model

import lotto.constant.ErrorType
import lotto.constant.LottoRank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.assertDoesNotThrow

@Suppress("NonAsciiCharacters")
class DHLotteryTest {
    @Test
    fun `정상 생성 - 유효한 winningNumbers와 bonusNumber`() {
        val numbers = FIRST_NUMBERS
        val bonus = BONUS_NUMBER
        assertDoesNotThrow { DHLottery(numbers, bonus) }
    }

    @Test
    fun `중복 숫자가 포함되면 예외`() {
        val numbers = FIRST_NUMBERS
        val bonus = 6
        val result = assertThrows<IllegalArgumentException> {
            DHLottery(numbers, bonus)
        }
        assertThat(result.message).isEqualTo(ErrorType.DUPLICATE_NUMBER.message)
    }

    @Test
    fun `범위 벗어난 숫자가 포함되면 예외`() {
        val numbers = listOf(0, 2, 3, 4, 5, 6)
        val result = assertThrows<IllegalArgumentException> {
            DHLottery(numbers, BONUS_NUMBER)
        }
        assertThat(result.message).isEqualTo(ErrorType.INVALID_NUMBER_RANGE.message)
    }

    @Test
    fun `오름차순이 아니면 예외`() {
        val numbers = listOf(1, 3, 2, 4, 5, 6)
        val result = assertThrows<IllegalArgumentException> {
            DHLottery(numbers, BONUS_NUMBER)
        }
        assertThat(result.message).isEqualTo(ErrorType.NOT_ASCENDING_ORDER.message)
    }

    @Test
    fun `getLottoRank - 1등`() {
        val lotto = Lotto(FIRST_NUMBERS)
        assertThat(LOTTERY.getLottoRank(lotto)).isEqualTo(LottoRank.FIRST)
    }

    @Test
    fun `checkAllLottos 정상 입력`() {
        val lottos = listOf(
            Lotto(FIRST_NUMBERS),
            Lotto(SECOND_NUMBERS),
            Lotto(THIRD_NUMBERS),
            Lotto(FOURTH_NUMBERS),
            Lotto(FIFTH_NUMBERS),
            Lotto(NONE_NUMBERS)
        )

        val ranks = LOTTERY.checkAllLottos(lottos)
        assertThat(ranks).containsExactly(
            LottoRank.FIRST,
            LottoRank.SECOND,
            LottoRank.THIRD,
            LottoRank.FOURTH,
            LottoRank.FIFTH,
            LottoRank.NONE
        )
    }

    companion object {
        private val FIRST_NUMBERS = listOf(1, 2, 3, 4, 5, 6)
        private val SECOND_NUMBERS = listOf(1, 2, 3, 4, 5, 7)
        private val THIRD_NUMBERS = listOf(1, 2, 3, 4, 5, 8)
        private val FOURTH_NUMBERS = listOf(1, 2, 3, 4, 8, 9)
        private val FIFTH_NUMBERS = listOf(1, 2, 3, 8, 9, 10)
        private val NONE_NUMBERS = listOf(1, 2, 8, 9, 10, 11)

        private const val BONUS_NUMBER = 7
        private val LOTTERY = DHLottery(FIRST_NUMBERS, BONUS_NUMBER)
    }
}