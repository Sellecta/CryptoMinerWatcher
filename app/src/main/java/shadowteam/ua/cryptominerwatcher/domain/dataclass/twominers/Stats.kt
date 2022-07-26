package shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers

import java.math.BigDecimal

data class Stats (

    val balance: BigDecimal,
    val balanceUsd: Double,
    val immature: BigDecimal,
    val immatureUSD: Double,
    val lastShareTime: Int,
    val paid: BigDecimal,
    val paidUSD:Double
    )