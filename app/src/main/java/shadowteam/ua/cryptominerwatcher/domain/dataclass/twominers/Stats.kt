package shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers

data class Stats (

    val balance: Int,
    val immature: Int,
    val lastShareTime: Int,
    val paid: Int,
    )