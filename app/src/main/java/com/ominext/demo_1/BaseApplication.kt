package com.ominext.demo_1

import android.annotation.SuppressLint
import android.app.Application
import com.google.firebase.FirebaseApp
import com.ominext.demo_1.di.component.AppComponent
import com.ominext.demo_1.di.component.DaggerAppComponent
import com.ominext.demo_1.di.module.AppModule
import io.realm.Realm
import io.realm.RealmConfiguration

@SuppressLint("Registered")
class BaseApplication : Application() {
    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        setup()
        initRealmConfiguration()
        if (BuildConfig.DEBUG) {

        }
        FirebaseApp.initializeApp(this)
    }

    private fun setup() {
        component = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        component.inject(this)
    }

    private fun initRealmConfiguration() {
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .name("chat.realm")
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)
    }


    fun getAppComponent(): AppComponent = component

    companion object {
        lateinit var instance: BaseApplication private set
    }
}