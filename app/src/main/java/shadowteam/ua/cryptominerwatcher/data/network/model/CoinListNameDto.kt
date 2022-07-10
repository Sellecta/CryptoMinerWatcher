package shadowteam.ua.cryptominerwatcher.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinListNameDto (
    @SerializedName("Data")
    @Expose
    val name: List<CoinHolderDto>? = null
)