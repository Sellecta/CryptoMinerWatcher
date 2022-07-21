package shadowteam.ua.cryptominerwatcher.data.database.model.twominer

import androidx.room.Entity
import androidx.room.PrimaryKey
import shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers.Stats

@Entity(tableName = "twominer_acc")
data class TwoMinerAccDb(
    @PrimaryKey
    val wallet:String,
    val currentHashrate: Int,
    val hashrateAvg: Int,
    val reward24h: Int,
    val paymentsTotal: Int,
    val sharesInvalid: Int,
    val sharesStale: Int,
    val sharesValid: Int,
    val stats: StatsDb,
    val workersOffline: Int,
    val workersOnline: Int,
    val workersTotal: Int
)