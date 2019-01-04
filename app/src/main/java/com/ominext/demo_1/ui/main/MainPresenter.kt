package com.ominext.demo_1.ui.main

import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainPresenter @Inject constructor() {

    private val subcription = CompositeDisposable()
    private lateinit var view: MainContact.View

    fun subcribe() {

    }

    fun unSubcribe() {
        subcription.clear()
    }

    fun attach(view: MainContact.View) {
        this.view = view
        view.showFragment()
    }
}