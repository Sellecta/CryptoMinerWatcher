package shadowteam.ua.cryptominerwatcher.data.network.model.twominermodel


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Sumreward(
    @SerializedName("inverval")
    @Expose
    val inverval: Int,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("numreward")
    @Expose
    val numreward: Int,
    @SerializedName("offset")
    @Expose
    val offset: Int,
    @SerializedName("reward")
    @Expose
    val reward: Int
)