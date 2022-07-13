package shadowteam.ua.cryptominerwatcher.data.network.model.twominermodel


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class WorkerDto(
    @SerializedName("hr")
    @Expose
    val hr: Int,
    @SerializedName("hr2")
    @Expose
    val hr2: Int,
    @SerializedName("lastBeat")
    @Expose
    val lastBeat: Int,
    @SerializedName("offline")
    @Expose
    val offline: Boolean,
    @SerializedName("rhr")
    @Expose
    val rhr: Int,
    @SerializedName("sharesInvalid")
    @Expose
    val sharesInvalid: Int,
    @SerializedName("sharesStale")
    @Expose
    val sharesStale: Int,
    @SerializedName("sharesValid")
    @Expose
    val sharesValid: Int
)