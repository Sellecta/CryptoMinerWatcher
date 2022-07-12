package shadowteam.ua.cryptominerwatcher.domain.dataclass

data class CoinInfo(
    val fromSymbol: String,
    val toSymbol: String?,
    val price: String?,
    val lowDay: String?,
    val highDay: String?,
    val lastMarket: String?,
    val imageUrl: String?,
    val lastUpdate: String,
    val changePCT24Hour: String?
)