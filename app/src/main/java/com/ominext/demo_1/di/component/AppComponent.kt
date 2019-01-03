package com.ominext.demo_1.di.component

import com.ominext.demo_1.BaseApplication
import com.ominext.demo_1.di.module.AppModule
import dagger.Component
import dagger.Module

@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(app: BaseApplication)
}