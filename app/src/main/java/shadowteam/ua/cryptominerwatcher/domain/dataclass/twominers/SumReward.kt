package shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers

import java.math.BigDecimal


data class SumReward(

    val interval: Int,
    val name: String,
    val rewardETH: BigDecimal,
    val rewardUSD: Double,
    val rewardBTC: BigDecimal
)
