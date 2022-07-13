package shadowteam.ua.cryptominerwatcher.data.network.model.twominermodel


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Payment(
    @SerializedName("amount")
    @Expose
    val amount: Int,
    @SerializedName("timestamp")
    @Expose
    val timestamp: Int,
    @SerializedName("tx")
    @Expose
    val tx: String,
    @SerializedName("txFee")
    @Expose
    val txFee: Int
)