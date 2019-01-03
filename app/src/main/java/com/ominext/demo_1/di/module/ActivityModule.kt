package com.ominext.demo_1.di.module

import android.app.Activity
import com.ominext.demo_1.ui.main.MainContact
import com.ominext.demo_1.ui.main.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity = activity

    @Provides
    fun providePresenter(): MainContact.Presenter = MainPresenter()
}