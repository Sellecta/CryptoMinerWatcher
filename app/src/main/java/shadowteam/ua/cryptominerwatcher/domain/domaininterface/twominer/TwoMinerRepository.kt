package shadowteam.ua.cryptominerwatcher.domain.domaininterface.twominer

import androidx.lifecycle.LiveData
import shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers.ConfigAcc
import shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers.Payment
import shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers.Worker

interface TwoMinerRepository {

    fun loadData()

    fun getTwoMinerAcc()

    fun getTwoMinerConfig(): LiveData<ConfigAcc>

    fun getTwoMinerPayment(): LiveData<List<Payment>>

    fun getTwoMinerWorker(): LiveData<Map<String, Worker>>

}