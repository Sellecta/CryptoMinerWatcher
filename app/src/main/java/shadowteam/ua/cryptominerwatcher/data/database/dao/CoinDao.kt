package shadowteam.ua.cryptominerwatcher.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import shadowteam.ua.cryptominerwatcher.data.database.model.coin.CoinInfoDbModel

@Dao
interface CoinDao {

    @Query("SELECT * FROM coin_table")
    fun getAllCoins(): LiveData<List<CoinInfoDbModel>>

    @Query("SELECT * FROM coin_table WHERE fromSymbol == :fSym LIMIT 1")
    fun getInfoAboutCoin(fSym: String): LiveData<CoinInfoDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoinInfoList(coinInfo: List<CoinInfoDbModel>)

    @Query("SELECT Price FROM coin_table WHERE fromSymbol == :coin")
    fun getPriceCoinUSD(coin: String):String
}
