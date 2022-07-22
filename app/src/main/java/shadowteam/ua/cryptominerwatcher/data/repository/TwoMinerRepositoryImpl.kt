package shadowteam.ua.cryptominerwatcher.data.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import shadowteam.ua.cryptominerwatcher.data.database.dao.TwoMinerDao
import shadowteam.ua.cryptominerwatcher.data.maper.TwoMinerMapper
import shadowteam.ua.cryptominerwatcher.data.worker.twominer.TwoMinerDataWorker
import shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers.ConfigAcc
import shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers.Payment
import shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers.TwoMinerAcc
import shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers.Worker
import shadowteam.ua.cryptominerwatcher.domain.domaininterface.twominer.TwoMinerRepository
import shadowteam.ua.cryptominerwatcher.domain.usecase.twominer.GetTwoMinerAccUseCase
import javax.inject.Inject

class TwoMinerRepositoryImpl @Inject constructor(
    private val twoMinerDao: TwoMinerDao,
    private val twoMinerMapper: TwoMinerMapper,
    private val application: Application
): TwoMinerRepository {

    override fun loadData() {
        val worker = WorkManager.getInstance(application)
        worker.enqueueUniqueWork(
            TwoMinerDataWorker.Name,
            ExistingWorkPolicy.REPLACE,
            TwoMinerDataWorker.makeRequest("bc1q99ymugekddfemgaw4nzptv45xdvz7k797d3qpc")
        )
    }

    override fun getTwoMinerAcc(): LiveData<TwoMinerAcc> {
        return Transformations.map(twoMinerDao.getAllTwoMinerDbAcc()){
            twoMinerMapper.mapTwoMinerAccDbToEntity(it)
        }
    }

    override fun getTwoMinerConfig(): LiveData<ConfigAcc> {
       return Transformations.map(twoMinerDao.getConfigAccDb()){
           twoMinerMapper.mapConfigAccDbToEntity(it)
       }
    }

    override fun getTwoMinerPayment(): LiveData<List<Payment>> {
        return Transformations.map(twoMinerDao.getPaymentDb()){ listPayment ->
            listPayment.map {
                twoMinerMapper.mapPaymentDbToEntity(it)
            }
        }
    }

    override fun getTwoMinerWorker(): LiveData<List<Worker>> {
        return Transformations.map(twoMinerDao.getAllWorkers()){ listWorker ->
            listWorker.map {
                twoMinerMapper.mapWorkerDbToEntity(it)
            }
        }
    }
}