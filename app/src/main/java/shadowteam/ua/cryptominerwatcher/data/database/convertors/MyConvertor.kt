package shadowteam.ua.cryptominerwatcher.data.database.convertors

import androidx.room.TypeConverter
import com.google.gson.Gson
import shadowteam.ua.cryptominerwatcher.data.database.model.twominer.StatsDb
import shadowteam.ua.cryptominerwatcher.domain.dataclass.twominers.Worker

class MyConvertor {

    @TypeConverter
    fun listWorkerToJsonStr( myList :List<Worker>):String?{
        return Gson().toJson(myList)
    }
    @TypeConverter
    fun jsonStrToListWorker(str:String?): List<Worker>? {
        return str?.let { Gson().fromJson(str, Array<Worker>::class.java).asList() }
    }
    @TypeConverter
    fun statDbToJsonStr(stats: StatsDb):String?{
        return Gson().toJson(stats)
    }
    @TypeConverter
    fun jsonStrToStatsDb(str:String?): StatsDb?{
        return str?.let { Gson().fromJson(str, StatsDb::class.java) }
    }
}