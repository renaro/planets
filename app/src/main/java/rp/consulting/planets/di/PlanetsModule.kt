package rp.consulting.planets.di

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import rp.consulting.planets.data.PlanetsRepository
import rp.consulting.planets.data.api.PlanetsService
import rp.consulting.planets.data.database.AppDatabase
import rp.consulting.planets.data.database.PlanetsDao

@Module
@InstallIn(SingletonComponent::class)
object PlanetsModule {

    @Provides
    fun providePlanetService(): PlanetsService {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(
                Json.asConverterFactory(MediaType.parse("application/json")!!)
            )
            .baseUrl("https://us-central1-android-course-api.cloudfunctions.net/")
            .build()
        return retrofit.create(PlanetsService::class.java)
    }

    @Provides
    fun provideRepository(service: PlanetsService, dao: PlanetsDao): PlanetsRepository {
        return PlanetsRepository(service, dao)
    }

    @Provides
    fun provideDatabase(@ApplicationContext applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }

    @Provides
    fun provideDao(database: AppDatabase): PlanetsDao {
        return database.planetsDao()
    }

}