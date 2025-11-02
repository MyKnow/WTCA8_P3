package lotto.util

import lotto.constant.ErrorType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

@Suppress("NonAsciiCharacters")
class ValidatorTest {

    @Test
    fun `validateNonEmpty 정상 입력`() {
        assertDoesNotThrow { Validator.validateNonEmpty("1") }
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
        assertDoesNotThrow { Validator.validateIntegerFormat("123") }
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
        assertDoesNotThrow { Validator.validateIntegerSizeMatch(listOf(1, 2, 3, 4, 5, 6), TEST_SIZE) }
    }

    @Test
    fun `validateIntegerSizeMatch 크기 불일치 시 예외`() {
        val exception = assertThrows<IllegalArgumentException> {
            Validator.validateIntegerSizeMatch(listOf(1, 2), TEST_SIZE)
        }
        assertThat(exception.message).isEqualTo(ErrorType.INVALID_INPUT_COUNT.message)
    }

    @Test
    fun `validateIntegerRange 정상 입력`() {
        val sampleNumbers = listOf<Int>(1, 5, 10)
        assertDoesNotThrow { Validator.validateIntegerRange(sampleNumbers, TEST_RANGE) }
    }

    @Test
    fun `validateIntegerRange 범위 벗어날 시 예외`() {
        val exception = assertThrows<IllegalArgumentException> {
            Validator.validateIntegerRange(listOf<Int>(46), TEST_RANGE)
        }
        assertThat(exception.message).isEqualTo(ErrorType.INVALID_NUMBER_RANGE.message)
    }

    @Test
    fun `validateUniqueNumber 정상 입력`() {
        assertDoesNotThrow { Validator.validateUniqueNumber(listOf(1, 2, 3)) }
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
        assertDoesNotThrow { Validator.validatePositiveInteger(5) }
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
        assertDoesNotThrow { Validator.validateMultipleOf1000(3000) }
    }

    @Test
    fun `validateMultipleOf1000 1000 배수 아니면 예외`() {
        val exception = assertThrows<IllegalArgumentException> {
            Validator.validateMultipleOf1000(2500)
        }
        assertThat(exception.message).isEqualTo(ErrorType.NOT_MULTIPLE_OF_1000.message)
    }

    @Test
    fun `validateArgumentCounts 정상 입력`() {
        assertDoesNotThrow { Validator.validateArgumentCounts(1, 1) }
    }

    @Test
    fun `validateArgumentCounts 매개 변수의 갯수가 다를 경우 예외`() {
        val exception = assertThrows<IllegalArgumentException> {
            Validator.validateArgumentCounts(1, 2)
        }
        assertThat(exception.message).isEqualTo(ErrorType.MESSAGE_ARGUMENT_NOT_MATCHED.message)
    }

    @Test
    fun `validateAscendingOrder 정상 입력`() {
        assertDoesNotThrow { Validator.validateAscendingOrder(listOf(1, 2, 3, 4, 5, 6)) }
    }

    @Test
    fun `validateAscendingOrder 비정렬 입력 시 예외`() {
        val exception = assertThrows<IllegalArgumentException> {
            Validator.validateAscendingOrder(listOf(1, 3, 2, 5, 4))
        }
        assertThat(exception.message).isEqualTo(ErrorType.NOT_ASCENDING_ORDER.message)
    }

    @Test
    fun `validateMatchCount 정상 입력`() {
        assertDoesNotThrow { Validator.validateMatchCount(TEST_SIZE) }
    }

    @Test
    fun `validateMatchCount 비정상 갯수 입력 시 예외`() {
        val result = assertThrows<IllegalArgumentException> {
            Validator.validateMatchCount(TEST_SIZE + 1)
        }
        assertThat(result.message).isEqualTo(ErrorType.INVALID_MATCH_COUNT.message)
    }

    companion object {
        const val TEST_SIZE = 6
        val TEST_RANGE = 1..45
    }
}
