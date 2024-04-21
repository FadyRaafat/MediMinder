package com.fady.mediminder.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fady.mediminder.data.datasource.local.userprofile.UserProfile
import com.fady.mediminder.data.datasource.local.userprofile.UserProfileDao

@Database(entities = [UserProfile::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getUserProfileDao(): UserProfileDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, AppDataBase::class.java, DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }

        private const val DATABASE_NAME = "mediminder_database"
    }

}
