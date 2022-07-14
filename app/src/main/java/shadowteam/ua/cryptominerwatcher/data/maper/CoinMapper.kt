package shadowteam.ua.cryptominerwatcher.data.maper

import com.google.gson.Gson
import shadowteam.ua.cryptominerwatcher.data.database.CoinInfoDbModel
import shadowteam.ua.cryptominerwatcher.data.network.model.coinmodel.CoinInfoDto
import shadowteam.ua.cryptominerwatcher.data.network.model.coinmodel.CoinInfoJsonObjectDto
import shadowteam.ua.cryptominerwatcher.data.network.model.coinmodel.CoinListNameDto
import shadowteam.ua.cryptominerwatcher.domain.dataclass.coin.CoinInfo
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CoinMapper @Inject constructor() {

    fun mapNamesListToString(nameListDto: CoinListNameDto):String{
        return nameListDto.name?.map { it.coinName?.name }?.joinToString(",") ?:""
    }

    fun mapDbModelToEntity(dbModel: CoinInfoDbModel) = CoinInfo(
        fromSymbol = dbModel.fromSymbol,
        toSymbol = dbModel.toSymbol,
        price = dbModel.price,
        lowDay = dbModel.lowDay,
        highDay = dbModel.highDay,
        lastMarket = dbModel.lastMarket,
        imageUrl = dbModel.imageUrl,
        lastUpdate = convertTimestampToTime(dbModel.lastUpdate),
        changePCT24Hour = dbModel.changePCT24Hour
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

    fun mapJsonCoinInfoToListCoinInfo(jsonContainer: CoinInfoJsonObjectDto): List<CoinInfoDto> {
        val result = mutableListOf<CoinInfoDto>()
        val jsonObject = jsonContainer.json ?: return result
        val coinKeySet = jsonObject.keySet()
        for(coinKey in coinKeySet){
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for(currencyKey in currencyKeySet){
                val coin = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfoDto::class.java
                )
                result.add(coin)
            }
        }
        return result
    }

    fun mapDtoToDbModel(coin: CoinInfoDto) = CoinInfoDbModel(
        fromSymbol = coin.fromSymbol,
        toSymbol = coin.toSymbol,
        price = coin.price,
        lowDay = coin.lowDay,
        highDay = coin.highDay,
        lastMarket = coin.lastMarket,
        imageUrl = BASE_IMAGE_URL + coin.imageUrl,
        lastUpdate = coin.lastUpdate,
        changePCT24Hour = coin.changePCT24Hour
    )

    companion object{
        private const val BASE_IMAGE_URL = "https://cryptocompare.com"
    }
}