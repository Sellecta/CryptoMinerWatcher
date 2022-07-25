package shadowteam.ua.cryptominerwatcher.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import shadowteam.ua.cryptominerwatcher.data.database.model.twominer.*

@Dao
interface TwoMinerDao {

    @Query("SELECT * FROM twominer_workers")
    fun getAllWorkers():LiveData<List<WorkerDb>>

    @Query("SELECT * FROM twominer_acc")
     fun getAllTwoMinerDbAcc():TwoMinerAccDb

    @Query("SELECT * FROM twominer_acc_config")
    fun getConfigAccDb():LiveData<ConfigAccDb>

    @Query("SELECT * FROM twominer_acc_payment")
    fun getPaymentDb():LiveData<List<PaymentDb>>

    @Query("SELECT * FROM twominer_sumreward")
    fun getAllSumReward():List<SumRewardDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkerDb(worker: WorkerDb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListWorkerDb(listWorker: List<WorkerDb>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPaymentDb(pay:PaymentDb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListPaymentDb(listPay:List<PaymentDb>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSumRewardDB(sum:SumRewardDb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListSumRewardDB(sumList: List<SumRewardDb>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTwoMinerAccDb(acc:TwoMinerAccDb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConfigAccDb(conf:ConfigAccDb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDataTwoMiner(
        listWorker: List<WorkerDb>,
        listPay: List<PaymentDb>,
        sumList: List<SumRewardDb>,
        acc: TwoMinerAccDb,
        conf: ConfigAccDb,
    )

    @Query("DELETE FROM twominer_workers WHERE id =:workerId")
    suspend fun delWorkerDb(workerId:Int)

}