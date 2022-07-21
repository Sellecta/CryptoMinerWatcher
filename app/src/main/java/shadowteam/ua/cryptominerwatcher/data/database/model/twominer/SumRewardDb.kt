package shadowteam.ua.cryptominerwatcher.data.database.model.twominer

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "twominer_sumreward")
data class SumRewardDb(
    @PrimaryKey
    val id:Int,
    val wallet: String,
    val interval: Int,
    val name: String,
    val numReward: Int,
    val offset: Int,
    val reward: Int
)
