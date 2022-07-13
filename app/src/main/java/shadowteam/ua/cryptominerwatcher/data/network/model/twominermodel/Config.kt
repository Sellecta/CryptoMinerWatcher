package shadowteam.ua.cryptominerwatcher.data.network.model.twominermodel


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Config(
    @SerializedName("allowedMaxPayout")
    @Expose
    val allowedMaxPayout: Long,
    @SerializedName("allowedMinPayout")
    @Expose
    val allowedMinPayout: Int,
    @SerializedName("defaultMinPayout")
    @Expose
    val defaultMinPayout: Int,
    @SerializedName("ipHint")
    @Expose
    val ipHint: String,
    @SerializedName("ipWorkerName")
    @Expose
    val ipWorkerName: String,
    @SerializedName("minPayout")
    @Expose
    val minPayout: Int,
    @SerializedName("paymentHubHint")
    @Expose
    val paymentHubHint: String
)