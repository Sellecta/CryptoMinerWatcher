package shadowteam.ua.cryptominerwatcher.data.network.model.raveosmodel


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Worker(
    @SerializedName("coin_id")
    @Expose
    val coinId: Int,
    @SerializedName("count_detected_mpu")
    @Expose
    val countDetectedMpu: Int,
    @SerializedName("count_error_msg")
    @Expose
    val countErrorMsg: Int,
    @SerializedName("count_info_msg")
    @Expose
    val countInfoMsg: Int,
    @SerializedName("count_set_mpu")
    @Expose
    val countSetMpu: Int,
    @SerializedName("count_warning_msg")
    @Expose
    val countWarningMsg: Int,
    @SerializedName("hashrate")
    @Expose
    val hashrate: Int,
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("lan_ip")
    @Expose
    val lanIp: String,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("os_version")
    @Expose
    val osVersion: String,
    @SerializedName("pool_id")
    @Expose
    val poolId: Any,
    @SerializedName("power")
    @Expose
    val power: Int,
    @SerializedName("start_time")
    @Expose
    val startTime: Int,
    @SerializedName("status")
    @Expose
    val status: Int,
    @SerializedName("temp")
    @Expose
    val temp: Int,
    @SerializedName("uptime")
    @Expose
    val uptime: Int,
    @SerializedName("wallet_id")
    @Expose
    val walletId: Int,
    @SerializedName("wan_ip")
    @Expose
    val wanIp: String,
    @SerializedName("worker_type")
    @Expose
    val workerType: Int
)