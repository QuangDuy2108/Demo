package com.ominext.demo_1.ui.main

import io.reactivex.disposables.CompositeDisposable

class MainPresenter: MainContact.Presenter {

    private val subcription = CompositeDisposable()
    private lateinit var view : MainContact.View

    override fun subcribe() {

    }

    override fun unSubcribe() {
        subcription.clear()
    }

    override fun attach(view: MainContact.View) {
        this.view = view
        view.showFragment()
    }
}