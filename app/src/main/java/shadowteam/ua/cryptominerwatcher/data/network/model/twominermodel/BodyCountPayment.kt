package shadowteam.ua.cryptominerwatcher.data.network.model.twominermodel

import com.google.gson.annotations.SerializedName

data class BodyCountPayment (

    @SerializedName("ip")
    var ip: String,

    @SerializedName("key")
    var key: String = "minPayout",

    @SerializedName("value")
    var count: String = "5 000 000",
)