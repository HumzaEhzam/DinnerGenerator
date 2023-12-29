package com.example.dinnergenerator.di

import android.app.Application
import androidx.room.Room
import com.example.dinnergenerator.database.AppDataBase
import com.example.dinnergenerator.utils.CONSTANTS.DRINK_PROVIDER
import com.example.dinnergenerator.utils.CONSTANTS.MEAL_PROVIDER

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        app: Application,
    ) = Room.databaseBuilder(app, AppDataBase::class.java, "app_db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideTaskDao(db: AppDataBase) = db.dbDao()


    @Provides
    @Singleton
    @Named(MEAL_PROVIDER)
    fun mealProvideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    @Named(DRINK_PROVIDER)
    fun drinkProvideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}