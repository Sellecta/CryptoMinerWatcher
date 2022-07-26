package shadowteam.ua.cryptominerwatcher.data.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import retrofit2.HttpException
import shadowteam.ua.cryptominerwatcher.data.database.dao.CoinDao
import shadowteam.ua.cryptominerwatcher.data.database.dao.TwoMinerDao
import shadowteam.ua.cryptominerwatcher.data.maper.TwoMinerMapper
import shadowteam.ua.cryptominerwatcher.data.network.ApiService
import shadowteam.ua.cryptominerwatcher.data.network.model.twominermodel.BodyCountPayment
import shadowteam.ua.cryptominerwatcher.data.network.model.twominermodel.SaveRequest
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
    private val application: Application,
    private val apiService: ApiService,
    private val coinDao: CoinDao
): TwoMinerRepository {

    override fun loadData() {
        val worker = WorkManager.getInstance(application)
        worker.enqueueUniqueWork(
            TwoMinerDataWorker.Name,
            ExistingWorkPolicy.REPLACE,
            TwoMinerDataWorker.makeRequest("bc1q99ymugekddfemgaw4nzptv45xdvz7k797d3qpc")
        )
    }

    override fun getTwoMinerAcc(): TwoMinerAcc{
//        val priceCoin = coinDao.getPriceCoinUSD(ETH)
//        val minPay = twoMinerDao.getMinAllowedPay()
        val it = twoMinerDao.getAllTwoMinerDbAcc()

//        return Transformations.map(twoMinerDao.getAllTwoMinerDbAcc()){
//            twoMinerMapper.mapTwoMinerAccDbToEntity(it, priceCoin, minPay)
//        }
        return  twoMinerMapper.mapTwoMinerAccDbToEntity( twoMinerDao.getAllTwoMinerDbAcc().value!!, "5555555",555555555)
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

    override suspend fun savePaymentCountUseCase(ip: String, wallet: String, count: Int
    ): LiveData<SaveRequest> {
        return try{
            val body = BodyCountPayment(ip)
            val req =  apiService.posSaveMinimumPay(wallet, body)
            MutableLiveData<SaveRequest>().apply {
                value = req
            }
        }catch (e: HttpException){
            Log.e("LOG_ERROR", e.code().toString())
            MutableLiveData<SaveRequest>().apply {
                value = SaveRequest(e.code(),null)
            }
        }
    }
    companion object{

        private const val ETH = "ETH"
    }
}