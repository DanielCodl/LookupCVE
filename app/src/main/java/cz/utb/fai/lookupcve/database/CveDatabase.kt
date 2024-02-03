package cz.utb.fai.lookupcve.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteStatement

@Database(entities = [CveDTO::class], version = 1, exportSchema = false)
abstract class CveDatabase:RoomDatabase() {

    abstract fun CveDao(): CveDao

    companion object {
        @Volatile
        private var INSTANCE: CveDatabase? = null

        fun getDatabase(context: Context): CveDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CveDatabase::class.java,
                    "cve_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}




