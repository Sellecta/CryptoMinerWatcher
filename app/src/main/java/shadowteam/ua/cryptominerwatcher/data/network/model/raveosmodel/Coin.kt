package shadowteam.ua.cryptominerwatcher.data.network.model.raveosmodel


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Coin(
    @SerializedName("algo")
    @Expose
    val algo: String,
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("short_name")
    @Expose
    val shortName: String
)