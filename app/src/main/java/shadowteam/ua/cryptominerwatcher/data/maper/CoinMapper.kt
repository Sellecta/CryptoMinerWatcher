package shadowteam.ua.cryptominerwatcher.data.maper

import shadowteam.ua.cryptominerwatcher.data.database.CoinInfoDbModel
import shadowteam.ua.cryptominerwatcher.domain.dataclass.CoinInfo
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class CoinMapper {

    fun mapDbModelToEntity(dbModel: CoinInfoDbModel) = CoinInfo(
        fromSymbol = dbModel.fromSymbol,
        toSymbol = dbModel.toSymbol,
        price = dbModel.price,
        lowDay = dbModel.lowDay,
        highDay = dbModel.highDay,
        lastMarket = dbModel.lastMarket,
        imageUrl = dbModel.imageUrl,
        lastUpdate = convertTimestampToTime(dbModel.lastUpdate)
    )

    private fun convertTimestampToTime(timestamp: Long?):String{
        if (timestamp == null) return ""
        val stamp = Timestamp(timestamp * 1000)
        val date = Date(stamp.time)
        val pattern = "HH:mm:ss"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }
}