package lotto.util

import lotto.constant.ErrorType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@Suppress("NonAsciiCharacters")
class ParserTest {

    @Test
    fun `toInteger 정상 입력`() {
        val result = Parser.toInteger("123")
        assertThat(result).isEqualTo(123)
    }

    @Test
    fun `toInteger 공백 입력 시 예외`() {
        val exception = assertThrows<IllegalArgumentException> {
            Parser.toInteger(" ")
        }
        assertThat(exception.message).isEqualTo(ErrorType.EMPTY_INPUT.message)
    }

    @Test
    fun `toInteger 숫자 아님 예외`() {
        val exception = assertThrows<IllegalArgumentException> {
            Parser.toInteger("abc")
        }
        assertThat(exception.message).isEqualTo(ErrorType.INVALID_INTEGER.message)
    }

    @Test
    fun `parseByDelimiters 정상 입력`() {
        val result = Parser.parseByDelimiters("1,2,3")
        assertThat(result).containsExactly("1", "2", "3")
    }

    @Test
    fun `parseByDelimiters 공백 입력 시 예외`() {
        val exception = assertThrows<IllegalArgumentException> {
            Parser.parseByDelimiters("")
        }
        assertThat(exception.message).isEqualTo(ErrorType.EMPTY_INPUT.message)
    }

    @Test
    fun `splitNumbers 정상 입력`() {
        val result = Parser.splitNumbers("1,2,3")
        assertThat(result).containsExactly(1, 2, 3)
    }

    @Test
    fun `splitNumbers 입력에 공백 포함`() {
        val result = Parser.splitNumbers("1, 2, 3")
        assertThat(result).containsExactly(1, 2, 3)
    }

    @Test
    fun `splitNumbers 중 숫자 아닌 값 포함 시 예외`() {
        val exception = assertThrows<IllegalArgumentException> {
            Parser.splitNumbers("1,2,a")
        }
        assertThat(exception.message).isEqualTo(ErrorType.INVALID_INTEGER.message)
    }

    @Test
    fun `splitNumbers 빈 문자열 입력 시 예외`() {
        val exception = assertThrows<IllegalArgumentException> {
            Parser.splitNumbers(" ")
        }
        assertThat(exception.message).isEqualTo(ErrorType.EMPTY_INPUT.message)
    }
}