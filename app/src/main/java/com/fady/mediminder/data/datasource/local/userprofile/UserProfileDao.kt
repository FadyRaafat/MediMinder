package com.fady.mediminder.data.datasource.local.userprofile

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fady.mediminder.data.datasource.local.userprofile.UserProfile

@Dao
interface UserProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserProfile(userProfile: UserProfile)

    @Query("SELECT * FROM UserProfile")
    suspend fun getUserProfile(): UserProfile?

    @Query("DELETE FROM UserProfile")
    suspend fun deleteUserProfile()

}