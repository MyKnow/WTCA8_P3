package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@Suppress("NonAsciiCharacters")
class LottoTest {
    @Test
    fun `로또 번호의 개수가 6개가 넘어가면 예외가 발생한다`() {
        val overRangeNumbers = listOf(1, 2, 3, 4, 5, 6, 7)
        assertThrows<IllegalArgumentException> {
            Lotto(overRangeNumbers)
        }
    }

    @Test
    fun `로또 번호에 중복된 숫자가 있으면 예외가 발생한다`() {
        val duplicatedNumbers = listOf(1, 2, 3, 4, 5, 5)
        assertThrows<IllegalArgumentException> {
            Lotto(duplicatedNumbers)
        }
    }

    @Test
    fun `로또 번호는 항상 오름차순 정렬하여 출력해야 한다`() {
        val lottoNumbers = listOf(6, 5, 4, 3, 2, 1)
        assertThat(Lotto(lottoNumbers).toString()).isEqualTo("[1, 2, 3, 4, 5, 6]")
    }
}
