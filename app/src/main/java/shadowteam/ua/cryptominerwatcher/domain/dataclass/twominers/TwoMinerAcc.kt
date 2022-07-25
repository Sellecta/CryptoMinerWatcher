package shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers

data class TwoMinerAcc(

    val wallet:String,
    val currentHashrate: Double,
    val hashrateAvg: Double,
    val reward24h: Double,
    val paymentsTotal: Double,
    val sharesInvalid: Int,
    val sharesStale: Int,
    val sharesValid: Int,
    val stats: Stats,
    val workersOffline: Int,
    val workersOnline: Int,
    val workersTotal: Int
)