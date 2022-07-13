package shadowteam.ua.cryptominerwatcher.data.network.model.coinmodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinName (
    @SerializedName("Name")
    @Expose
    val name: String? = null
    )

