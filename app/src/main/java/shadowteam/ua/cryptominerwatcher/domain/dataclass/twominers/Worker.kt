package shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers

data class Worker (

    val name: String,
    val lastShare: String,
    val hrCurrent: Double,
    val hrAvg: Double,
    val hrMiner: Double,
    val offline: Boolean,
    val sharesInvalid: Int,
    val sharesStale: Int,
    val sharesValid: Int
)
