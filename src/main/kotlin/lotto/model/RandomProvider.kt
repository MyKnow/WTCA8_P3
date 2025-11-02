package lotto.model

import camp.nextstep.edu.missionutils.Randoms.pickUniqueNumbersInRange

interface RandomProvider {
    fun pick(start: Int, end: Int, count: Int): List<Int>
}

object DefaultRandomProvider : RandomProvider {
    override fun pick(start: Int, end: Int, count: Int): List<Int> {
        return pickUniqueNumbersInRange(start, end, count).sorted()
    }
}
