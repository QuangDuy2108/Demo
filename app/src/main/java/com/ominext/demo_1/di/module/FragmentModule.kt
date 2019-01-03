package com.ominext.demo_1.di.module

import android.app.Fragment
import com.ominext.demo_1.api.ApiService
import com.ominext.demo_1.ui.list.ListContact
import com.ominext.demo_1.ui.list.ListPresenter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule{

    @Provides
    fun providePresenter(): ListContact.Presenter = ListPresenter()

    @Provides
    fun provideApiService(): ApiService = ApiService.create()
}