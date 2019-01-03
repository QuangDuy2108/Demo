package com.ominext.demo_1.di.module

import android.app.Application
import com.ominext.demo_1.BaseApplication
import com.ominext.demo_1.di.scope.PerApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val baseApp: BaseApplication) {
    @Provides
    @Singleton
    @PerApp
    fun provideApplication(): Application = baseApp
}