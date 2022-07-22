package shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers

data class TwoMinerAcc(

    val wallet:String,
    val currentHashrate: Int,
    val hashrateAvg: Int,
    val reward24h: Int,
    val paymentsTotal: Int,
    val sharesInvalid: Int,
    val sharesStale: Int,
    val sharesValid: Int,
    val stats: Stats,
    val workersOffline: Int,
    val workersOnline: Int,
    val workersTotal: Int
)