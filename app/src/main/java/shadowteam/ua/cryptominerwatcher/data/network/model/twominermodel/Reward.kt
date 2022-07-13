package shadowteam.ua.cryptominerwatcher.data.network.model.twominermodel


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Reward(
    @SerializedName("blockheight")
    @Expose
    val blockheight: Int,
    @SerializedName("immature")
    @Expose
    val immature: Boolean,
    @SerializedName("orphan")
    @Expose
    val orphan: Boolean,
    @SerializedName("percent")
    @Expose
    val percent: Double,
    @SerializedName("reward")
    @Expose
    val reward: Int,
    @SerializedName("timestamp")
    @Expose
    val timestamp: Int,
    @SerializedName("uncle")
    @Expose
    val uncle: Boolean
)