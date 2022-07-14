package shadowteam.ua.cryptominerwatcher.data.network.model.raveosmodel


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class RaveOsDto(
    @SerializedName("coins")
    @Expose
    val coins: List<Coin>,
    @SerializedName("pools")
    @Expose
    val pools: Any,
    @SerializedName("wallets")
    @Expose
    val wallets: List<Wallet>,
    @SerializedName("workers")
    @Expose
    val workers: List<Worker>
)