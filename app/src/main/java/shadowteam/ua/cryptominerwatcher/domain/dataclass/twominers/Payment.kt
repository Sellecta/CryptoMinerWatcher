package shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers


data class Payment(

    val amount: Int,
    val timestamp: Int,
    val tx: String,
    val txFee: Int,
)
