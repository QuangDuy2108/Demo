package com.ominext.demo_1.di.module

import android.app.Application
import com.ominext.demo_1.BaseApplication
import com.ominext.demo_1.api.ApiService
import com.ominext.demo_1.api.RealmService
import dagger.Module
import dagger.Provides
import io.realm.Realm
import javax.inject.Singleton

@Module
class AppModule(private val baseApp: BaseApplication) {

    @Provides
    @Singleton
    internal fun provideApplication(): Application = baseApp

    @Provides
    internal fun provideApiService(): ApiService = ApiService.create()

    @Provides
    internal fun provideRealm(): Realm = Realm.getDefaultInstance()

    @Provides
    internal fun provideRealmService(): RealmService {
        return RealmService()
    }
}