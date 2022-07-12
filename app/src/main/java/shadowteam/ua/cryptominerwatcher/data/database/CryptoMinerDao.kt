package shadowteam.ua.cryptominerwatcher.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CryptoMinerDao {

    @Query("SELECT * FROM coin_table ORDER BY lastUpdate DESC")
    fun getAllCoins(): LiveData<List<CoinInfoDbModel>>

    @Query("SELECT * FROM coin_table WHERE fromSymbol == :fSym LIMIT 1")
    fun getInfoAboutCoin(fSym: String): LiveData<CoinInfoDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoinInfoList(coinInfo: List<CoinInfoDbModel>)
}
