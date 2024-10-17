package com.example.schreduler.di

import android.content.Context
import androidx.room.Room
import com.example.schreduler.data.room.dao.EmployeeDao
import com.example.schreduler.data.room.dao.SchedulerDao
import com.example.schreduler.data.room.database.AppDatabase
import com.example.schreduler.data.room.repository.EmployeeRepository
import com.example.schreduler.data.room.repository.ScheduleRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .disableHtmlEscaping()
        .create()

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "database3.db"
    ).build()

    @Singleton
    @Provides
    fun provideEmployeeDao(db: AppDatabase) = db.getEmployeeDao()

    @Singleton
    @Provides
    fun provideEmployeeRepository(employeeDao: EmployeeDao): EmployeeRepository =
        EmployeeRepository(employeeDao)

    @Singleton
    @Provides
    fun provideSchedulerDao(db: AppDatabase) = db.getSchedulerDao()

    @Singleton
    @Provides
    fun provideSchedulerRepository(schedulerDao: SchedulerDao): ScheduleRepository =
        ScheduleRepository(schedulerDao)

}