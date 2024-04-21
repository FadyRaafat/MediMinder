package com.fady.mediminder.data.di

import android.content.Context
import com.fady.mediminder.data.datasource.local.AppDataBase
import com.fady.mediminder.data.datasource.local.userprofile.UserProfileDao
import com.fady.mediminder.data.datasource.remote.MedsRemoteDataSource
import com.fady.mediminder.data.datasource.remote.service.ClientService
import com.fady.mediminder.data.repository.MedsRepositoryImpl
import com.fady.mediminder.domain.repository.MedsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MedsModule {
    @Provides
    @Singleton
    fun provideNewsRepository(
        medsRemoteDataSource: MedsRemoteDataSource,
        userProfileDao: UserProfileDao
    ): MedsRepository {
        return MedsRepositoryImpl(medsRemoteDataSource, userProfileDao)
    }


    @Provides
    @Singleton
    fun provideMedsServices(retrofit: Retrofit): ClientService =
        retrofit.create(ClientService::class.java)

    @Singleton
    @Provides
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ): AppDataBase = AppDataBase.getDatabase(context)


    @Singleton
    @Provides
    fun provideUserProfileDao(appDataBase: AppDataBase) = appDataBase.getUserProfileDao()

}
