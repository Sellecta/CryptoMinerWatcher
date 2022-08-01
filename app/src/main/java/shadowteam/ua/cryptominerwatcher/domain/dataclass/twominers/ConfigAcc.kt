package shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers

import java.math.BigDecimal

data class ConfigAcc (

    val ipHint: String,
    val ipWorkerName: String,
    val minPayout: BigDecimal,
    val minPayoutBTC: BigDecimal,
    val minPayoutUSD: Double,
    val paymentHubHint: String,
    val allowedMinPayout:Int
)