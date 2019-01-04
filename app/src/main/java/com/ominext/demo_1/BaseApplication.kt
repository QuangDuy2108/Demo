package com.ominext.demo_1

import android.app.Application
import com.ominext.demo_1.di.component.AppComponent
import com.ominext.demo_1.di.component.DaggerAppComponent
import com.ominext.demo_1.di.module.AppModule

class BaseApplication : Application() {
    private lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        setup()
        if (BuildConfig.DEBUG){

        }
    }

    private fun setup() {
        component = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        component.inject(this)
    }

    fun getAppComponent(): AppComponent = component
    
    companion object {
        lateinit var instance: BaseApplication private set
    }
}