package shadowteam.ua.cryptominerwatcher.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import shadowteam.ua.cryptominerwatcher.data.database.convertors.MyConvertor
import shadowteam.ua.cryptominerwatcher.data.database.model.coin.CoinInfoDbModel
import shadowteam.ua.cryptominerwatcher.data.database.dao.CoinDao
import shadowteam.ua.cryptominerwatcher.data.database.dao.TwoMinerDao
import shadowteam.ua.cryptominerwatcher.data.database.model.twominer.*

@Database(entities = [CoinInfoDbModel::class,
    WorkerDb::class,
    TwoMinerAccDb::class,
    ConfigAccDb::class,
    PaymentDb::class,
    SumRewardDb::class], version = 7, exportSchema = false)
@TypeConverters(MyConvertor::class)
abstract class AppDatabase : RoomDatabase() {

    companion object{
        private const val DATABASE_NAME = "crypto_miner_watcher_db"
        private var db: AppDatabase? = null
        private val LOCK = Any()

        fun getInstance(application: Application): AppDatabase{
            synchronized(LOCK){
                db?.let { return it }
                val instance = Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).fallbackToDestructiveMigration().build()
                db = instance
                return instance
            }
        }
    }

    abstract fun coinDao(): CoinDao

    abstract fun twoMinerDao(): TwoMinerDao
}