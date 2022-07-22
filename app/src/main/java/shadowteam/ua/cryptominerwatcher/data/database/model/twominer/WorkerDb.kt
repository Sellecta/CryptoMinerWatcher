package shadowteam.ua.cryptominerwatcher.data.database.model.twominer

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "twominer_workers")
data class WorkerDb (

    @PrimaryKey
    val id:Int,
    val wallet:String,
    val name:String,
    val lastShare: Long,
    val hrCurrent: Int,
    val hrAvg: Int,
    val hrMiner: Int,
    val offline: Boolean,
    val sharesInvalid: Int,
    val sharesStale: Int,
    val sharesValid: Int
)
