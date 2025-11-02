package lotto.model

import lotto.constant.ErrorType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@Suppress("NonAsciiCharacters")
class LottoTest {
    @Test
    fun `로또 번호의 개수가 6개가 넘어가면 예외가 발생한다`() {
        val overRangeNumbers = listOf(1, 2, 3, 4, 5, 6, 7)
        val result = assertThrows<IllegalArgumentException> {
            Lotto(overRangeNumbers)
        }
        assertThat(result.message).isEqualTo(ErrorType.INVALID_INPUT_COUNT.message)
    }

    @Test
    fun `로또 번호에 중복된 숫자가 있으면 예외가 발생한다`() {
        val duplicatedNumbers = listOf(1, 2, 3, 4, 5, 5)
        val result = assertThrows<IllegalArgumentException> {
            Lotto(duplicatedNumbers)
        }
        assertThat(result.message).isEqualTo(ErrorType.DUPLICATE_NUMBER.message)
    }

    @Test
    fun `로또 번호는 항상 오름차순 정렬하여 출력해야 한다`() {
        val lottoNumbers = listOf(6, 5, 4, 3, 2, 1)
        val result = assertThrows<IllegalArgumentException> {
            Lotto(lottoNumbers)
        }
        assertThat(result.message).isEqualTo(ErrorType.NOT_ASCENDING_ORDER.message)
    }

    @Test
    fun `getNumbers 반환 리스트는 불변`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val lotto = Lotto(numbers)

        val result = lotto.getNumbers()
        assertThrows<UnsupportedOperationException> {
            (result as MutableList).add(7)
        }
    }
}
