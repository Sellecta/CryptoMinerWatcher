package shadowteam.ua.cryptominerwatcher.domain.domaininterface.twominer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import shadowteam.ua.cryptominerwatcher.data.network.model.twominermodel.SaveRequest
import shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers.*

interface TwoMinerRepository {

    fun loadData()

    suspend fun getTwoMinerAcc(): LiveData<TwoMinerAcc>

    fun getTwoMinerConfig(): LiveData<ConfigAcc>

    fun getTwoMinerPayment(): LiveData<List<Payment>>

    fun getTwoMinerWorker(): LiveData<List<Worker>>

    fun getTwoMinerSumReward():LiveData<List<SumReward>>

    suspend fun savePaymentCountUseCase(ip:String, wallet:String, count:Int):Int

}