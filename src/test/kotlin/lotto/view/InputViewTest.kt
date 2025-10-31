package lotto.view

import lotto.constant.ErrorType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@Suppress("NonAsciiCharacters")
class InputViewTest {

    @Test
    fun `readPurchaseAmount 정상 입력`() {
        val input = "5000"
        val result = InputView.readPurchaseAmount(input)
        assertThat(result).isEqualTo(5000)
    }

    @Test
    fun `readPurchaseAmount 숫자가 아닌 입력 시 예외`() {
        val input = "abc"
        val result = assertThrows<IllegalArgumentException> {
            InputView.readPurchaseAmount(input)
        }
        assertThat(result.message).isEqualTo(ErrorType.INVALID_INTEGER.message)
    }

    @Test
    fun `readBonusLottoNumber 정상 입력`() {
        val input = "7"
        val result = InputView.readBonusLottoNumber(input)
        assertThat(result).isEqualTo(7)
    }

    @Test
    fun `readBonusLottoNumber 숫자가 아닌 입력 시 예외`() {
        val input = "xyz"
        val result = assertThrows<IllegalArgumentException> {
            InputView.readBonusLottoNumber(input)
        }
        assertThat(result.message).isEqualTo(ErrorType.INVALID_INTEGER.message)
    }

    @Test
    fun `readWinningLottoNumbers 정상 입력`() {
        val input = "1,2,3,4,5,6"
        val result = InputView.readWinningLottoNumbers(input)
        assertThat(result).containsExactly(1, 2, 3, 4, 5, 6)
    }

    @Test
    fun `readWinningLottoNumbers 잘못된 입력 시 예외`() {
        val input = "1,2,a,4,5,6"
        val result = assertThrows<IllegalArgumentException> {
            InputView.readWinningLottoNumbers(input)
        }
        assertThat(result.message).isEqualTo(ErrorType.INVALID_INTEGER.message)
    }

    @Test
    fun `readWinningLottoNumbers 마지막 공백 입력 시 예외`() {
        val input = "1,2,a,4,5,"
        val result = assertThrows<IllegalArgumentException> {
            InputView.readWinningLottoNumbers(input)
        }
        assertThat(result.message).isEqualTo(ErrorType.INVALID_INTEGER.message)
    }
}