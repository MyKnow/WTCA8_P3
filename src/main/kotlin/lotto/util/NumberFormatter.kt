package lotto.util

import java.text.DecimalFormat

object NumberFormatter {
    private val formatter = DecimalFormat("#,###")

    fun formatWithComma(number: Int): String = formatter.format(number)
}