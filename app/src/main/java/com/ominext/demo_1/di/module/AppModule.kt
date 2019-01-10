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
    internal fun provideApplication(): Application = baseApp

    @Provides
    internal fun provideApiService(): ApiService = ApiService.create()

//    @Provides
//    internal fun provideRealm(): Realm = Realm.getDefaultInstance()
//
//    @Provides
//    internal fun provideRealmService(realm: Realm): RealmService {
//        return RealmService(realm)
//    }
//
//    @Provides
//    internal fun provideChatPresenter(realmService: RealmService): ChatPresenter {
//        return ChatPresenter(realmService)
//    }
}