package shadowteam.ua.cryptominerwatcher.data.network.model.twominermodel


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Stats(
    @SerializedName("balance")
    @Expose
    val balance: Int,
    @SerializedName("immature")
    @Expose
    val immature: Int,
    @SerializedName("lastShare")
    @Expose
    val lastShare: Int,
    @SerializedName("paid")
    @Expose
    val paid: Int,
    @SerializedName("pending")
    @Expose
    val pending: Int
)