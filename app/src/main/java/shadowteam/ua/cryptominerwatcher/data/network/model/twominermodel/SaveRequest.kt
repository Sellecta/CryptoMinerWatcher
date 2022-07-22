package shadowteam.ua.cryptominerwatcher.data.network.model.twominermodel


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class SaveRequest(
    @SerializedName("apiVersion")
    @Expose
    val apiVersion: Int?,
    @SerializedName("config")
    @Expose
    val config: ConfigDto?
)