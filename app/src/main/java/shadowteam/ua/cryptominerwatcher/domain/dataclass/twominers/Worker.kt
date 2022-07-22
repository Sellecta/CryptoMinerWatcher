package shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers

data class Worker (

    val name: String,
    val lastShare: Long,
    val hrCurrent: Int,
    val hrAvg: Int,
    val hrMiner: Int,
    val offline: Boolean,
    val sharesInvalid: Int,
    val sharesStale: Int,
    val sharesValid: Int
)
