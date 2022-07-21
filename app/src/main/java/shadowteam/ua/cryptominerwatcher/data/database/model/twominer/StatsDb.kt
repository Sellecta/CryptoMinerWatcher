package shadowteam.ua.cryptominerwatcher.data.database.model.twominer

data class StatsDb (

    val balance: Int,
    val immature: Int,
    val lastShareTime: Int,
    val paid: Int,
)