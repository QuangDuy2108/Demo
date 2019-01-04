package com.ominext.demo_1.di.module

import android.app.Application
import com.ominext.demo_1.BaseApplication
import com.ominext.demo_1.api.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val baseApp: BaseApplication) {

    @Provides
    @Singleton
    fun provideApplication(): Application = baseApp

    @Provides
    fun provideApiService(): ApiService = ApiService.create()
}