package lotto.util

import lotto.constant.ErrorType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@Suppress("NonAsciiCharacters")
class ValidatorTest {

    @Test
    fun `validateNonEmpty 정상 입력`() {
        Validator.validateNonEmpty("1")
    }

    @Test
    fun `validateNonEmpty 빈 문자열 입력 시 예외`() {
        val exception = assertThrows<IllegalArgumentException> {
            Validator.validateNonEmpty(" ")
        }
        assertThat(exception.message).isEqualTo(ErrorType.EMPTY_INPUT.message)
    }

    @Test
    fun `validateIntegerFormat 정상 입력`() {
        Validator.validateIntegerFormat("123")
    }

    @Test
    fun `validateIntegerFormat 정수가 아닌 문자열 시 예외`() {
        val exception = assertThrows<IllegalArgumentException> {
            Validator.validateIntegerFormat("abc")
        }
        assertThat(exception.message).isEqualTo(ErrorType.INVALID_INTEGER.message)
    }

    @Test
    fun `validateIntegerFormat 정수가 아닌 부동소수 시 예외`() {
        val exception = assertThrows<IllegalArgumentException> {
            Validator.validateIntegerFormat("123.2")
        }
        assertThat(exception.message).isEqualTo(ErrorType.INVALID_INTEGER.message)
    }

    @Test
    fun `validateIntegerSizeMatch 정상 입력`() {
        Validator.validateIntegerSizeMatch(listOf(1, 2, 3), 3)
    }

    @Test
    fun `validateIntegerSizeMatch 크기 불일치 시 예외`() {
        val exception = assertThrows<IllegalArgumentException> {
            Validator.validateIntegerSizeMatch(listOf(1, 2), 3)
        }
        assertThat(exception.message).isEqualTo(ErrorType.INVALID_INPUT_COUNT.message)
    }

    @Test
    fun `validateIntegerRange 정상 입력`() {
        Validator.validateIntegerRange(1, 1, 10)
        Validator.validateIntegerRange(5, 1, 10)
        Validator.validateIntegerRange(10, 1, 10)
    }

    @Test
    fun `validateIntegerRange 범위 벗어날 시 예외`() {
        val exception = assertThrows<IllegalArgumentException> {
            Validator.validateIntegerRange(11, 1, 10)
        }
        assertThat(exception.message).isEqualTo(ErrorType.INVALID_NUMBER_RANGE.message)
    }

    @Test
    fun `validateUniqueNumber 정상 입력`() {
        Validator.validateUniqueNumber(listOf(1, 2, 3))
    }

    @Test
    fun `validateUniqueNumber 중복 시 예외`() {
        val exception = assertThrows<IllegalArgumentException> {
            Validator.validateUniqueNumber(listOf(1, 2, 2))
        }
        assertThat(exception.message).isEqualTo(ErrorType.DUPLICATE_NUMBER.message)
    }

    @Test
    fun `validatePositiveInteger 정상 입력`() {
        Validator.validatePositiveInteger(5)
    }

    @Test
    fun `validatePositiveInteger 음수 입력 시 예외`() {
        val exception = assertThrows<IllegalArgumentException> {
            Validator.validatePositiveInteger(-1)
        }
        assertThat(exception.message).isEqualTo(ErrorType.NOT_POSITIVE_NUMBER.message)
    }

    @Test
    fun `validateMultipleOf1000 정상 입력`() {
        Validator.validateMultipleOf1000(3000)
    }

    @Test
    fun `validateMultipleOf1000 1000 배수 아니면 예외`() {
        val exception = assertThrows<IllegalArgumentException> {
            Validator.validateMultipleOf1000(2500)
        }
        assertThat(exception.message).isEqualTo(ErrorType.NOT_MULTIPLE_OF_1000.message)
    }
}