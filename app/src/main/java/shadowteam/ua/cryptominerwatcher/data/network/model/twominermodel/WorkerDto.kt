package shadowteam.ua.cryptominerwatcher.data.network.model.twominermodel


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class WorkerDto(

    val hr: Int,
    val hr2: Int,
    val lastBeat: Long,
    val offline: Boolean,
    val rhr: Int,
    val sharesInvalid: Int,
    val sharesStale: Int,
    val sharesValid: Int
)