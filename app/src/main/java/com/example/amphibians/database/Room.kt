package com.example.amphibians.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AmphibianDao {
    @Query("select * from amphibian_database")
    fun getAmphibians(): LiveData<List<AmphibianDatabaseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( photos: List<AmphibianDatabaseEntity>)
}



@Database(entities = [AmphibianDatabaseEntity::class], version = 1)
abstract class AmphibianDatabase: RoomDatabase() {
    abstract val amphibianDao: AmphibianDao
}

private lateinit var INSTANCE: AmphibianDatabase

fun getDatabase(context: Context): AmphibianDatabase {
    synchronized(AmphibianDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                AmphibianDatabase::class.java,
                "photos").build()
        }
    }
    return INSTANCE
}
