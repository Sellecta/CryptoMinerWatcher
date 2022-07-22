package shadowteam.ua.cryptominerwatcher.data.maper

import com.google.gson.Gson
import com.google.gson.JsonObject
import shadowteam.ua.cryptominerwatcher.data.database.model.twominer.*
import shadowteam.ua.cryptominerwatcher.data.network.model.twominermodel.*
import shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers.*
import javax.inject.Inject

class TwoMinerMapper @Inject constructor() {

    fun mapTwoMinerAccDtoToTwoMinerAccDb(twoMinerAcc: TwoMinerAccDto, wallet:String): TwoMinerAccDb {
        return TwoMinerAccDb(
            wallet = wallet,
            currentHashrate = twoMinerAcc.currentHashrate,
            hashrateAvg = twoMinerAcc.hashrate,
            reward24h = twoMinerAcc.hnumreward,
            paymentsTotal = twoMinerAcc.paymentsTotal,
            sharesInvalid = twoMinerAcc.sharesInvalid,
            sharesStale = twoMinerAcc.sharesStale,
            sharesValid = twoMinerAcc.sharesValid,
            stats = statsDtoToStatsDb(twoMinerAcc.stats),
            workersOffline = twoMinerAcc.workersOffline,
            workersOnline = twoMinerAcc.workersOnline,
            workersTotal = twoMinerAcc.workersTotal
        )
    }

    fun mapTwoMinerAccDbToEntity(twoMinerAcc: TwoMinerAccDb):TwoMinerAcc{
        return TwoMinerAcc(
            wallet = twoMinerAcc.wallet,
            currentHashrate = twoMinerAcc.currentHashrate,
            hashrateAvg = twoMinerAcc.hashrateAvg,
            reward24h = twoMinerAcc.reward24h,
            paymentsTotal = twoMinerAcc.paymentsTotal,
            sharesValid = twoMinerAcc.sharesValid,
            sharesInvalid = twoMinerAcc.sharesInvalid,
            sharesStale = twoMinerAcc.sharesStale,
            stats = statDbToEntity(twoMinerAcc.stats),
            workersTotal = twoMinerAcc.workersTotal,
            workersOnline = twoMinerAcc.workersOnline,
            workersOffline = twoMinerAcc.workersOffline
        )
    }

    fun mapJsonObjectWorkerToWorkerDb(workers: JsonObject,wallet: String) : List<WorkerDb> {
        val result = mutableListOf<WorkerDb>()
        val workerKeySet = workers.keySet()
        for((numWorker, workerKey) in workerKeySet.withIndex()){
            val currentJson = workers.getAsJsonObject(workerKey)
            val worker = Gson().fromJson(currentJson, WorkerDto::class.java)
            result.add(mapWorkerDtoToWorkerDb(worker,numWorker,wallet,workerKey))
        }
        return result
    }

    private fun mapWorkerDtoToWorkerDb(
        workerDto: WorkerDto,
        numWorker: Int,
        wallet: String,
        nameWorker: String,
    ): WorkerDb {
        return WorkerDb(
            id = numWorker,
            wallet = wallet,
            name = nameWorker,
            lastShare = workerDto.lastBeat,
            hrCurrent = workerDto.hr,
            hrAvg = workerDto.hr2,
            hrMiner = workerDto.rhr,
            offline = workerDto.offline,
            sharesValid = workerDto.sharesValid,
            sharesInvalid = workerDto.sharesInvalid,
            sharesStale = workerDto.sharesStale
        )
    }

    fun mapStatsDbToEntity(stats: StatsDb) : Stats{
        return Stats(
            balance = stats.balance,
            immature = stats.immature,
            lastShareTime = stats.lastShareTime,
            paid = stats.paid
        )
    }

    fun mapWorkerDbToEntity(worker: WorkerDb) : Worker {
        return Worker(
            name = worker.name,
            hrAvg = worker.hrAvg,
            hrCurrent = worker.hrCurrent,
            hrMiner = worker.hrMiner,
            lastShare = worker.lastShare,
            offline = worker.offline,
            sharesStale = worker.sharesStale,
            sharesInvalid = worker.sharesInvalid,
            sharesValid = worker.sharesValid
        )
    }

    fun mapPaymentDbToEntity(payment: PaymentDb): Payment{
        return Payment(
            amount = payment.amount,
            timestamp = payment.timestamp,
            tx = payment.tx,
            txFee = payment.txFee
        )
    }

    fun mapConfigAccDbToEntity(config: ConfigAccDb): ConfigAcc{
        return ConfigAcc(
            ipHint = config.ipHint,
            ipWorkerName = config.ipWorkerName,
            minPayout = config.minPayout,
            paymentHubHint = config.paymentHubHint
        )
    }

    private fun statsDtoToStatsDb(stats:StatsDto):StatsDb{
        return StatsDb(
            balance = stats.balance,
            immature = stats.immature,
            lastShareTime = stats.lastShare,
            paid = stats.paid
        )
    }

    private fun statDbToEntity(stats: StatsDb):Stats{
        return Stats(
            balance = stats.balance,
            paid = stats.paid,
            immature = stats.immature,
            lastShareTime = stats.lastShareTime
        )

    }

    private fun mapPaymentDtoToPaymentDb(payment: PaymentDto, wallet:String, id:Int): PaymentDb{
        return PaymentDb(
            id = id,
            wallet = wallet,
            amount = payment.amount,
            timestamp = payment.timestamp,
            tx = payment.tx,
            txFee = payment.txFee
        )
    }

    private fun mapSumRewardDtoToSumRewardDb(sumReward: SumRewardDto, wallet: String, id: Int):SumRewardDb{
        return SumRewardDb(
            id = id,
            wallet = wallet,
            interval = sumReward.inverval,
            name = sumReward.name,
            numReward = sumReward.numreward,
            offset = sumReward.offset,
            reward = sumReward.reward
        )
    }

    fun mapConfigAccDtoToConfigAccDb(config: ConfigDto, walletTwoMiner: String): ConfigAccDb {
        return ConfigAccDb(
            wallet = walletTwoMiner,
            ipHint = config.ipHint,
            ipWorkerName = config.ipWorkerName,
            minPayout = config.minPayout,
            paymentHubHint = config.paymentHubHint
        )
    }

    fun mapListPaymentDtoToListPaymentDb(
        payments: List<PaymentDto>,
        walletTwoMiner: String,
    ): List<PaymentDb> {
        val result = mutableListOf<PaymentDb>()
        for((numPayment, payment)  in payments.withIndex()){
            val paymentDb = mapPaymentDtoToPaymentDb(payment,walletTwoMiner,numPayment)
            result.add(paymentDb)
        }
        return result
    }

    fun mapListSumRewardDToListSumRewardDb(
        sumRewards: List<SumRewardDto>,
        walletTwoMiner: String,
    ): List<SumRewardDb> {
        val result = mutableListOf<SumRewardDb>()
        for ((numSumReward, sumReward) in sumRewards.withIndex()){
            val sumRewardDb = mapSumRewardDtoToSumRewardDb(sumReward,walletTwoMiner,numSumReward)
            result.add(sumRewardDb)
        }
        return result
    }


}