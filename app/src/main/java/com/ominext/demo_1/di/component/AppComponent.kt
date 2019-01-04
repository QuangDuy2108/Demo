package com.ominext.demo_1.di.component

import com.ominext.demo_1.BaseApplication
import com.ominext.demo_1.di.module.ActivityModule
import com.ominext.demo_1.di.module.AppModule
import com.ominext.demo_1.di.module.DetailModule
import com.ominext.demo_1.di.module.FragmentModule
import com.ominext.demo_1.ui.detail.DetailFragment
import com.ominext.demo_1.ui.list.ListFragment
import com.ominext.demo_1.ui.main.MainActivity
import dagger.Component

@Component(modules = [AppModule::class, ActivityModule::class, FragmentModule::class, DetailModule::class])
interface AppComponent {

    fun inject(app: BaseApplication)

    fun inject(mainActivity: MainActivity)

    fun inject(listFragment: ListFragment)

    fun inject(detailFragment: DetailFragment)
}