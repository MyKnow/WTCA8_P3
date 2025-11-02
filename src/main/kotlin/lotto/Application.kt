package lotto

import lotto.controller.LottoController

fun main() {
    try {
        LottoController().run()
    } catch (e: Exception) {
        println(e.message)
    }
}
