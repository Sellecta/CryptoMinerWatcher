package shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers



data class SumReward(

    val interval: Int,
    val name: String,
    val numReward: Int,
    val offset: Int,
    val reward: Int
)
