package lotto

import lotto.controller.LottoController
import java.lang.Exception

fun main() {
    try {
        LottoController.run()
    } catch (e: Exception) {
        print(e.message)
    }
}
