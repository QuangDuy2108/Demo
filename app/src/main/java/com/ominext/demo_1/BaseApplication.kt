package com.ominext.demo_1

import android.annotation.SuppressLint
import android.app.Application
import com.ominext.demo_1.di.component.AppComponent
import com.ominext.demo_1.di.component.DaggerAppComponent
import com.ominext.demo_1.di.module.AppModule

@SuppressLint("Registered")
class BaseApplication : Application() {
    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        setup()
        if (BuildConfig.DEBUG) {

        }
    }

    private fun setup() {
        component = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        component.inject(this)
    }

    private fun initRealm(){}

    fun getAppComponent(): AppComponent = component

    companion object {
        lateinit var instance: BaseApplication private set
    }
}