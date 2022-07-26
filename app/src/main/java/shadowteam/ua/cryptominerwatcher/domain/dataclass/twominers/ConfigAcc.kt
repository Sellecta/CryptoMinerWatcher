package shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers

data class ConfigAcc (

    val ipHint: String,
    val ipWorkerName: String,
    val minPayout: Int,
    val paymentHubHint: String,
    val allowedMinPayout:Int
)