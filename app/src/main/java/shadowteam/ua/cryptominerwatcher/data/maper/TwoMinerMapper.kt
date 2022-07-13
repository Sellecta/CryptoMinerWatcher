package shadowteam.ua.cryptominerwatcher.data.maper

import com.google.gson.Gson
import com.google.gson.JsonObject
import shadowteam.ua.cryptominerwatcher.data.network.model.twominermodel.WorkerDto

class TwoMinerMapper {
    fun mapJsonObjectWorkerToWorkerDto(workers: JsonObject) : Map<String, WorkerDto> {
        val result = mutableMapOf<String, WorkerDto>()
        mapOf<String, WorkerDto>()
        val workerKeySet = workers.keySet()
        for(workerKey in workerKeySet){
            val currencyJson = workers.getAsJsonObject(workerKey)
            val worker = Gson().fromJson(currencyJson, WorkerDto::class.java)
            result[workerKey] = worker
        }
        return result
    }
}