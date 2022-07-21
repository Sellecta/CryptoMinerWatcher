package shadowteam.ua.cryptominerwatcher.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import shadowteam.ua.cryptominerwatcher.data.database.model.twominer.*

@Dao
interface TwoMinerDao {

    @Query("SELECT * FROM twominer_workers")
    fun getAllWorkers():List<WorkerDb>

    @Query("SELECT * FROM twominer_acc")
    fun getAllTwoMinerDbAcc():TwoMinerAccDb

    @Query("SELECT * FROM twominer_acc_config")
    fun getConfigAccDb():ConfigAccDb

    @Query("SELECT * FROM twominer_acc_payment")
    fun getPaymentDb():List<PaymentDb>

    @Query("SELECT * FROM twominer_sumreward")
    fun getAllSumReward():List<SumRewardDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkerDb(worker: WorkerDb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTwoMinerAccDb(acc:TwoMinerAccDb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConfigAccDb(conf:ConfigAccDb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPaymentDb(pay:PaymentDb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSumRewardDB(sum:SumRewardDb)

}