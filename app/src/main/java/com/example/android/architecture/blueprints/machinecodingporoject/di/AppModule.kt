package com.example.android.architecture.blueprints.machinecodingporoject.di

import android.content.Context
import com.example.android.architecture.blueprints.machinecodingporoject.data.DataSource
import com.example.android.architecture.blueprints.machinecodingporoject.data.JsonDataSource
import com.example.android.architecture.blueprints.machinecodingporoject.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Singleton
    @Provides
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideDataSource(@ApplicationContext context:Context):DataSource{
        return JsonDataSource(context)
    }

    @Provides
    @Singleton
    fun provideCheckGameResult():WordMatchManager{
        return MatchExactWord()
    }

    @Provides
    @Singleton
    fun provideGameSelect(dataSource: DataSource):GameSelect{
        return GameSelectInCircle(dataSource)
    }

    @Provides
    @Singleton
    fun provideRandomCharacters():RandomCharacters{
        return DefaultRandomCharacter()
    }



}