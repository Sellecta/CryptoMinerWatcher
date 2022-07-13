package shadowteam.ua.cryptominerwatcher.data.network.model.coinmodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinHolderDto (
    @SerializedName("CoinInfo")
    @Expose
    val coinName: CoinName? = null
    )
