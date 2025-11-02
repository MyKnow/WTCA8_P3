package lotto.util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class NumberFormatterTest {
    @Test
    fun `정수를 천단위 콤마 문자열로 변환한다`() {
        val number = 5000
        val formatted = NumberFormatter.formatWithComma(number)
        assertThat("5,000").isEqualTo(formatted)
    }

    @Test
    fun `큰 정수를 천단위 콤마 문자열로 변환한다`() {
        val number = 2000000000
        val formatted = NumberFormatter.formatWithComma(number)
        assertThat("2,000,000,000").isEqualTo(formatted)
    }

    @Test
    fun `0을 천단위 콤마 문자열로 변환하면 0이 나온다`() {
        val number = 0
        val formatted = NumberFormatter.formatWithComma(number)
        assertThat("0").isEqualTo(formatted)
    }
}
