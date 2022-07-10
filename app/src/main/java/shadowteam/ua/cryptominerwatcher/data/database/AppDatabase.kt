package shadowteam.ua.cryptominerwatcher.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CoinInfoDbModel::class], version = 1, exportSchema = false)
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

    abstract fun cryptoMinerDao(): CryptoMinerDao
}