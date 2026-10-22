package plat.sebastian.lab9.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import plat.sebastian.lab9.data.local.dao.CharacterDao
import plat.sebastian.lab9.data.local.dao.LocationDao
import plat.sebastian.lab9.data.local.entity.CharacterEntity
import plat.sebastian.lab9.data.local.entity.LocationEntity

@Database(
    entities = [
        CharacterEntity::class,
        LocationEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    abstract fun locationDao(): LocationDao
}