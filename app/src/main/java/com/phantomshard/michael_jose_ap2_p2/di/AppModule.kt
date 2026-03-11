package com.phantomshard.michael_jose_ap2_p2.di

import com.phantomshard.michael_jose_ap2_p2.data.remote.SnakeApi
import com.phantomshard.michael_jose_ap2_p2.data.remotedatasource.JugadorRemoteDataSource
import com.phantomshard.michael_jose_ap2_p2.data.repository.JugadorRepositoryImpl
import com.phantomshard.michael_jose_ap2_p2.domain.repository.JugadorRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://gestionhuacalesapi.azurewebsites.net/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Singleton
    @Provides
    fun provideSnakeApi(retrofit: Retrofit): SnakeApi {
        return retrofit.create(SnakeApi::class.java)
    }

    @Singleton
    @Provides
    fun provideJugadorRepository(remoteDataSource: JugadorRemoteDataSource): JugadorRepository {
        return JugadorRepositoryImpl(remoteDataSource)
    }
}
