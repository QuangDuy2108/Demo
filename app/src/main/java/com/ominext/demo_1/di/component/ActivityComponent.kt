package com.ominext.demo_1.di.component

import com.ominext.demo_1.di.module.ActivityModule
import com.ominext.demo_1.ui.main.MainActivity
import dagger.Component


@Component(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)
}