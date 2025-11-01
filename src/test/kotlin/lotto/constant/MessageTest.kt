package lotto.constant

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

@Suppress("NonAsciiCharacters")
class MessageTest {

    @Test
    fun `format 정상 동작 - 인자 수 일치`() {
        val result = Message.PURCHASED_COUNT.format(5)
        assertThat(result).isEqualTo("5개를 구매했습니다.")
    }

    @Test
    fun `format 정상 동작 - 부동소수 포함`() {
        val result = Message.RATES_OF_RETURN.format(62.5)
        assertThat(result).isEqualTo("총 수익률은 62.5%입니다.")
    }

    @Test
    fun `format 정상 동작 - 여러 인자`() {
        val result = Message.MATCH_LOG_WITH_BONUS.format(3, "5000", 2)
        assertThat(result).isEqualTo("3개 일치 (5000원) - 2개")
    }

    @Test
    fun `format 예외 - 인자 수 불일치`() {
        val exception = assertThrows<IllegalArgumentException> {
            Message.MATCH_LOG.format(3, "5000") // 3번째 인자 누락
        }
        assertThat(exception.message).contains(ErrorType.MESSAGE_ARGUMENT_NOT_MATCHED.message)
    }

    @Test
    fun `format 예외 - 과도한 인자`() {
        val exception = assertThrows<IllegalArgumentException> {
            Message.WINNING_LOTTO_NUMBERS.format("crong", "extra")
        }
        assertThat(exception.message).isEqualTo(ErrorType.MESSAGE_ARGUMENT_NOT_MATCHED.message)
    }

    @Test
    fun `format 정상 동작 - 인자 없는 메시지`() {
        assertDoesNotThrow { Message.RESULT_TITLE.format() }
    }
}
