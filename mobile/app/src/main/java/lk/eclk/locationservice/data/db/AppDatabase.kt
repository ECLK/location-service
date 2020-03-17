package lk.eclk.locationservice.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import lk.eclk.locationservice.data.db.dao.LocationDao
import lk.eclk.locationservice.data.db.dao.MediaItemDao
import lk.eclk.locationservice.models.Location


@Database(entities = [Location::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    // for caching
    abstract fun locationsDao(): LocationDao

    abstract fun mediaItemDao(): MediaItemDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "locationService.db"
        ).build()
    }
}