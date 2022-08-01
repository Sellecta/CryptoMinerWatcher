package shadowteam.ua.cryptominerwatcher.data.database.model.twominer

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "twominer_acc_config")
data class ConfigAccDb (
    @PrimaryKey
    val wallet:String,
    val ipHint: String,
    val ipWorkerName: String,
    val minPayout: Int,
    val minPayoutUSD:Double,
    val minPayoutBTC: Double,
    val allowedMinPayout:Int,
    val paymentHubHint: String
)