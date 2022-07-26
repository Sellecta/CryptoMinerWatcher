package shadowteam.ua.cryptominerwatcher.data.network.model.twominermodel


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class WorkerDto(

    var hr: Int,
    var hr2: Int,
    var lastBeat: Long,
    var offline: Boolean,
    var rhr: Int,
    var sharesInvalid: Int,
    var sharesStale: Int,
    var sharesValid: Int
)