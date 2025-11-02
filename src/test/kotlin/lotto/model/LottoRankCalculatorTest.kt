package lotto.model

import lotto.constant.ErrorType
import lotto.constant.LottoRank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@Suppress("NonAsciiCharacters")
class LottoRankCalculatorTest {
    @Test
    fun `1등 반환`() {
        val rank = LottoRankCalculator.getRank(matchCount = 6, bonusMatched = false)
        assertThat(rank).isEqualTo(LottoRank.FIRST)
    }

    @Test
    fun `2등 반환 - 5개 맞고 보너스 포함`() {
        val rank = LottoRankCalculator.getRank(matchCount = 5, bonusMatched = true)
        assertThat(rank).isEqualTo(LottoRank.SECOND)
    }

    @Test
    fun `3등 반환 - 5개 맞고 보너스 없음`() {
        val rank = LottoRankCalculator.getRank(matchCount = 5, bonusMatched = false)
        assertThat(rank).isEqualTo(LottoRank.THIRD)
    }

    @Test
    fun `4등 반환 - 4개 맞음`() {
        val rank = LottoRankCalculator.getRank(matchCount = 4, bonusMatched = false)
        assertThat(rank).isEqualTo(LottoRank.FOURTH)
    }

    @Test
    fun `5등 반환 - 3개 맞음`() {
        val rank = LottoRankCalculator.getRank(matchCount = 3, bonusMatched = false)
        assertThat(rank).isEqualTo(LottoRank.FIFTH)
    }

    @Test
    fun `NONE 반환 - 2개 이하`() {
        val rank1 = LottoRankCalculator.getRank(matchCount = 2, bonusMatched = false)
        val rank2 = LottoRankCalculator.getRank(matchCount = 0, bonusMatched = true)
        assertThat(rank1).isEqualTo(LottoRank.NONE)
        assertThat(rank2).isEqualTo(LottoRank.NONE)
    }

    @Test
    fun `에러 반환 - 범위 상한 초과`() {
        val result = assertThrows<IllegalArgumentException> {
            LottoRankCalculator.getRank(matchCount = 7, bonusMatched = false)
        }
        assertThat(result.message).isEqualTo(ErrorType.INVALID_MATCH_COUNT.message)
    }

    @Test
    fun `에러 반환 - 범위 하한 미만`() {
        val result = assertThrows<IllegalArgumentException> {
            LottoRankCalculator.getRank(matchCount = -1, bonusMatched = false)
        }
        assertThat(result.message).isEqualTo(ErrorType.INVALID_MATCH_COUNT.message)
    }
}
