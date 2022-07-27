package shadowteam.ua.cryptominerwatcher.domain.domaininterface.twominer

import androidx.lifecycle.LiveData
import shadowteam.ua.cryptominerwatcher.data.network.model.twominermodel.SaveRequest
import shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers.ConfigAcc
import shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers.Payment
import shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers.TwoMinerAcc
import shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers.Worker

interface TwoMinerRepository {

    fun loadData()

    suspend fun getTwoMinerAcc(): LiveData<TwoMinerAcc>

    fun getTwoMinerConfig(): LiveData<ConfigAcc>

    fun getTwoMinerPayment(): LiveData<List<Payment>>

    fun getTwoMinerWorker(): LiveData<List<Worker>>

    suspend fun savePaymentCountUseCase(ip:String, wallet:String, count:Int): LiveData<SaveRequest>

}