package shadowteam.ua.cryptominerwatcher.data.maper

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import shadowteam.ua.cryptominerwatcher.data.database.model.twominer.*
import shadowteam.ua.cryptominerwatcher.data.network.model.twominermodel.*
import shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers.*
import java.math.BigDecimal
import java.math.MathContext
import javax.inject.Inject

class TwoMinerMapper @Inject constructor() {

    fun mapTwoMinerAccDtoToTwoMinerAccDb(
        twoMinerAcc: TwoMinerAccDto,
        wallet: String,
    ): TwoMinerAccDb {
        return TwoMinerAccDb(
            wallet = wallet,
            currentHashrate = twoMinerAcc.currentHashrate,
            hashrateAvg = twoMinerAcc.hashrate,
            reward24h = twoMinerAcc.hreward,
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

    fun mapTwoMinerAccDbToEntity(twoMinerAcc: TwoMinerAccDb, price: String, minPay:Int): TwoMinerAcc {
        return TwoMinerAcc(
            wallet = twoMinerAcc.wallet,
            currentHashrate = mapDataForBigDecimal(twoMinerAcc.currentHashrate,
                NUM_DIVINER_HASH,
                4).toDouble(),
            hashrateAvg = mapDataForBigDecimal(twoMinerAcc.hashrateAvg,
                NUM_DIVINER_HASH,
                4).toDouble(),
            reward24h = mapDataForBigDecimal(twoMinerAcc.reward24h, NUM_DIVINER_PAY, 4).toDouble(),
            paymentsTotal = mapDataForBigDecimal(twoMinerAcc.paymentsTotal,
                NUM_DIVINER_PAY,
                4).toDouble(),
            sharesValid = twoMinerAcc.sharesValid,
            sharesInvalid = twoMinerAcc.sharesInvalid,
            sharesStale = twoMinerAcc.sharesStale,
            stats = mapStatsDbToEntity(twoMinerAcc.stats, price),
            workersTotal = twoMinerAcc.workersTotal,
            workersOnline = twoMinerAcc.workersOnline,
            workersOffline = twoMinerAcc.workersOffline,
            progress = calculateProgress(twoMinerAcc.stats.balance,minPay),
            reward24hUSD = calculateUSD(twoMinerAcc.reward24h, price)
        )
    }

    fun mapJsonObjectWorkerToWorkerDb(workers: JsonObject, wallet: String): List<WorkerDb> {
        val result = mutableListOf<WorkerDb>()
        val workerKeySet = workers.keySet()
        for ((numWorker, workerKey) in workerKeySet.withIndex()) {
            val currentJson = workers.getAsJsonObject(workerKey)
            val worker = Gson().fromJson(currentJson, WorkerDto::class.java)
            result.add(mapWorkerDtoToWorkerDb(worker, numWorker, wallet, workerKey))
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

    private fun mapStatsDbToEntity(stats: StatsDb, price: String): Stats {
        val priceCoin = price.toDouble()
        return Stats(
            balance = mapDataForBigDecimal(stats.balance, NUM_DIVINER_PAY, 2),
            immature = mapDataForBigDecimal(stats.immature, NUM_DIVINER_PAY, 2),
            lastShareTime = stats.lastShareTime,
            paid = mapDataForBigDecimal(stats.paid, NUM_DIVINER_PAY, 2),
            balanceUsd = mapDataForBigDecimal(stats.balance, NUM_DIVINER_PAY, 2).multiply(BigDecimal(
                priceCoin)).toDouble(),
            immatureUSD = mapDataForBigDecimal(stats.immature,
                NUM_DIVINER_PAY,
                2).multiply(BigDecimal(priceCoin)).toDouble(),
            paidUSD = mapDataForBigDecimal(stats.paid, NUM_DIVINER_PAY, 2).multiply(BigDecimal(
                priceCoin)).toDouble(),
        )
    }

    fun mapWorkerDbToEntity(worker: WorkerDb): Worker {
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

    fun mapPaymentDbToEntity(payment: PaymentDb): Payment {
        return Payment(
            amount = payment.amount,
            timestamp = payment.timestamp,
            tx = payment.tx,
            txFee = payment.txFee
        )
    }

    fun mapConfigAccDbToEntity(config: ConfigAccDb): ConfigAcc {
        return ConfigAcc(
            ipHint = config.ipHint,
            ipWorkerName = config.ipWorkerName,
            minPayout = config.minPayout,
            paymentHubHint = config.paymentHubHint,
            allowedMinPayout = config.allowedMinPayout
        )
    }

    private fun statsDtoToStatsDb(stats: StatsDto): StatsDb {
        return StatsDb(
            balance = stats.balance,
            immature = stats.immature,
            lastShareTime = stats.lastShare,
            paid = stats.paid
        )
    }

    private fun mapPaymentDtoToPaymentDb(payment: PaymentDto, wallet: String, id: Int): PaymentDb {
        return PaymentDb(
            id = id,
            wallet = wallet,
            amount = payment.amount,
            timestamp = payment.timestamp,
            tx = payment.tx,
            txFee = payment.txFee
        )
    }

    private fun mapSumRewardDtoToSumRewardDb(
        sumReward: SumRewardDto,
        wallet: String,
        id: Int,
    ): SumRewardDb {
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

    private fun mapDataForBigDecimal(inputData: Int, diviner: Double, roundCount: Int): BigDecimal {
        val res = inputData / diviner
        return BigDecimal(res).round(MathContext(roundCount))
    }

    private fun calculateUSD(inputData: Int, price: String):Double{
        val tempNumb = inputData / NUM_DIVINER_PAY
        val decimalNumb = BigDecimal(tempNumb).multiply(BigDecimal(price))
        return decimalNumb.toDouble()
    }

    private fun calculateProgress(inputData: Int,  minPay:Int): Int{

        val progress = ((inputData/minPay.toDouble())*100).toInt()
        return  if(progress<=100) progress else 100
    }

    fun mapConfigAccDtoToConfigAccDb(config: ConfigDto, walletTwoMiner: String): ConfigAccDb {
        return ConfigAccDb(
            wallet = walletTwoMiner,
            ipHint = config.ipHint,
            ipWorkerName = config.ipWorkerName,
            minPayout = config.minPayout,
            paymentHubHint = config.paymentHubHint,
            allowedMinPayout = config.allowedMinPayout
        )
    }

    fun mapListPaymentDtoToListPaymentDb(
        payments: List<PaymentDto>,
        walletTwoMiner: String,
    ): List<PaymentDb> {
        val result = mutableListOf<PaymentDb>()
        for ((numPayment, payment) in payments.withIndex()) {
            val paymentDb = mapPaymentDtoToPaymentDb(payment, walletTwoMiner, numPayment)
            result.add(paymentDb)
        }
        return result
    }

    fun mapListSumRewardDToListSumRewardDb(
        sumRewards: List<SumRewardDto>,
        walletTwoMiner: String,
    ): List<SumRewardDb> {
        val result = mutableListOf<SumRewardDb>()
        for ((numSumReward, sumReward) in sumRewards.withIndex()) {
            val sumRewardDb = mapSumRewardDtoToSumRewardDb(sumReward, walletTwoMiner, numSumReward)
            result.add(sumRewardDb)
        }
        return result
    }

    companion object {
        private const val NUM_DIVINER_PAY = 1_000_000_000.0
        private const val NUM_DIVINER_HASH = 1_000_000.0
    }


}