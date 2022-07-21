package shadowteam.ua.cryptominerwatcher.data.database.model.twominer

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "twominer_acc_payment")
data class PaymentDb(
    @PrimaryKey
    val id:Int,
    val wallet:String,
    val amount: Int,
    val timestamp: Int,
    val tx: String,
    val txFee: Int,
)
