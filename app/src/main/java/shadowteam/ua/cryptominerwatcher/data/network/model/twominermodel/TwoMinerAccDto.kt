package shadowteam.ua.cryptominerwatcher.data.network.model.twominermodel


import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class TwoMinerAccDto(
    @SerializedName("apiVersion")
    @Expose
    val apiVersion: Int,
    @SerializedName("config")
    @Expose
    val config: ConfigDto,
    @SerializedName("currentHashrate")
    @Expose
    val currentHashrate: Int,
    @SerializedName("currentLuck")
    @Expose
    val currentLuck: String,
    @SerializedName("hashrate")
    @Expose
    val hashrate: Int,
    @SerializedName("24hnumreward")
    @Expose
    val hnumreward: Int,
    @SerializedName("24hreward")
    @Expose
    val hreward: Int,
    @SerializedName("pageSize")
    @Expose
    val pageSize: Int,
    @SerializedName("payments")
    @Expose
    val payments: List<PaymentDto>,
    @SerializedName("paymentsTotal")
    @Expose
    val paymentsTotal: Int,
    @SerializedName("rewards")
    @Expose
    val rewards: List<RewardDto>,
    @SerializedName("roundShares")
    @Expose
    val roundShares: Int,
    @SerializedName("sharesInvalid")
    @Expose
    val sharesInvalid: Int,
    @SerializedName("sharesStale")
    @Expose
    val sharesStale: Int,
    @SerializedName("sharesValid")
    @Expose
    val sharesValid: Int,
    @SerializedName("stats")
    @Expose
    val stats: StatsDto,
    @SerializedName("sumrewards")
    @Expose
    val sumrewards: List<SumRewardDto>,
    @SerializedName("updatedAt")
    @Expose
    val updatedAt: Long,
    @SerializedName("workers")
    @Expose
    val workers: JsonObject,
    @SerializedName("workersOffline")
    @Expose
    val workersOffline: Int,
    @SerializedName("workersOnline")
    @Expose
    val workersOnline: Int,
    @SerializedName("workersTotal")
    @Expose
    val workersTotal: Int
)