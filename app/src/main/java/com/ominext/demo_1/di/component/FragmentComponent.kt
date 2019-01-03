package com.ominext.demo_1.di.component

import com.ominext.demo_1.di.module.FragmentModule
import com.ominext.demo_1.ui.list.ListFragment
import dagger.Component


@Component(modules = [FragmentModule::class])
interface FragmentComponent {

    fun inject(listFragment: ListFragment)
}