package lotto.view

import camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest
import camp.nextstep.edu.missionutils.test.NsTest
import lotto.constant.LottoRank
import lotto.model.Lotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class OutputViewTest : NsTest() {
    @Test
    fun `printLottos 로또 번호를 정해진 포맷에 따라 출력`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        OutputView.printLottos(listOf(lotto))
        assertSimpleTest {
            assertThat(output()).contains("1개를 구매했습니다.", "[1, 2, 3, 4, 5, 6]")
        }
    }

    @Test
    fun `printStatistics 각 등수에 따른 로또 번호를 포맷에 따라 출력`() {
        val rank = LottoRank.FIRST
        val result = sortedMapOf(rank to 1)
        OutputView.printStatistics(result)
        assertSimpleTest {
            assertThat(output()).contains("6개 일치 (2,000,000,000원) - 1개")
        }
    }

    //
    @Test
    fun `printRateOfReturn prints correctly`() {
        OutputView.printRateOfReturn(62.5)
        assertSimpleTest {
            assertThat(output()).contains("총 수익률은 62.5%입니다.")
        }
    }

    override fun runMain() {}
}