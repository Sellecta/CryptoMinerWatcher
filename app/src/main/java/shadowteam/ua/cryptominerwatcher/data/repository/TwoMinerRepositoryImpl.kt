package shadowteam.ua.cryptominerwatcher.data.repository

import androidx.lifecycle.LiveData
import shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers.ConfigAcc
import shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers.Payment
import shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers.Worker
import shadowteam.ua.cryptominerwatcher.domain.domaininterface.twominer.TwoMinerRepository

class TwoMinerRepositoryImpl : TwoMinerRepository {

    override fun loadData() {
        TODO("Not yet implemented")
    }

    override fun getTwoMinerAcc() {
        TODO("Not yet implemented")
    }

    override fun getTwoMinerConfig(): LiveData<ConfigAcc> {
        TODO("Not yet implemented")
    }

    override fun getTwoMinerPayment(): LiveData<List<Payment>> {
        TODO("Not yet implemented")
    }

    override fun getTwoMinerWorker(): LiveData<Map<String, Worker>> {
        TODO("Not yet implemented")
    }
}