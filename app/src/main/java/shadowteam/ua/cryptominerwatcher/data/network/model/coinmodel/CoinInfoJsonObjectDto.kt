package shadowteam.ua.cryptominerwatcher.data.network.model.coinmodel

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinInfoJsonObjectDto(
    @SerializedName("RAW")
    @Expose
    val json: JsonObject? = null
)
