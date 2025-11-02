package lotto.constant

enum class LottoRule(val value: Int) {
    START_NUMBER(1),
    END_NUMBER(45),
    SIZE(6),
    PRICE(1000),
}

enum class LottoRank(
    val matchCount: Int,
    val reward: Int,
    val hasBonus: Boolean = false,
) {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000, hasBonus = true),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    NONE(0, 0),
}